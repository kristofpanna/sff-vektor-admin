package com.codecool.kp.sffvectoradmin.model.book;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BookList {
    @Id
    @GeneratedValue
    private Long id;

    private String genre;
    private int year;
    private String url;

    @Singular
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Book> books = new ArrayList<>();


    /*
    public List<Book> getBooks() {
        // Collections.sort(books); // TODO why kills jackson?
        // Could not write JSON: (was java.lang.UnsupportedOperationException); nested exception is com.fasterxml.jackson.databind.JsonMappingException: (was java.lang.UnsupportedOperationException) (through reference chain: com.codecool.kp.sffvectoradmin.model.book.BookList["books"])
        //org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON: (was java.lang.UnsupportedOperationException); nested exception is com.fasterxml.jackson.databind.JsonMappingException: (was java.lang.UnsupportedOperationException) (through reference chain: com.codecool.kp.sffvectoradmin.model.book.BookList["books"])
        return books;
    }
     */

}
