package com.biblioteca.library_service.testUnitarios.service.author;


import com.biblioteca.library_service.author.aplication.port.out.AuthorRepositoryPort;
import com.biblioteca.library_service.author.aplication.service.AuthorService;
import com.biblioteca.library_service.author.domain.exception.AuthorErrorMessage;
import com.biblioteca.library_service.author.domain.exception.AuthorException;
import com.biblioteca.library_service.author.domain.model.Author;
import com.biblioteca.library_service.author.infraestructure.controller.dto.input.AuthorCreateCommand;
import com.biblioteca.library_service.author.infraestructure.mapper.IAuthorMapper;
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
public class AuthorServiceImplTest {

    @InjectMocks
    private AuthorService authorService;
    @Mock
    private AuthorRepositoryPort authorRepo;
    @Spy
    private IAuthorMapper authorMapper = Mappers.getMapper(IAuthorMapper.class);

    @Test
    void givenAuthorWhenCreateAuthorThenReturnSavedAuthor() {
        // Given
        AuthorCreateCommand input = new AuthorCreateCommand("Gabriel García Márquez");
        Author savedAuthor = new Author(1L, "Gabriel García Márquez");

        // When
        Mockito.when(authorRepo.existsByName("Gabriel García Márquez")).thenReturn(false);
        Mockito.when(authorRepo.save(Mockito.any(Author.class))).thenReturn(savedAuthor);


        Author result = authorService.createAuthor(input);
        Author author = new Author(null, "Gabriel García Márquez");

        // Then
        assertEquals(savedAuthor, result);
        Mockito.verify(authorRepo).save(author);
    }

    @Test
    void givenAuthorWithNullNameWhenCreateAuthorThenThrowException() {
        AuthorCreateCommand input = new AuthorCreateCommand( null);
        assertThrows(IllegalArgumentException.class, () -> authorService.createAuthor(input));
    }

    @Test
    void givenAuthorWithEmptyNameWhenCreateAuthorThenThrowException() {
        AuthorCreateCommand input = new AuthorCreateCommand( "");
        assertThrows(IllegalArgumentException.class, () -> authorService.createAuthor(input));
    }

    @Test
    void givenAuthorWithExistingNameWhenCreateAuthorThenThrowAuthorException() {
        AuthorCreateCommand input = new AuthorCreateCommand( "Gabriel García Márquez");

        Mockito.when(authorRepo.existsByName(input.name())).thenReturn(true);

        AuthorException  ex = assertThrows(AuthorException.class, () -> authorService.createAuthor(input));

        assertTrue(ex.getMessage().contains(AuthorErrorMessage.AUTOR_ALREADY_REGISTERED));
    }

    @Test
    void givenExistingAuthorWhenGetAuthorByIdThenReturnAuthor() {
        // given
        Long id = 1L;
        Author expected = new Author(id, "Gabriel García Márquez");
        Mockito.when(authorRepo.getAuthorById(id)).thenReturn(expected);

        // when
        Author result = authorService.getAuthorById(id);

        // then
        assertNotNull(result);
        assertEquals(expected, result);
    }

}
