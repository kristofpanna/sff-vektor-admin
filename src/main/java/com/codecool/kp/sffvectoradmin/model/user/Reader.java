package com.codecool.kp.sffvectoradmin.model.user;

import com.codecool.kp.sffvectoradmin.model.book.BookList;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Reader {
    @Id
    @GeneratedValue
    private Long id;

    private String molyName;
    private String url;

    @OneToOne(mappedBy = "reader", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private VectorUser user;

    @Singular
    @ManyToMany
    @JoinTable(
            name = "judge",
            joinColumns = @JoinColumn(name = "reader_id"),
            inverseJoinColumns = @JoinColumn(name = "booklist_id")
    )
    private List<BookList> judgesLists = new ArrayList<>();
}
