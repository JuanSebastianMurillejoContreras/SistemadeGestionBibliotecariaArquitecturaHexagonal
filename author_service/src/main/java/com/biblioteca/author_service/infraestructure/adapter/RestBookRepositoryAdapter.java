package com.biblioteca.author_service.infraestructure.adapter;

import com.biblioteca.author_service.aplication.port.out.BookRepositoryPort;
import com.biblioteca.author_service.infraestructure.controller.dto.out.BookServiceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestBookRepositoryAdapter implements BookRepositoryPort {

    private final RestTemplate restTemplate;

    @Value("${book.service.url}")
    private String bookServiceUrl;

    @Override
    public BookServiceResponseDTO getBooksByAuthor(Long authorId) {
        if (authorId == null) {
            throw new IllegalArgumentException("El ID del autor no puede ser nulo al consultar libros.");
        }

        String url = String.format("%s?authorId=%d", bookServiceUrl, authorId);
        System.out.println("📘 Solicitando libros del autor con ID = " + authorId + " → " + url);

        ResponseEntity<BookServiceResponseDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }
}
