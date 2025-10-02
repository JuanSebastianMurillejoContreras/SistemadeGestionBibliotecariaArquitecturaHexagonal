package com.biblioteca.author_service.infraestructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataAuthorRepository extends JpaRepository<AuthorEntity, Long> {

        boolean existsByName(String name);

}
