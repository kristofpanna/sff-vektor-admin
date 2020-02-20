package com.codecool.kp.sffvectoradmin.service;

import com.codecool.kp.sffvectoradmin.model.BookList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    MolyScrapingService scrapingService;

    public BookList getBookList(Long listId) {
        // TODO get from repository, do scheduled scrapings and save to db
        return scrapingService.getBookList("https://moly.hu/listak/2020-terv-4");
    }
}
