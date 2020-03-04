package com.codecool.kp.sffvectoradmin.model.book;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String series;
    private int numberInSeries;
    private int year;

    private boolean isPending;
    private boolean isApproved = false;

    @Setter(AccessLevel.NONE)
    @Transient
    private String key;

    @Singular
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(mappedBy = "books", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<BookList> lists = new HashSet<>();

    public String getKey() {
        String seriesInfo = "";
        if (series != null) {
            seriesInfo = series + " " + numberInSeries + " - ";
        }

        String authorNames = authors.stream()
                .map(Author::getDisplayName)
                .sorted()
                .collect(Collectors.joining(", "));

        return authorNames + ": " + seriesInfo + title;
    }
}
