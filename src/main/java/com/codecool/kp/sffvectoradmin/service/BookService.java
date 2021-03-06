package com.codecool.kp.sffvectoradmin.service;

import com.codecool.kp.sffvectoradmin.model.book.Book;
import com.codecool.kp.sffvectoradmin.model.book.BookList;
import com.codecool.kp.sffvectoradmin.repository.BookListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {

    @Autowired
    BookListRepository listRepository;

    @Autowired
    MolyScrapingService scrapingService;


    public BookList getBookList(int year, String genre) {
        return listRepository.findByYearAndGenre(year, genre).get();
    }

    public List<BookList> getBookListsByYear(int year) {
        return listRepository.findAllByYear(year);
    }

    public void addNewList(BookList list) {
        listRepository.save(list);
    }


    /* Refresh */

    public void refreshBookLists() {
        log.info("== Listák frissítése molyról elkezdődött ");

        // TODO all lists (from db)
        //String bookListUrl = "/listak/2019-es-science-fiction-megjelenesek";
        //String bookListUrl = "/polcok/besorolasra-var-2019";
        String bookListUrl = "/listak/2020-terv-4";

        final List<MolyShelfItem> shelfItems = scrapingService.getShelfItemsFromUrl(bookListUrl);
        // TODO check note problems (no "sci-fi" or "fantasy") -> log error and don't refresh

        // TODO load all lists
        // TODO check if all books on 1 list (-> log error and don't refresh if not)

        // refresh books
        final List<Book> books = refreshBooksByShelfItems(shelfItems);

        // TODO refresh book-booklist connections
        var bookList = BookList.builder()
                .url(bookListUrl)
                .year(2020)
                .genre("sci-fi")
                .books(books)
                .build();

        for (Book book : books) {
            book.setYear(bookList.getYear());
        }

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
