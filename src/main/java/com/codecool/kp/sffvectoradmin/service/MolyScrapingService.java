package com.codecool.kp.sffvectoradmin.service;

import com.codecool.kp.sffvectoradmin.model.Author;
import com.codecool.kp.sffvectoradmin.model.Book;
import com.codecool.kp.sffvectoradmin.model.BookList;
import org.springframework.stereotype.Service;

@Service
public class MolyScrapingService {

    public BookList getBookList(String bookListUrl) {
        // sample data for testing
        Author a1 = Author.builder()
                .id(1L)
                .name("author1")
                .build();
        Author a2 = Author.builder()
                .id(2L)
                .name("author2")
                .build();
        Book book1 = Book.builder()
                .id(1L)
                .title("title 1")
                .author(a1)
                .author(a2)
                .build();
        Book book2 = Book.builder()
                .id(1L)
                .title("title 2")
                .author(a1)
                .build();

        return BookList.builder()
                .id(1L)
                .url(bookListUrl)
                .book(book1)
                .book(book2)
                .build();
    }
}
