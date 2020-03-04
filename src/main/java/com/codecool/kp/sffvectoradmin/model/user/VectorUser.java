package com.codecool.kp.sffvectoradmin.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VectorUser {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private boolean isAdmin;
}
