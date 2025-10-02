package com.biblioteca.author_service_mejorado.infraestructure.adapter;

import com.biblioteca.author_service_mejorado.aplication.port.out.BookPort;
import com.biblioteca.author_service_mejorado.infraestructure.controller.dto.out.BookDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class BookAdapter implements BookPort {


    private final RestTemplate restTemplate;


    public BookAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<BookDTO> getBooksByAuthor(Long authorId) {
        String url = "http://book-service/api/books?authorId=" + authorId;
        return Arrays.asList(restTemplate.getForObject(url, BookDTO[].class));
    }
}
