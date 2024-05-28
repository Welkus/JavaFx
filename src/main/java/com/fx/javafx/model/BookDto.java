package com.fx.javafx.model;


import lombok.Data;

import java.util.List;

@Data
public class BookDto {


    private long bookId;
    private String bookTitle = "";
    private String bookAuthor = "";
    private int yearOfPublication;
    private List<String> bookGenre;
    private String bookState = "";
}
