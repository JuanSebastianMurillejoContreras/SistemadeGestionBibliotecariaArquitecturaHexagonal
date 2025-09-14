package com.biblioteca.sistemadegestionbibliotecaria.testUnitarios.service.library;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.input.LibraryCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.input.LibraryDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.entity.LibraryEntity;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.mapper.ILibraryMapper;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.repo.ILibraryRepo;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.service.impl.LibraryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceImplTest {

    @InjectMocks
    private LibraryServiceImpl libraryService;
    @Mock
    private ILibraryRepo libraryRepo;
    @Spy
    private ILibraryMapper libraryMapper = Mappers.getMapper(ILibraryMapper.class);

    @Test
    void givenLibraryRequestDTOWhencreateLibraryThenReturnLibraryResponseDTO() {
        //Given
        LibraryCreateDTO input = new LibraryCreateDTO("Alejandria","Grecia");

        //When
        LibraryEntity libraryEntity = new LibraryEntity();
        libraryEntity.setName("Alejandria");
        libraryEntity.setAddress("Grecia");

        LibraryEntity libraryEntity2 = new LibraryEntity();
        libraryEntity2.setId(1L);
        libraryEntity2.setName("Alejandria");
        libraryEntity2.setAddress("Grecia");

        Mockito.when(libraryRepo.save(libraryEntity)).thenReturn(libraryEntity2);

        LibraryDTO outPutEsperado = new LibraryDTO("Alejandria","Grecia");
        LibraryDTO resultado = libraryService.createLibrary(input);

        //Then
        assertEquals(outPutEsperado, resultado);

    }

    @Test
    void givenLibraryRequestDTOWhencreateLibraryWithNullNameThenThrowException() {
        // given
        LibraryCreateDTO input = new LibraryCreateDTO(null, "Calle 123");

        // then
        assertThrows(IllegalArgumentException.class, () -> libraryService.createLibrary(input));
    }

    @Test
    void givenLibraryRequestDTOWhencreateLibraryWithEmptyNameThenThrowException() {
        // given
        LibraryCreateDTO input = new LibraryCreateDTO("", "Calle 123");

        // then
        assertThrows(IllegalArgumentException.class, () -> libraryService.createLibrary(input));
    }

    @Test
    void givenLibraryRequestDTOWhencreateLibraryWithNullAddressThenThrowException() {
        // given
        LibraryCreateDTO input = new LibraryCreateDTO("Biblioteca Nacional", null);

        // then
        assertThrows(IllegalArgumentException.class, () -> libraryService.createLibrary(input));
    }

    @Test
    void givenLibraryRequestDTOWhencreateLibraryWithEmptyAddressThenThrowException() {
        // given
        LibraryCreateDTO input = new LibraryCreateDTO("Biblioteca Nacional", "");

        // then
        assertThrows(IllegalArgumentException.class, () -> libraryService.createLibrary(input));
    }


}
