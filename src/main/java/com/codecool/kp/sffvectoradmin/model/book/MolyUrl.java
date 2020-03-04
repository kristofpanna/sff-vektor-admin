package com.codecool.kp.sffvectoradmin.model.book;

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
public class MolyUrl {
    @Id
    @GeneratedValue
    private Long id;

    private String url;
    private int seqId;
}
