package com.codecool.kp.sffvectoradmin.model.user;

import com.codecool.kp.sffvectoradmin.model.book.Book;
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
public class Plan {
    @EmbeddedId
    private PlanKey planKey;

    private PlanStatus status;

    @ManyToOne
    @MapsId("reader_id")
    private Reader reader;

    @ManyToOne
    @MapsId("book_id")
    private Book book;

    public enum PlanStatus {
        UNDECIDED,
        NO,
        YES,
        DONE
    }
}
