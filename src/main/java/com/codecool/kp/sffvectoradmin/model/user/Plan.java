package com.codecool.kp.sffvectoradmin.model.user;

import com.codecool.kp.sffvectoradmin.model.book.Book;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Plan {
    @Id
    @GeneratedValue
    private Long id;

    private PlanStatus status;

    @ManyToOne
    private Reader reader;

    @ManyToOne
    private Book book;

    public enum PlanStatus {
        UNDECIDED,
        NO,
        YES,
        DONE
    }
}
