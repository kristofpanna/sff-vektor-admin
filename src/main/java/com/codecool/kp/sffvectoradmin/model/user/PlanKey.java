package com.codecool.kp.sffvectoradmin.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class PlanKey implements Serializable {
    @Column(name = "reader_id")
    private long readerId;

    @Column(name = "book_id")
    private long bookId;
}
