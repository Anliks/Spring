package com.edu.ulab.app.repository;

import com.edu.ulab.app.entity.Book;

import java.util.List;



public interface AbstractRepository {

    Object getById(Long id);

    <T> T create(T t);
    <T> T update(T t);

    void delete(Long id);

    List<Book> getBooks();

    Long getCurrentId();

}
