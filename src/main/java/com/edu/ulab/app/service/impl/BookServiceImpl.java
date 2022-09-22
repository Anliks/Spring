package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.repository.AbstractRepository;
import com.edu.ulab.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    AbstractRepository abstractRepository;
    BookMapper bookMapper;


    @Autowired
    public BookServiceImpl(AbstractRepository abstractRepository, BookMapper bookMapper) {
        this.abstractRepository = abstractRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book;
        bookDto.setId(abstractRepository.getCurrentId());
        book = abstractRepository.create(bookMapper.bookDtoBook(bookDto));
        return bookMapper.bookToBookDto(book);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        BookDto isHolderBook = getBookById(bookDto.getId());
        isHolderBook.setTitle(bookDto.getTitle());
        isHolderBook.setAuthor(bookDto.getAuthor());
        isHolderBook.setPageCount(bookDto.getPageCount());
        return isHolderBook;
    }

    @Override
    public BookDto getBookById(Long id) {
        Object object = abstractRepository.getById(id);
        if (object instanceof Book) {
            return bookMapper.bookToBookDto((Book) object);
        }
        return null;
    }

    @Override
    public void deleteBookById(Long id) {
        Object object = abstractRepository.getById(id);
        if (object instanceof Book) {
            abstractRepository.delete(id);
        }
    }

    @Override
    public List<BookDto> listBooksFromUser(Long id) {
        return abstractRepository.getBooks().stream().filter(book -> book.getUserId().equals(id)).map(bookMapper::bookToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBooksFromUser(Long userId) {
        listBooksFromUser(userId).stream().map(BookDto::getId).peek(id -> log.info("Deleted book/s  ids {}", id))
                .forEach(abstractRepository::delete);
        log.info("Deleted {}");
    }
}
