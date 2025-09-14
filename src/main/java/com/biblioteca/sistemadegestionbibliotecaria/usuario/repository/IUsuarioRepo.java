package com.biblioteca.sistemadegestionbibliotecaria.usuario.repository;

import com.biblioteca.sistemadegestionbibliotecaria.usuario.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepo extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByName(String name);
    boolean existsByEmail(String email);

}
