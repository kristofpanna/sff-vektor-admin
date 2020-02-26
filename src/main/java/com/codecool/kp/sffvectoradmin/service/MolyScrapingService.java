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
        Document doc = getDocumentFromUrl(shelfURL);
        log.info("== Lista szkennelése: " + doc.selectFirst("h1").ownText());
        final Elements bookLinks = doc.select("a .fn, .book_selector");
        return bookLinks.stream()
                .map(this::getShelfItemFromElement)
                .peek(item -> log.info("=== Lista/polc elem: " + item.getDescription() + ", url: " + item.getUrl()))
                .collect(Collectors.toList());
    }

    private MolyShelfItem getShelfItemFromElement(Element element) {
        return MolyShelfItem.builder()
                .url(element.attr("href"))
                .description(element.ownText())
                .build();
    }


    public Book getBook(String bookUrl) {
        Document doc = getDocumentFromUrl(bookUrl);

        String title = doc.selectFirst("span.fn").ownText(); // TODO exception
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
