package com.codecool.kp.sffvectoradmin.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookList {
    private Long id;
    private String url;
    private String title;
    @Singular
    private List<Book> books = new ArrayList<>();
}
