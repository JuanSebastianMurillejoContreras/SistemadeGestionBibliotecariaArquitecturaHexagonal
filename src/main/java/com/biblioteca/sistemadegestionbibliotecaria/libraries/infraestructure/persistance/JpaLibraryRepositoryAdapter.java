package com.biblioteca.sistemadegestionbibliotecaria.libraries.infraestructure.persistance;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.aplication.port.out.LibraryRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.domain.model.Library;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.infraestructure.mapper.ILibraryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaLibraryRepositoryAdapter implements LibraryRepositoryPort {

    private final SpringDataLibraryRepository libraryRepository;
    private final ILibraryMapper libraryMapper;

    @Override
    public Library createLibrary(Library library) {
        LibraryEntity libraryEntity = libraryMapper.toEntity(library);
        return libraryMapper.toDomain(libraryRepository.save(libraryEntity));
    }

    @Override
    public boolean existsByName(String name) {
        return libraryRepository.existsByName(name);
    }
}
