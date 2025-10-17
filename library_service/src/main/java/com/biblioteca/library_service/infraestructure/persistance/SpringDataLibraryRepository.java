package com.biblioteca.library_service.infraestructure.persistance;

import com.biblioteca.library_service.domain.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataLibraryRepository extends JpaRepository<LibraryEntity, Long> {

    boolean existsByName(String name);
    Library save(Library library);
    Library getLibraryById(Long id);
}
