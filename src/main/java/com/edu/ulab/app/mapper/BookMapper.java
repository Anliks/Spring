package com.edu.ulab.app.mapper;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.web.request.BookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "id", target = "id")
    BookDto bookRequestToBookDto(BookRequest bookRequest);

    BookDto bookToBookDto(Book book);

    Book bookDtoBook(BookDto bookDto);
}
