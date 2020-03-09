package com.codecool.kp.sffvectoradmin.model.book;

import com.codecool.kp.sffvectoradmin.model.user.Reader;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class BookList {
    @Id
    @GeneratedValue
    private Long id;

    private String genre; // TODO only with url-compatible characters :)
    private int year; // TODO only year, with constraint
    private String url;

    @Singular
    @ManyToMany(mappedBy = "onLists", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Book> books = new ArrayList<>();

    @Singular
    @ManyToMany(mappedBy = "judgesLists")
    private List<Reader> readers = new ArrayList<>();

    /*
    public List<Book> getBooks() {
        // Collections.sort(books); // TODO why kills jackson?
        // Could not write JSON: (was java.lang.UnsupportedOperationException); nested exception is com.fasterxml.jackson.databind.JsonMappingException: (was java.lang.UnsupportedOperationException) (through reference chain: com.codecool.kp.sffvectoradmin.model.book.BookList["books"])
        //org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON: (was java.lang.UnsupportedOperationException); nested exception is com.fasterxml.jackson.databind.JsonMappingException: (was java.lang.UnsupportedOperationException) (through reference chain: com.codecool.kp.sffvectoradmin.model.book.BookList["books"])
        return books;
    }
     */

}
