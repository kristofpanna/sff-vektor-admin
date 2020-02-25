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

    public BookList getBookList(String bookListUrl) {
        Document doc;
        try {
            doc = Jsoup.connect(getMolyUrl(bookListUrl)).get();
        } catch (IOException e) { // spec. HttpStatusException
            e.printStackTrace();
            return null;
        }

        final String listTitle = doc.selectFirst("h1").ownText();

        final List<Book> books = getBooksFromListPage(doc);

        log.info("== Got this list from moly.hu: " + listTitle + ", length: " + books.size());
        return BookList.builder()
                .url(bookListUrl)
                .title(listTitle)
                .books(books)
                .build();
    }

    private List<Book> getBooksFromListPage(Document doc) {
        final Elements bookLinks = doc.select("a .fn, .book_selector");
        return bookLinks.stream()
                .map(element -> element.attr("href"))
                .map(this::getBook)
                .collect(Collectors.toList());
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
