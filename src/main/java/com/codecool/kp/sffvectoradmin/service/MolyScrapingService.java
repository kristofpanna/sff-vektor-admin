package com.codecool.kp.sffvectoradmin.service;

import com.codecool.kp.sffvectoradmin.model.Author;
import com.codecool.kp.sffvectoradmin.model.Book;
import com.codecool.kp.sffvectoradmin.model.BookList;
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

    // for testing
    public BookList getBookList(String bookListUrl) { // TODO refactor: refreshBookLists()
        log.info("== Listák frissítése molyról elkezdődött ");
        Document doc;
        try {
            doc = Jsoup.connect(getMolyUrl(bookListUrl)).get();
        } catch (IOException e) { // spec. HttpStatusException
            e.printStackTrace();
            return null;
        }
        log.info("== sikeres csatlakozás moly.hu-hoz ");

        final String listTitle = doc.selectFirst("h1").ownText();

        final List<MolyShelfItem> shelfItems = getShelfItemsFromDoc(doc);
        // TODO check note problems (no "sci-fi" or "fantasy") -> log error and don't refresh

        // TODO load all lists
        // TODO check if all books on 1 list (-> log error and don't refresh if not)
        final List<Book> books = getBooksFromShelfItems(shelfItems);

        log.info("== Lista/polc: " + listTitle + ", könyvek száma: " + books.size());
        return BookList.builder()
                .url(bookListUrl)
                .title(listTitle)
                .books(books)
                .build();
    }

    /* MolyShelfItem */

    private List<MolyShelfItem> getShelfItemsFromDoc(Document doc) {
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


    /* Book */

    private List<Book> getBooksFromShelfItems(List<MolyShelfItem> shelfItems) {
        return shelfItems.stream()
                .map(this::getBookFromShelfItem)
                .collect(Collectors.toList());
    }

    private Book getBookFromShelfItem(MolyShelfItem molyShelfItem) {
        // TODO from repo if saved
        return getBook(molyShelfItem.getUrl());
    }

    private Book getBook(String bookUrl) {
        Document doc;
        try {
            doc = Jsoup.connect(getMolyUrl(bookUrl)).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String title = doc.selectFirst("span.fn").ownText();

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


    private String getMolyUrl(String urlTail) {
        String molyBaseUrl = "https://moly.hu";
        return molyBaseUrl + urlTail;
    }
}
