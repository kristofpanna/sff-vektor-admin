package com.codecool.kp.sffvectoradmin.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue
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
