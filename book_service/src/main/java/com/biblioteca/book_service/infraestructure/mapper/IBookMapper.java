package com.biblioteca.book_service.infraestructure.mapper;

import com.biblioteca.book_service.domain.model.Book;
import com.biblioteca.book_service.infraestructure.controller.dto.input.BookRequestDTO;
import com.biblioteca.book_service.infraestructure.controller.dto.out.BookResponseDTO;
import com.biblioteca.book_service.infraestructure.controller.dto.out.PageDTO;
import com.biblioteca.book_service.infraestructure.persistance.BookEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface IBookMapper {

    // Request → Domain
    Book BookRequestDTOtoDomain(BookRequestDTO bookRequestDTO);

    // Domain → Response
    BookResponseDTO toResponseDTO(Book book);

    // Domain → Entity
    BookEntity toEntity(Book book);

    // Entity → Domain
    Book bookEntitytoDomain(BookEntity bookEntity);

    // Page<BookEntity> → Page<Book>
    default Page<Book> toDomainPage(Page<BookEntity> entities) {
        return entities.map(this::bookEntitytoDomain);
    }

    // Page<Book> → PageDTO<BookResponseDTO>
    default PageDTO<BookResponseDTO> pageBookToPageDTO(Page<Book> page) {
        return new PageDTO<>(
                page.getContent().stream()
                        .map(this::toResponseDTO)
                        .toList(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize()
        );
    }
}

