-- Tabla de libros
CREATE TABLE tb_libro (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      title VARCHAR(255) NOT NULL,
      isbn VARCHAR(255) NOT NULL UNIQUE,
      author_id BIGINT NOT NULL,
      library_id BIGINT NOT NULL,
);