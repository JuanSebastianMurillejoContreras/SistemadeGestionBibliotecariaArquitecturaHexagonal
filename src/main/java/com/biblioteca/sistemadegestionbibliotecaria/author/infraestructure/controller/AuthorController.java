package com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller;

import com.biblioteca.sistemadegestionbibliotecaria.author.aplication.port.in.CreateAuthorUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.author.aplication.port.in.GetAuthorUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller.api.AuthorApi;
import com.biblioteca.sistemadegestionbibliotecaria.author.domain.model.Author;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller.dto.input.AuthorRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller.dto.out.AuthorResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.mapper.IAuthorMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController implements AuthorApi {


    private final CreateAuthorUseCase createAuthorUseCase;
    private final GetAuthorUseCase getAuthorUseCase;
    private final IAuthorMapper authorMapper;

    @Override
    @PostMapping
    public ResponseEntity<AuthorResponseDTO> addAuthor(@Valid @RequestBody AuthorRequestDTO request) {
        Author domainAuthor = authorMapper.toDomain(request);
        Author savedAuthor = createAuthorUseCase.createAuthor(domainAuthor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorMapper.toResponseDTO(savedAuthor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getByID(@PathVariable Long id) {
        Author foundAuthor = getAuthorUseCase.getAuthorById(id);
        return ResponseEntity.ok(authorMapper.toResponseDTO(foundAuthor));
    }

}
