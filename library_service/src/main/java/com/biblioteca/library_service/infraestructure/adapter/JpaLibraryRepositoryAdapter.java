package com.biblioteca.library_service.infraestructure.adapter;

import com.biblioteca.library_service.aplication.port.out.LibraryRepositoryPort;
import com.biblioteca.library_service.domain.model.Library;
import com.biblioteca.library_service.infraestructure.mapper.ILibraryMapper;
import com.biblioteca.library_service.infraestructure.persistance.LibraryEntity;
import com.biblioteca.library_service.infraestructure.persistance.SpringDataLibraryRepository;
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
    public Library getLibraryById(Long id) {
        return libraryRepository.getLibraryById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return libraryRepository.existsByName(name);
    }
}
