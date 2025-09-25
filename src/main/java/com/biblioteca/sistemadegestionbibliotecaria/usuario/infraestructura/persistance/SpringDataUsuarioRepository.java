package com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity save(String username);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}
