package com.biblioteca.author_service.aplication.port.out;

import com.biblioteca.author_service.infraestructure.controller.dto.out.BookServiceResponseDTO;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryPort {

    BookServiceResponseDTO getBooksByAuthor(Long authorId, Pageable pageable);

}
