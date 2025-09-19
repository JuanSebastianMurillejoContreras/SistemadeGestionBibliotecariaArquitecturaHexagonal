package com.biblioteca.sistemadegestionbibliotecaria.libraries.infraestructure.persistance;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.domain.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataLibraryRepository extends JpaRepository<LibraryEntity, Long> {

    boolean existsByName(String name);
    Library save(Library library);
}
