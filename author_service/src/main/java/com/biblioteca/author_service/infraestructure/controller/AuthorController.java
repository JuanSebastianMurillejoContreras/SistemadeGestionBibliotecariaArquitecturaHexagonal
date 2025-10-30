package com.biblioteca.author_service.infraestructure.controller;


import com.biblioteca.author_service.aplication.port.in.CreateAuthorUseCase;
import com.biblioteca.author_service.aplication.port.in.GetAuthorUseCase;
import com.biblioteca.author_service.domain.model.Author;
import com.biblioteca.author_service.infraestructure.controller.api.AuthorApi;
import com.biblioteca.author_service.infraestructure.controller.dto.input.AuthorCreateCommand;
import com.biblioteca.author_service.infraestructure.controller.dto.input.AuthorGetCommand;
import com.biblioteca.author_service.infraestructure.controller.dto.input.AuthorRequestDTO;
import com.biblioteca.author_service.infraestructure.controller.dto.out.AuthorResponseDTO;
import com.biblioteca.author_service.infraestructure.controller.dto.out.AuthorResponseWithBooksDTO;
import com.biblioteca.author_service.infraestructure.mapper.IAuthorMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<AuthorResponseWithBooksDTO> getById(@PathVariable Long id,
                                                              @RequestParam Boolean books,
                                                              @PageableDefault Pageable pageable) {

        AuthorGetCommand authorGetCommand = new AuthorGetCommand(id, books, pageable);

        Author found = getAuthorUseCase.getAuthorById(authorGetCommand);

        AuthorResponseWithBooksDTO response2 = authorMapper.authorToAuthorResponseWithBooksDTO(found);

        return ResponseEntity.ok(response2);
    }
}