package com.biblioteca.author_service.infraestructure.adapter;

import com.biblioteca.author_service.infraestructure.controller.dto.out.BookDTO;
import com.biblioteca.author_service.infraestructure.persistance.RestBookRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookAdapter extends RestBookRepositoryAdapter {

    private final RestTemplate restTemplate;

    @Value("${book.service.url}")
    private String bookServiceUrl;

    @Override
    public List<BookDTO> getBooksByAuthor(Long authorId) {
        String url = String.format("%s/author/%d", bookServiceUrl, authorId);

        ResponseEntity<List<BookDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }
}
