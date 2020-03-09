package com.codecool.kp.sffvectoradmin;

import com.codecool.kp.sffvectoradmin.model.book.BookList;
import com.codecool.kp.sffvectoradmin.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        final BookList scifi = BookList.builder()
                .genre("sci-fi")
                .year(2019)
                .url("https://moly.hu/listak/2019-es-science-fiction-megjelenesek")
                .build();
        bookService.addNewList(scifi);

        final BookList fantasy = BookList.builder()
                .genre("fantasy")
                .year(2019)
                .url("https://moly.hu/listak/2019-es-fantasy-megjelenesek")
                .build();
        bookService.addNewList(fantasy);



    }
}
