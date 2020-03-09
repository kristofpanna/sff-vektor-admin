package com.codecool.kp.sffvectoradmin.repository;

import com.codecool.kp.sffvectoradmin.model.book.BookList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookListRepository extends JpaRepository<BookList, Long> {

    Optional<BookList> findByYearAndGenre(int year, String genre);

    List<BookList> findAllByYear(int year);

}
