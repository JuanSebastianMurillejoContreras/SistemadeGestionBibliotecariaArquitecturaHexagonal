package com.biblioteca.sistemadegestionbibliotecaria.libraries.repo;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.entity.LibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILibraryRepo extends JpaRepository<LibraryEntity, Long> {

    boolean existsByName(String name);

}
