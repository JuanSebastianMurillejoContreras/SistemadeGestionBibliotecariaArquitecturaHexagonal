package com.biblioteca.sistemadegestionbibliotecaria.testUnitarios.service.author;


import com.biblioteca.sistemadegestionbibliotecaria.author.aplication.port.out.AuthorRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.author.aplication.service.AuthorService;
import com.biblioteca.sistemadegestionbibliotecaria.author.domain.model.Author;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.mapper.IAuthorMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
        Author input = new Author(null, "Gabriel García Márquez", null);
        Author savedAuthor = new Author(1L, "Gabriel García Márquez", null);

        // When
        Mockito.when(authorRepo.existsByName("Gabriel García Márquez")).thenReturn(false);
        Mockito.when(authorRepo.save(Mockito.any(Author.class))).thenReturn(savedAuthor);

        Author result = authorService.createAuthor(input);

        // Then
        assertEquals(savedAuthor, result);
        Mockito.verify(authorRepo).save(input);
    }

    @Test
    void givenAuthorWithNullNameWhenCreateAuthorThenThrowException() {
        Author input = new Author(null, null, null);
        assertThrows(IllegalArgumentException.class, () -> authorService.createAuthor(input));
    }

    @Test
    void givenAuthorWithEmptyNameWhenCreateAuthorThenThrowException() {
        Author input = new Author(null, "", null);
        assertThrows(IllegalArgumentException.class, () -> authorService.createAuthor(input));
    }
}
