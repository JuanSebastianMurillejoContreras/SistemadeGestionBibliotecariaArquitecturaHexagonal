package com.biblioteca.author_service.infraestructure.adapter;

import com.biblioteca.author_service.aplication.port.out.BookRepositoryPort;
import com.biblioteca.author_service.domain.exception.RestRequestException;
import com.biblioteca.author_service.domain.model.Book;
import com.biblioteca.author_service.infraestructure.controller.dto.out.BookServiceResponseDTO;
import com.biblioteca.author_service.infraestructure.controller.dto.out.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestBookRepositoryAdapter implements BookRepositoryPort {

    private final RestTemplate restTemplate;

    @Value("${book.service.url}")
    private String bookServiceUrl;

    @Override
    public PageDTO<Book> getBooksByAuthor(Long authorId, Pageable pageable) {

        if (authorId == null) {
            throw new IllegalArgumentException("El ID del autor no puede ser nulo al consultar libros.");
        }

        // Construir la URL base
        String url = String.format("%s?authorId=%d", bookServiceUrl, authorId, pageable);

        // Si la paginación está activa, añadir los parámetros
        if (pageable.isPaged()) {
            url += String.format("&page=%d&size=%d", pageable.getPageNumber(), pageable.getPageSize());
        }

        ResponseEntity<BookServiceResponseDTO> response = restTemplate.getForEntity(url, BookServiceResponseDTO.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RestRequestException("Erro al obtener los libros del autor", response.getStatusCode().value());
        }

        BookServiceResponseDTO bookServiceResponseDTO = response.getBody();

        // Convertir al dominio Book
        List<Book> books = bookServiceResponseDTO.data()
                .stream()
                .map(bookDTO -> new Book(bookDTO.title()))
                .toList();

        // Crear el PageDTO con los metadatos del servicio de libros
        return new PageDTO<>(
                books,
                bookServiceResponseDTO.currentPage(),
                bookServiceResponseDTO.totalPages(),
                bookServiceResponseDTO.totalElements(),
                bookServiceResponseDTO.pageSize()
        );
        }
}



