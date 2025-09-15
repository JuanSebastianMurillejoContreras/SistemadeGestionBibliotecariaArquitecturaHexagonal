package com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.mapper;

import com.biblioteca.sistemadegestionbibliotecaria.book.domain.model.Book;
import com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.controller.dto.input.BookRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.controller.dto.out.BookResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.testContainers.common.dto.PageDTO;
import com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.persistance.BookEntity;
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
    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "libraryId", target = "library.id")
    BookEntity toEntity(Book book);

    // Entity → Domain
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "library.id", target = "libraryId")
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

