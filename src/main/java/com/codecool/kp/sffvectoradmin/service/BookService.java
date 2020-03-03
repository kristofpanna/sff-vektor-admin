package com.codecool.kp.sffvectoradmin.service;

import com.codecool.kp.sffvectoradmin.model.Book;
import com.codecool.kp.sffvectoradmin.model.BookList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {

    @Autowired
    MolyScrapingService scrapingService;

    // "repo" with one booklist
    private BookList bookList;

    public BookList getBookList(Long listId) {
        // TODO get from repository, do scheduled scrapings and save to db
        refreshBookLists();
        return bookList;
    }


    /* Refresh */

    public void refreshBookLists() {
        log.info("== Listák frissítése molyról elkezdődött ");

        // TODO all lists (from db)
        //String bookListUrl = "/listak/2019-es-science-fiction-megjelenesek";
        //String bookListUrl = "/polcok/besorolasra-var-2019";
        //String bookListUrl = "/polcok/csak-nalunk-kaphato-kiadvanyok";
        String bookListUrl = "/listak/2020-terv-4";

        final List<MolyShelfItem> shelfItems = scrapingService.getShelfItemsFromUrl(bookListUrl);
        // TODO check note problems (no "sci-fi" or "fantasy") -> log error and don't refresh

        // TODO load all lists
        // TODO check if all books on 1 list (-> log error and don't refresh if not)

        // refresh books
        final List<Book> books = refreshBooksByShelfItems(shelfItems);

        // TODO refresh book-booklist connections
        this.bookList = BookList.builder()
                .url(bookListUrl)
                .year(2020)
                .genre("sci-fi")
                .books(books)
                .build();

        log.info("== Lista frissítve: " + bookList.getGenre() + ", könyvek száma: " + books.size());
    }

    private List<Book> refreshBooksByShelfItems(List<MolyShelfItem> shelfItems) {
        return shelfItems.stream()
                .map(this::refreshBookByShelfItem)
                .collect(Collectors.toList());
    }

    private Book refreshBookByShelfItem(MolyShelfItem shelfItem) {
        // TODO save book if not yet in repo
        return scrapingService.getBook(shelfItem.getUrl());
    }
}
