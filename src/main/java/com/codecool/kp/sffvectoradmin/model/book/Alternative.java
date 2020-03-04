package com.codecool.kp.sffvectoradmin.model.book;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Alternative {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Book book;

    @Singular
    @OneToMany(mappedBy = "alternative", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<MolyUrl> urls;
}
