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
public class VectorUser {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private boolean isAdmin;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Reader reader;
}
