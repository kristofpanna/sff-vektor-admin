package com.codecool.kp.sffvectoradmin.service;

import com.codecool.kp.sffvectoradmin.BaseTestWithMockito;
import com.codecool.kp.sffvectoradmin.model.Author;
import com.codecool.kp.sffvectoradmin.model.Book;
import com.codecool.kp.sffvectoradmin.model.BookList;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;



class BookServiceTest extends BaseTestWithMockito {

    @Mock
    MolyScrapingService molyScrapingService;

    @InjectMocks
    BookService bookService;

    @Test
    void getBookList() {
        String bookListUrl = "https://moly.hu/listak/2020-terv-4";

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
        BookList bookList = BookList.builder()
                .id(1L)
                .title("sff list")
                .url(bookListUrl)
                .book(book1)
                .book(book2)
                .build();

        when(molyScrapingService.getBookList(bookListUrl)).thenReturn(bookList);


        BookList gotBookList = bookService.getBookList(1L);


        assertThat(gotBookList)
                .hasNoNullFieldsOrProperties()
                .matches(l -> l.getTitle().contentEquals("sff list"))
                .matches(l -> l.getBooks().size() == 2)
                .matches(l -> l.getBooks().contains(book1));

    }
}