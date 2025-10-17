package com.biblioteca.library_service.testUnitarios.service.book;

import com.biblioteca.library_service.author.domain.exception.AuthorErrorMessage;
import com.biblioteca.library_service.book.aplication.port.out.BookRepositoryPort;
import com.biblioteca.library_service.book.aplication.service.BookService;
import com.biblioteca.library_service.book.domain.exception.BookErrorMessage;
import com.biblioteca.library_service.book.domain.exception.BookException;
import com.biblioteca.library_service.book.domain.model.Book;
import com.biblioteca.library_service.book.infraestructure.mapper.IBookMapper;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepositoryPort repositoryPort;
    @Spy
    private IBookMapper bookMapper = Mappers.getMapper(IBookMapper.class);

    @Test
    void givenBookRequestDTOWhenAddBookThenReturnBookResponseDTO() {
        // given
        Book input = new Book(1L, "Libro", "9780306406157", 1L, 1L);
        Book expected = new Book(1L, "Libro", "9780306406157", 1L, 1L);

        // when
        Mockito.when(repositoryPort.save(Mockito.any(Book.class))).thenReturn(expected);

        // act
        Book result = bookService.createBook(input);

        // then
        assertEquals(expected, result);
    }

    @Test
    void givenBookRequestDTOWhenAddBookWithNUllISBNThenReturnBookResponseDTO3() {
        //given:
        Book input = new Book(1L, "Libro2",null,1L,1L);
        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(input));
    }

    @Test
    void givenBookRequestDTOWhenAddBookWithEmptyISBNThenReturnBookResponseDTO3() {
        //given:
        Book input = new Book(1L, "Libro2","",1L,1L);
        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(input));
    }

    @Test
    void givenBookRequestDTOWhenAddBookWithRandomCaracterISBNThenReturnBookResponseDTO3() {
        //given:
        Book input = new Book(1L, "Libro2","12sgh2",1L,1L);
        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(input));
    }


    @Test
    void givenBookRequestDTOWhenAddBookWithNUllTitleThenReturnBookResponseDTO3() {
        //given:
        Book input = new Book(1L, null,"9780306406157",1L,1L);
        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(input));
    }

    @Test
    void givenBookRequestDTOWhenAddBookWithEmptyTitleThenReturnBookResponseDTO3() {
        //given:
        Book input = new Book(1L, "","9780306406157",1L,1L);
        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(input));
    }

    @Test
    void givenBookRequestDTOWhenAddBookWithNUllAuthorIdThenReturnBookResponseDTO3() {
        //given:
        Book input = new Book(1L, null,"9780306406157",null,1L);
        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(input));
    }

    @Test
    void givenBookRequestDTOWhenAddBookWithNULLLibraryIdThenReturnBookResponseDTO3() {
        //given:
        Book input = new Book(1L, "","9780306406157", 1L,null);
        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(input));
    }

    @Test
    void givenBookRequestDTOWhenAddBookWithIDLibraryMinorThatZeroThenReturnBookResponseDTO3() {
        //given:
        Book input = new Book(1L, "EL Manjar","9780306406157", 1L,-1L);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> bookService.createBook(input)
        );

        assertEquals("El libraryId debe ser mayor que 0", ex.getMessage());
    }

    @Test
    void givenBookRequestDTOWhenAddBookWithIDAuthorMinorThatZeroThenReturnBookResponseDTO3() {
        //given:
        Book input = new Book(1L, "Libro2","9780306406157", -1L,1L);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> bookService.createBook(input)
        );

        assertEquals("El authorId debe ser mayor que 0", ex.getMessage());
    }

    @Test
    void givenBookRequestDTOWithExistingNameWhenCreateAuthorThenThrowAuthorException(){

        Book input = new Book(1L, "Libro2","9780306406157", 1L,1L);

        Mockito.when(repositoryPort.existsByTitle(input.title())).thenReturn(true);

        BookException ex = assertThrows(BookException.class, () -> bookService.createBook(input));

        assertTrue(ex.getMessage().contains(BookErrorMessage.BOOK_NAME_ALREADY_REGISTERED));

    }

    @Test
    void givenBookRequestDTOWithExistingISBNWhenCreateAuthorThenThrowAuthorException(){

        Book input = new Book(1L, "Libro2","9780306406157", 1L,1L);

        Mockito.when(repositoryPort.existsByIsbn(input.isbn())).thenReturn(true);

        BookException ex = assertThrows(BookException.class, () -> bookService.createBook(input));

        assertTrue(ex.getMessage().contains(BookErrorMessage.BOOK_ISBN_ALREADY_REGISTERED));

    }

    @Test
    void whenSearchBooksThenReturnPageFromRepository() {
        // given
        Long libraryId = 1L;
        Long authorId = 2L;
        String title = "Spring";
        Pageable pageable = PageRequest.of(0, 10);

        Page<Book> expectedPage = new PageImpl<>(List.of(new Book(1L, "Spring Boot", "9780306406157", authorId, libraryId)));

        Mockito.when(repositoryPort.findByLibraryIdOrAuthorIdOrTitleContainingIgnoreCase(libraryId, authorId, title, pageable))
                .thenReturn(expectedPage);

        // when
        Page<Book> result = bookService.buscarLibroPorIdAutorOTituloIgnorandoMasyusculaOMinuscula(libraryId, authorId, title, pageable);

        // then
        assertEquals(expectedPage, result);
        Mockito.verify(repositoryPort).findByLibraryIdOrAuthorIdOrTitleContainingIgnoreCase(libraryId, authorId, title, pageable);
    }
}

