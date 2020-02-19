# Notes

## Backend plan

### Model objects

* Book
    * String title
    * Set<Author> authors
    * String url
    
* Author
    * name
    
* BookList (?)
    * title
    * url
    * List<Book> books 
    
### API endpoints

* GET /list/{id}
* GET /book/{id}
...

## Web scraping

### HTML parsing
* tutorials for jsoup: 
    - https://www.baeldung.com/java-with-jsoup
    - https://www.thetechnojournals.com/2019/11/web-scraper-using-jsoup-and-spring-boot.html
    - https://stackabuse.com/web-scraping-the-java-way/
    - https://jsoup.org/ -> https://stackabuse.com/web-scraping-the-java-way/


### moly.hu structure

#### List page
* eg.
    * short: https://moly.hu/listak/2020-terv-4
    * long: https://moly.hu/listak/2019-es-science-fiction-megjelenesek
   
* TODO:
    - select all book links:  a .fn, .book_selector
    - for each:
        - get href attribute
        - go to book page
        - get book info
    - also with paging, if there is more
        - decide if there are multiple pages:
            * if exists "Következő" element (link/span):  .pagination .next_page
        - load all pages (until Next element has disabled class)
    
#### Book page
* eg. https://moly.hu/konyvek/adrian-tchaikovsky-az-ido-gyermekei
* TODO get book info: 
    * title:  span.fn
    * authors:  div.authors a
