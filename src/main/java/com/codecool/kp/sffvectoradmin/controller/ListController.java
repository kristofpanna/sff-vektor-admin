package com.codecool.kp.sffvectoradmin.controller;

import com.codecool.kp.sffvectoradmin.model.book.BookList;
import com.codecool.kp.sffvectoradmin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/list")
public class ListController {
    @Autowired
    private BookService bookService;

    @GetMapping("/{year}/{genre}")
    public BookList getBookList(@PathVariable int year, @PathVariable String genre) {
        return bookService.getBookList(year, genre);
    }

    @GetMapping("/{year}")
    public List<BookList> getBookList(@PathVariable int year) {
        return bookService.getBookListsByYear(year);
    }
}
