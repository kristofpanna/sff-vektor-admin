package com.codecool.kp.sffvectoradmin.model.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
