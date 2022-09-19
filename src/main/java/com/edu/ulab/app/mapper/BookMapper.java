package com.edu.ulab.app.mapper;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.web.request.BookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "id", target = "id")
    BookDto bookRequestToBookDto(BookRequest bookRequest);

    BookRequest bookDtoToBookRequest(BookDto bookDto);
}
