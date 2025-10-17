package com.biblioteca.library_service.author.infraestructure.controller;

import com.biblioteca.library_service.author.aplication.port.in.CreateAuthorUseCase;
import com.biblioteca.library_service.author.aplication.port.in.GetAuthorUseCase;
import com.biblioteca.library_service.author.infraestructure.controller.api.AuthorApi;
import com.biblioteca.library_service.author.domain.model.Author;
import com.biblioteca.library_service.author.infraestructure.controller.dto.input.AuthorCreateCommand;
import com.biblioteca.library_service.author.infraestructure.controller.dto.input.AuthorRequestDTO;
import com.biblioteca.library_service.author.infraestructure.controller.dto.out.AuthorResponseDTO;
import com.biblioteca.library_service.author.infraestructure.mapper.IAuthorMapper;
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

        AuthorCreateCommand authorCreateCommand = authorMapper.authorRequestDTOToAuthorDTO(request);

        Author savedAuthor = createAuthorUseCase.createAuthor(authorCreateCommand);

        AuthorCreateCommand authorDTOToAuthor = authorMapper.authorToAuthorDTO(savedAuthor);

        AuthorResponseDTO authorResponseDTO = authorMapper.authorDTOToAuthorResponseDTO(authorDTOToAuthor);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getById(@PathVariable Long id) {

        Author found = getAuthorUseCase.getAuthorById(id);

        AuthorCreateCommand dto = authorMapper.authorToAuthorDTO(found);

        AuthorResponseDTO response = authorMapper.authorDTOToAuthorResponseDTO(dto);

        return ResponseEntity.ok(response);
    }


}
