package com.biblioteca.library_service.testUnitarios.service.library;

import com.biblioteca.library_service.book.domain.model.Book;
import com.biblioteca.library_service.aplication.port.out.LibraryRepositoryPort;
import com.biblioteca.library_service.aplication.service.LibraryService;
import com.biblioteca.library_service.domain.exception.LibraryErrorMessage;
import com.biblioteca.library_service.domain.exception.LibraryException;
import com.biblioteca.library_service.domain.model.Library;
import com.biblioteca.library_service.infraestructure.mapper.ILibraryMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceImplTest {

    @InjectMocks
    private LibraryService libraryService;
    @Mock
    private LibraryRepositoryPort libraryRepositoryPort;
    @Spy
    private ILibraryMapper libraryMapper = Mappers.getMapper(ILibraryMapper.class);

    List<Book> lstBooks = List.of(
            new Book(1L, "El túnel", "978-1000000001", 1L, 1L),
            new Book(1L, "Los detectives salvajes", "978-1000000004", 1L, 1L)
    );

    @Test
    void givenLibraryRequestDTOWhencreateLibraryThenReturnLibraryResponseDTO() {

        //Given
        Library input = new Library(1l,"Alejandria","Grecia", lstBooks);

        //When
        Library Library = new Library(1l,"Alejandria","Grecia", lstBooks);
        
        Library Library2 = new Library(1l,"Alejandria","Grecia", lstBooks);


        Mockito.when(libraryRepositoryPort.createLibrary(Library)).thenReturn(Library2);

        Library outPutEsperado = new Library(1L,"Alejandria","Grecia", lstBooks);
        Library resultado = libraryService.createLibrary(input);

        //Then
        assertEquals(outPutEsperado, resultado);

    }

    @Test
    void givenLibraryRequestDTOWhencreateLibraryWithNullNameThenThrowException() {
        // given
        Library input = new Library(1L, null, "Grecia", lstBooks);

        // then
        assertThrows(IllegalArgumentException.class, () -> libraryService.createLibrary(input));
    }

    @Test
    void givenLibraryRequestDTOWhencreateLibraryWithEmptyNameThenThrowException() {
        // given
        Library input = new Library(1L, "", "Calle 123", lstBooks);

        // then
        assertThrows(IllegalArgumentException.class, () -> libraryService.createLibrary(input));
    }

    @Test
    void givenLibraryRequestDTOWhencreateLibraryWithNullAddressThenThrowException() {
        // given
        Library input = new Library(1L,"Biblioteca Nacional", null, lstBooks);

        // then
        assertThrows(IllegalArgumentException.class, () -> libraryService.createLibrary(input));
    }

    @Test
    void givenLibraryRequestDTOWhencreateLibraryWithEmptyAddressThenThrowException() {
        // given
        Library input = new Library(1L, "Biblioteca Nacional", "", lstBooks);

        // then
        assertThrows(IllegalArgumentException.class, () -> libraryService.createLibrary(input));
    }

    @Test
    void givenExistingLibraryNameWhenCreateLibraryThenThrowLibraryException() {
        // given
        Library input = new Library(1L, "Central Library","Cra 5", lstBooks);
        Mockito.when(libraryRepositoryPort.existsByName(input.name())).thenReturn(true);

        // when - then
        LibraryException ex = assertThrows(
                LibraryException.class,
                () -> libraryService.createLibrary(input)
        );

        assertEquals(LibraryErrorMessage.LIBRARY_ALREDY_REGISTERED, ex.getMessage());
        Mockito.verify(libraryRepositoryPort).existsByName(input.name());
    }




}
