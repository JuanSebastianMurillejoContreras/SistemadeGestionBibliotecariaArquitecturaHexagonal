-- Tabla de libraries
CREATE TABLE tb_biblioteca (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       address VARCHAR(255) NOT NULL
);

-- Tabla de autores
CREATE TABLE tb_autor (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(255) NOT NULL UNIQUE
);

-- Tabla de libros
CREATE TABLE tb_libro (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      title VARCHAR(255) NOT NULL,
      isbn VARCHAR(255) NOT NULL UNIQUE,

      author_id BIGINT NOT NULL,
      library_id BIGINT NOT NULL,

      CONSTRAINT fk_libro_autor
          FOREIGN KEY (author_id)
              REFERENCES tb_autor(id)
              ON DELETE RESTRICT
              ON UPDATE CASCADE,

      CONSTRAINT fk_libro_biblioteca
          FOREIGN KEY (library_id)
              REFERENCES tb_biblioteca(id)
              ON DELETE RESTRICT
              ON UPDATE CASCADE
);

-- Tabla de usuarios
CREATE TABLE tb_usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Tabla de reserva
CREATE TABLE tb_reserva (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    date_reservation TIMESTAMP NOT NULL,
    is_active BOOLEAN NOT NULL,

    CONSTRAINT fk_reserva_usuario
        FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id),

    CONSTRAINT fk_reserva_book
        FOREIGN KEY (book_id) REFERENCES tb_libro(id)
);