package sax;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
    String lang;
    String title;
    String id;
    String isbn;
    Date regDate;
    String publisher;
    int price;
    List<String> authors;
    public Book(){
        authors=new ArrayList<String>();
    }
    //getters and setters

    
}