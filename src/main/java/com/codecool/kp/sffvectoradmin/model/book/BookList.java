package com.codecool.kp.sffvectoradmin.model.book;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
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
    private List<Book> books = new ArrayList<>();

    /*
    public List<Book> getBooks() {
        books.sort();
        return books;
    }

     */
}
