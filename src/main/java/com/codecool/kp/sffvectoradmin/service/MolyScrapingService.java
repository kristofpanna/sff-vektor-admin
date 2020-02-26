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
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MolyScrapingService {

    public List<MolyShelfItem> getShelfItemsFromUrl(String shelfURL) {
        boolean isPolc = isPolc(shelfURL);
        String typeName = isPolc ? "Polc" : "Lista";

        Document doc = getDocumentFromUrl(shelfURL);
        log.info("== " + typeName + " szkennelése: " + doc.selectFirst("h1").ownText()); // TODO throw exception

        final Elements bookLinks = doc.select("a .fn, .book_selector");
        return bookLinks.stream()
                .map(e -> getShelfItemFromElement(e, isPolc))
                .peek(item -> log.info("=== " + typeName + "elem: " + item.getDescription() + ", url: " + item.getUrl()))
                .collect(Collectors.toList());
    }

    /**
     * Returns true if the url points to a "polc" (not a "lista")
     */
    private boolean isPolc(String url) {
        return url.startsWith("polcok", 1);
    }

    private String getNoteForPolcElement(Element element) {
        final String note = element
                .parent()   // h3.item
                .parent()   // div.book_atom
                .siblingElements().select(".sticky_note").first()
                .select("p").first()
                .text();
        log.info("=== Megjegyzés: " + note);
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

    public Book getBook(String bookUrl) {
        Document doc = getDocumentFromUrl(bookUrl);

        String title = doc.selectFirst("span.fn").ownText(); // TODO throw exception
        log.info("=== Új könyv, címe: " + title);

        final Elements authorLinks = doc.select("div.authors a");
        List<Author> authors = authorLinks.stream()
                .map(Element::text)
                .map(Author::new)
                .collect(Collectors.toList());

        return Book.builder()
                .url(bookUrl)
                .title(title)
                .authors(authors)
                .build();
    }

    /* Util */

    private Document getDocumentFromUrl(String Url) {
        Document doc;
        try {
            doc = Jsoup.connect(getMolyUrl(Url)).get();
        } catch (IOException e) { // spec. HttpStatusException
            e.printStackTrace();
            return null;
        }
        log.info("== Sikeres csatlakozás a moly.hu-hoz ");
        return doc;
    }

    private String getMolyUrl(String urlTail) {
        String molyBaseUrl = "https://moly.hu";
        return molyBaseUrl + urlTail;
    }
}
