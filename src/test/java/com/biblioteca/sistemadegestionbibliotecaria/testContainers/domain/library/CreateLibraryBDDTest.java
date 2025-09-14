package com.biblioteca.sistemadegestionbibliotecaria.testContainers.domain.library;

import com.biblioteca.sistemadegestionbibliotecaria.testContainers.common.AbstractIntegrationTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class CreateLibraryBDDTest extends AbstractIntegrationTest {

    @Test
    void registrarBibliotecaConDatosValidos() {
        // Given que el administrador desea agregar una nueva biblioteca
        var requestBody = """
            {
              "name": "Biblioteca Central",
              "address": "Calle 123"
            }
            """;

        // When envía una solicitud con el nombre y dirección de la biblioteca
        var response =
                given()
                        .port(port)
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .when()
                        .post("/api/v1/libraries");

        // Then la biblioteca se registra correctamente
        // And recibe una respuesta con los datos de la nueva biblioteca
        response.then()
                .statusCode(201)
                .body("name", equalTo("Biblioteca Central"));
    }

}
