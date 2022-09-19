package com.edu.ulab.app.repository;

import com.edu.ulab.app.entity.Book;


public interface BookRepository {

    Book getById(long id);

    Book createBook(Book book);

    void deleteBook(long id);

    Book updateBook(Book book);
}
