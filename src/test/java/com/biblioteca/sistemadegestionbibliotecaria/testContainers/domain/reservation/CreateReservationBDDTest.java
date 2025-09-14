package com.biblioteca.sistemadegestionbibliotecaria.testContainers.domain.reservation;

import com.biblioteca.sistemadegestionbibliotecaria.testContainers.common.AbstractIntegrationTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class CreateReservationBDDTest extends AbstractIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("DELETE FROM tb_reserva");
        jdbcTemplate.update("DELETE FROM tb_libro");
        jdbcTemplate.update("DELETE FROM tb_usuario");
        jdbcTemplate.update("DELETE FROM tb_autor");
        jdbcTemplate.update("DELETE FROM tb_biblioteca");

        jdbcTemplate.update("INSERT INTO tb_autor (id, name) VALUES (1, 'Gabriel García Márquez')");
        jdbcTemplate.update("INSERT INTO tb_biblioteca (id, name, address) VALUES (1, 'Biblioteca Central', 'Calle 123')");
        jdbcTemplate.update("INSERT INTO tb_libro (id, title, isbn, author_id, library_id) VALUES (1, 'El túnel', '978-1000000001', 1, 1)");
        jdbcTemplate.update("INSERT INTO tb_usuario (id, name, email) VALUES (1, 'juan', 'juan@example.com')");
    }


    @Test
    public void registrarReservacionConDatosValidos () {

        // Given que un administrador desea registrar una reserva
        String requestBody = """
                    {
                    "usuarioId": 1,
                    "bookId": 1,
                    "isActive": true
                    }
                """;

        var response =
            given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/v1/reservations");

        response.then()
                .statusCode(200)
                .body("usuarioId", equalTo(1))
                .body("bookId", equalTo(1))
                .body("isActive", equalTo(true));
    }


    @Test
    public void registrarReservacionConUsuarioIdNulNoValidos () {

        // Given que un administrador desea registrar una reserva
        String requestBody = """
                    {
                    "usuarioId": null,
                    "bookId": 1,
                    "isActive": true
                    }
                """;

        var response =
                given()
                        .port(port)
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .when()
                        .post("/api/v1/reservations");

        response.then()
                .statusCode(500);
    }

    @Test
    public void registrarReservacionConBookIdIdNulNoValidos () {

        // Given que un administrador desea registrar una reserva
        String requestBody = """
                    {
                    "usuarioId": 1,
                    "bookId": null,
                    "isActive": true
                    }
                """;

        var response =
                given()
                        .port(port)
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .when()
                        .post("/api/v1/reservations");

        response.then()
                .statusCode(500);
    }

    @Test
    public void registrarReservacionConIsActiveNullNoValidos () {

        // Given que un administrador desea registrar una reserva
        String requestBody = """
                    {
                    "usuarioId": 1,
                    "bookId": 1,
                    "isActive": null
                    }
                """;

        var response =
                given()
                        .port(port)
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .when()
                        .post("/api/v1/reservations");

        response.then()
                .statusCode(500);
    }

    @Test
    public void registrarReservacionConIsActiveTrueNoValidos () {

        // Given que un administrador desea registrar una reserva
        String requestBody = """
                    {
                    "usuarioId": 1,
                    "bookId": 1,
                    "isActive": true
                    }
                """;

        var response =
                given()
                        .port(port)
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .when()
                        .post("/api/v1/reservations");

        response.then()
                .statusCode(200);
    }

}
