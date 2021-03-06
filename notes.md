# Notes

## Backend plan
    
### API endpoints

* GET /list/{id}
* GET /book/{id}
...

## Web scraping

### HTML parsing
* tutorials for jsoup: 
    + https://www.baeldung.com/java-with-jsoup
    - https://www.thetechnojournals.com/2019/11/web-scraper-using-jsoup-and-spring-boot.html
    - https://stackabuse.com/web-scraping-the-java-way/
    - https://jsoup.org/ -> https://stackabuse.com/web-scraping-the-java-way/


### moly.hu structure

#### List page
* eg. https://moly.hu
    * short "lista": /listak/2020-terv-4
    * long (42) "lista": /listak/2019-es-science-fiction-megjelenesek
    * short "polc": /polcok/besorolasra-var-2019
    * long (22) "polc": /polcok/csak-nalunk-kaphato-kiadvanyok
   
* TODO:
    + select list title: h1 
    + select all book links:  a .fn, .book_selector
    + for each:
        + get href attribute
        + go to book page
        + get book info
    + also with paging, if there is more
        + decide if there are multiple pages:
            * if exists "Következő" element (link/span):  .pagination .next_page   -> href: url of next page
        + load all pages (until Next element has disabled class)
    + also with moly.hu "polc" 
    
#### Book page
* eg. https://moly.hu/konyvek/adrian-tchaikovsky-az-ido-gyermekei
+ get book info: 
    + title:  span.fn
    + authors:  div.authors a
  
    
## Persistence
* many-to-many
    * https://www.baeldung.com/jpa-many-to-many
    * (composite id: https://www.baeldung.com/hibernate-identifiers)

