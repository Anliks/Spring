package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.repository.BookRepository;
import com.edu.ulab.app.repository.UserRepository;
import com.edu.ulab.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    UserRepository userRepository;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = new Book();
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setPageCount(bookDto.getPageCount());
        book.setUserId(bookDto.getUserId());
        bookDto.setId(bookRepository.createBook(book).getId());
        return bookDto;
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        if (bookDto.getId() == null) {
            throw new NotFoundException("т.к нет бд вводите id книг!");
        }
        Book book = bookRepository.getById(bookDto.getId());
        book.setPageCount(bookDto.getPageCount());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setUserId(bookDto.getUserId());
        bookDto.setId(bookRepository.updateBook(book).getId());
        return bookDto;
    }

    @Override
    public BookDto getBookById(Long id) {
        BookDto bookDto = new BookDto();
        Book book = bookRepository.getById(id);
        bookDto.setId(book.getId());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setUserId(book.getUserId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPageCount(book.getPageCount());
        return bookDto;
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteBook(id);
    }

}
