package com.biblioteca.sistemadegestionbibliotecaria.book.controller;

import com.biblioteca.sistemadegestionbibliotecaria.book.api.BookApi;
import com.biblioteca.sistemadegestionbibliotecaria.book.dto.input.BookCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.book.dto.input.BookDTO;
import com.biblioteca.sistemadegestionbibliotecaria.book.dto.input.BookRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.book.dto.out.BookResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.testContainers.common.dto.PageDTO;
import com.biblioteca.sistemadegestionbibliotecaria.book.mapper.IBookMapper;
import com.biblioteca.sistemadegestionbibliotecaria.book.service.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController implements BookApi {

    private final IBookService bookService;
    private final IBookMapper bookMapper;

    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody @Valid BookRequestDTO bookRequestDTO){
        BookCreateDTO bookCreateDTO = bookMapper.bookRequestDTOToBookCreateDTO(bookRequestDTO);
        BookDTO bookDTO = bookService.addBook(bookCreateDTO);
        BookResponseDTO bookResponseDTO = bookMapper.bookDTOToBookResponseDTO(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
    }

    @GetMapping
    public ResponseEntity<PageDTO<BookResponseDTO>> getBookByLibraryOrAuthorOrTitle(
                                                            @PageableDefault() Pageable pageable,
                                                            @RequestParam(required = false) Long libraryId,
                                                            @RequestParam(required = false) Long authorId,
                                                            @RequestParam(required = false) String title){

        Page<BookDTO> bookDTOPage = bookService.getBookByLibraryOrAuthorOrTitle(libraryId, authorId, title, pageable);
        PageDTO<BookResponseDTO> bookResponseDTOPageDTO = bookMapper.pageBookDTOToPageDTOBookResponseDTO(bookDTOPage);
        return ResponseEntity.ok(bookResponseDTOPageDTO);
    }

}
