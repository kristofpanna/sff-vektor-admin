package com.codecool.kp.sffvectoradmin.controller;

import com.codecool.kp.sffvectoradmin.model.BookList;
import com.codecool.kp.sffvectoradmin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/list/{listId}")
    public BookList getBookList(@PathVariable Long listId) {
        return bookService.getBookList(listId);
    }
}
