package com.biblioteca.book_service.infraestructure.controller;

import com.biblioteca.sistemadegestionbibliotecaria.book.aplication.port.in.CreateBookUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.book.aplication.port.in.GetBookUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.book.domain.model.Book;
import com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.controller.api.BookApi;
import com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.controller.dto.input.BookRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.controller.dto.out.BookResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.mapper.IBookMapper;
import com.biblioteca.sistemadegestionbibliotecaria.testContainers.common.dto.PageDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController implements BookApi {

    private final CreateBookUseCase createBookUseCase;
    private final GetBookUseCase getBookUseCase;
    private final IBookMapper bookMapper;

    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody @Valid BookRequestDTO bookRequestDTO){
        Book domainBook = bookMapper.BookRequestDTOtoDomain(bookRequestDTO);
        Book saveBook = createBookUseCase.createBook(domainBook);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookMapper.toResponseDTO(saveBook));
    }

    @GetMapping
    public ResponseEntity<PageDTO<BookResponseDTO>> getBookByLibraryOrAuthorOrTitle(
                                                            @PageableDefault() Pageable pageable,
                                                            @RequestParam(required = false) Long libraryId,
                                                            @RequestParam(required = false) Long authorId,
                                                            @RequestParam(required = false) String title){

        Page<Book> bookDTOPage = getBookUseCase.buscarLibroPorIdAutorOTituloIgnorandoMasyusculaOMinuscula(libraryId, authorId, title, pageable);
        PageDTO<BookResponseDTO> bookResponseDTOPageDTO = bookMapper.pageBookToPageDTO(bookDTOPage);
        return ResponseEntity.ok(bookResponseDTOPageDTO);
    }

}
