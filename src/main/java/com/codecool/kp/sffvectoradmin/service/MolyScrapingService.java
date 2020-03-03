package com.codecool.kp.sffvectoradmin.service;

import com.codecool.kp.sffvectoradmin.model.Author;
import com.codecool.kp.sffvectoradmin.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MolyScrapingService {

    /* ShelfItems */

    public List<MolyShelfItem> getShelfItemsFromUrl(String shelfUrl) {
        boolean isPolc = isPolc(shelfUrl);
        String typeName = isPolc ? "Polc" : "Lista";

        final Elements bookLinks = getBookLinksFromAllPages(shelfUrl);
        return bookLinks.stream()
                .map(e -> getShelfItemFromElement(e, isPolc))
                .peek(item -> log.info("=== " + typeName + "elem: " + item.getDescription() + ", url: " + item.getUrl()))
                .collect(Collectors.toList());
    }

    private Elements getBookLinksFromAllPages(String url) {
        String typeName = isPolc(url) ? "Polc" : "Lista"; // for log
        Document doc;
        Elements bookLinks = new Elements();
        String nextUrl = url;
        do {
            doc = getDocumentFromUrl(nextUrl);
            bookLinks.addAll(getBookLinksFromPage(doc));
            nextUrl = getNextUrl(doc);
        } while (nextUrl != null);

        log.info("== " + typeName + " szkennelése befejeződött: " + url + " könyvek száma: " + bookLinks.size());
        return bookLinks;
    }

    private Elements getBookLinksFromPage(Document document) {
        return document.select("a .fn, .book_selector");
    }

    private String getNextUrl(Document doc) {
        final Elements nextLinks = doc.select(".pagination .next_page");
        if (nextLinks.isEmpty()) {
            return null;
        }
        return nextLinks.first().attr("href");
    }

    /**
     * Returns true if the url points to a "polc" on moly.hu (not a "lista")
     */
    private boolean isPolc(String url) {
        return url.startsWith("polcok", 1);
    }

    private String getNoteForPolcElement(Element element) {
        String note = null;
        try {
            note = element
                    .parent()   // h3.item
                    .parent()   // div.book_atom
                    .siblingElements().select(".sticky_note").first()
                    .select("p").first()
                    .text();
            log.info("=== Megjegyzés: " + note);
        } catch (NullPointerException e) {
            log.info("=== Nincs megjegyzés a polcelemnél!"); // TODO is it normal not to have a note?
        }
        return note;
    }

    private MolyShelfItem getShelfItemFromElement(Element element, boolean isPolcElement) {
        String note = isPolcElement ? getNoteForPolcElement(element) : null;
        return MolyShelfItem.builder()
                .url(element.attr("href"))
                .description(element.ownText())
                .note(note)
                .build();
    }

    /* Books */

    public Book getBook(String bookUrl) {
        Document doc = getDocumentFromUrl(bookUrl);

        String title = doc.selectFirst("span.fn").ownText();
        log.info("=== Új könyv, címe: " + title);

        List<Author> authors = getAuthorsFromBookPage(doc);

        return Book.builder()
                //.url(bookUrl) // todo url->alternative
                .title(title)
                .authors(authors)
                .build();
    }

    private List<Author> getAuthorsFromBookPage(Document doc) {
        final Elements authorLinks = doc.select("div.authors a");
        List<Author> authors = new ArrayList<>();
        for (Element authorLink : authorLinks) {
            String text = authorLink.text();
            String url = authorLink.attr("href");
            Author author = new Author(text, url);
            authors.add(author);
        }
        return authors;
    }

    /* Util */

    private Document getDocumentFromUrl(String url) {
        Document doc;
        try {
            doc = Jsoup.connect(getMolyUrl(url)).get();
        } catch (IOException e) { // spec. HttpStatusException // TODO throw exception
            e.printStackTrace();
            log.error("Nem elérhető: " + getMolyUrl(url));
            return null;
        }
        log.debug("== Sikeres csatlakozás a moly.hu-hoz ");
        return doc;
    }

    private String getMolyUrl(String urlTail) {
        String molyBaseUrl = "https://moly.hu";
        return molyBaseUrl + urlTail;
    }
}
