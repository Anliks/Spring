package com.edu.ulab.app.entity;


import lombok.Data;

@Data
public class Book {

    private Long id;
    private String title;
    private String author;
    private long pageCount;
    private Long userId;

}
