package com.biblioteca.sistemadegestionbibliotecaria.testUnitarios.service.book;

import com.biblioteca.sistemadegestionbibliotecaria.book.aplication.port.out.BookRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.book.aplication.service.BookService;
import com.biblioteca.sistemadegestionbibliotecaria.book.domain.model.Book;
import com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.mapper.IBookMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


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
}