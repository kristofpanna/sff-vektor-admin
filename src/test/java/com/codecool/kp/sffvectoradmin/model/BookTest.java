package com.codecool.kp.sffvectoradmin.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void getKey() {
        Author author1 = new Author("Arkagyij Sztrugackij", null);
        Author author3 = new Author("Mégegy Sztrugackij", null);
        Author author2 = new Author("Borisz Sztrugackij", null);

        final Book book = Book.builder()
                .title("A sztalker visszatér")
                .author(author1)
                .author(author2)
                .author(author3)
                .year(2050)
                .series("Sztrugackij életmű sorozat")
                .numberInSeries(2)
                .build();

        final String expectedKey = "Arkagyij Sztrugackij, Borisz Sztrugackij, Mégegy Sztrugackij: Sztrugackij életmű sorozat 2 - A sztalker visszatér";
        assertEquals(expectedKey, book.getKey());

    }
}