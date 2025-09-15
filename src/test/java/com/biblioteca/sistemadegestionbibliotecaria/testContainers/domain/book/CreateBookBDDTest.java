package com.biblioteca.sistemadegestionbibliotecaria.testContainers.domain.book;

import com.biblioteca.sistemadegestionbibliotecaria.author.aplication.port.out.AuthorRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.author.domain.model.Author;
import com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.persistance.SpringDataBookRepository;
import com.biblioteca.sistemadegestionbibliotecaria.testContainers.common.AbstractIntegrationTest;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.entity.LibraryEntity;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.repo.ILibraryRepo;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateBookBDDTest extends AbstractIntegrationTest {

    @Autowired
    private AuthorRepositoryPort authorRepo;

    @Autowired
    private ILibraryRepo libraryRepo;

    @Autowired
    private SpringDataBookRepository bookRepo;

    @Test
    void registrarLibroConAutorYBibliotecaExistentes() {

        // Given: que el autor y la biblioteca ya existen en el sistema
       Author author = authorRepo.save(
                new Author(null, "Gabriel García Márquez")
        );
        LibraryEntity library = libraryRepo.save(
                new LibraryEntity(null, "Biblioteca Nacional", "Colombia", new ArrayList<>())
        );

        // When: se envía una solicitud con el título del libro, ID del autor e ID de la biblioteca
        String requestBody = """
                {
                  "title": "Cien Años de Soledad",
                  "isbn": "9780307474728",
                  "authorId": %d,
                  "libraryId": %d
                }
                """.formatted(author.id(), library.getId());

        var response =
        given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/v1/books");
                // Then: el libro se registra correctamente

        response.then()
                .statusCode(201)
                .body("title", equalTo("Cien Años de Soledad"))
                .body("isbn", equalTo("9780307474728"))
                .body("authorId", equalTo(author.id().intValue()))
                .body("libraryId", equalTo(library.getId().intValue()));

        // And: se verifica que el libro existe en la base de datos
        assertTrue(
                bookRepo.existsByIsbn("9780307474728"),
                "El libro debería existir en la base de datos"
        );
    }
}
