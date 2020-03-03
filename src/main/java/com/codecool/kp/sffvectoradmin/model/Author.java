package com.codecool.kp.sffvectoradmin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Long id;

    private String displayName;
    private String sortName;
    private String url;

    public Author(String displayName, String url) {
        this.displayName = displayName;
        this.sortName = displayName;
        this.url = url;
    }
}
