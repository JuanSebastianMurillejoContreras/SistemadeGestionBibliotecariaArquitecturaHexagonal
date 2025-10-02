package com.biblioteca.author_service_mejorado.aplication.port.in;


import com.biblioteca.author_service_mejorado.domain.model.Author;

public interface GetAuthorUseCase {

    Author getAuthorById(Long id);

}
