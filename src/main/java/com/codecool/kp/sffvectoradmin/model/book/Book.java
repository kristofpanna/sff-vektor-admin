package com.codecool.kp.sffvectoradmin.model.book;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private Long id;

    private String title;
    private String series;
    private int numberInSeries;
    private int year;

    private boolean isPending;
    private boolean isApproved = false;

    @Setter(AccessLevel.NONE)
    // TODO @Transient
    private String key;

    @Singular
    private List<Author> authors = new ArrayList<>();

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
