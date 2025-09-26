package com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller;

import com.biblioteca.sistemadegestionbibliotecaria.author.aplication.port.in.CreateAuthorUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.author.aplication.port.in.GetAuthorUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller.api.AuthorApi;
import com.biblioteca.sistemadegestionbibliotecaria.author.domain.model.Author;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller.dto.input.AuthorDTO;
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

        AuthorDTO authorDTO = authorMapper.authorRequestDTOToAuthorDTO(request);

        Author domainAuthor = authorMapper.authorDTOToAuthor(authorDTO);
        Author savedAuthor = createAuthorUseCase.createAuthor(domainAuthor);

        AuthorDTO authorDTOToAuthor = authorMapper.authorToAuthorDTO(savedAuthor);

        AuthorResponseDTO authorResponseDTO = authorMapper.authorDTOToAuthorResponseDTO(authorDTOToAuthor);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getById(@PathVariable Long id) {

        Author found = getAuthorUseCase.getAuthorById(id);

        AuthorDTO dto = authorMapper.authorToAuthorDTO(found);

        AuthorResponseDTO response = authorMapper.authorDTOToAuthorResponseDTO(dto);

        return ResponseEntity.ok(response);
    }


}
