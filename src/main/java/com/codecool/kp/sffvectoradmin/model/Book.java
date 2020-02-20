package com.codecool.kp.sffvectoradmin.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private Long id;
    private String title;
    private String url;
    @Singular
    private List<Author> authors = new ArrayList<>();
}
