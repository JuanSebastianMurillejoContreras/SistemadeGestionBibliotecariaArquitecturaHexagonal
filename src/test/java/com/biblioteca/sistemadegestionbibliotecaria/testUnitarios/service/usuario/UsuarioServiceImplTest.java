package com.biblioteca.sistemadegestionbibliotecaria.testUnitarios.service.usuario;

import com.biblioteca.sistemadegestionbibliotecaria.usuario.aplication.port.out.UsuarioRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.aplication.service.UsuarioService;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.domain.exception.UsuarioErrorMessage;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.domain.exception.UsuarioException;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.domain.model.Usuario;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.mapper.IUsuarioMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepositoryPort usuarioRepo;
    @Spy
    private IUsuarioMapper usuarioMapper = Mappers.getMapper(IUsuarioMapper.class);

    @Test
    public void givenUsuarioCreateDTOWhenCreateUsuarioThenReturnUsuarioResponseDTO() {
        // Given
        Usuario input = new Usuario(1L,"Juanito","juanito@mail.com");

        //When
        Usuario usuario = new Usuario(1L,"Juanito","juanito@mail.com");

        Mockito.when(usuarioRepo.save(usuario)).thenReturn(usuario);

        Usuario outputEsperado = new Usuario(1L, "Juanito","juanito@mail.com");
        Usuario resultado = usuarioService.createUser(input);

        //Then
        assertEquals(outputEsperado,resultado);

    }

    @Test
    void givenUsuarioCreateDTOWhenAddUsuarioWithNullEmailThenThrowException() {
        // given
        Usuario input = new Usuario(1l, "Juan", null);

        // then
        assertThrows(IllegalArgumentException.class, () -> usuarioService.createUser(input));
    }

    @Test
    void givenUsuarioCreateDTOWhenAddUsuarioWithEmptyEmailThenThrowException() {
        // given
        Usuario input = new Usuario(1l, "Juan", "");

        // then
        assertThrows(IllegalArgumentException.class, () -> usuarioService.createUser(input));
    }

    @Test
    void givenUsuarioCreateDTOWhenAddUsuarioWithInvalidEmailThenThrowException() {
        // given
        Usuario input = new Usuario(1l, "Juan", "juan@@mail.com");

        // then
        assertThrows(IllegalArgumentException.class, () -> usuarioService.createUser(input));
    }

    @Test
    void givenUsuarioCreateDTOWhenAddUsuarioWithEmptyNameThenThrowException() {
        // given
        Usuario input = new Usuario(1l,"", "juan@mail.com");

        // then
        assertThrows(IllegalArgumentException.class, () -> usuarioService.createUser(input));
    }

    @Test
    void givenUsuarioCreateDTOWhenAddUsuarioWithNullNameThenThrowException() {
        // given
        Usuario input = new Usuario(1L,null, "juan@mail.com");

        // then
        assertThrows(IllegalArgumentException.class, () -> usuarioService.createUser(input));
    }

    @Test
    void givenUsuarioWithExistingNameWhenCreateUserThenThrowUsuarioException() {
        // given
        Usuario input = new Usuario(1L, "Juan", "juan@mail.com");

        Mockito.when(usuarioRepo.existsByName(input.name())).thenReturn(true);
        Mockito.when(usuarioRepo.existsByEmail(input.email())).thenReturn(false);

        // then
        UsuarioException ex = assertThrows(UsuarioException.class, () -> usuarioService.createUser(input));

        assertTrue(ex.getMessage().contains(UsuarioErrorMessage.USER_NAME_ALREADY_REGISTERED));
    }

    @Test
    void givenUsuarioWithExistingEmailWhenCreateUserThenThrowUsuarioException() {
        // given
        Usuario input = new Usuario(1L, "Juan", "juan@mail.com");

        Mockito.when(usuarioRepo.existsByName(input.name())).thenReturn(false);
        Mockito.when(usuarioRepo.existsByEmail(input.email())).thenReturn(true);

        // then
        UsuarioException ex = assertThrows(UsuarioException.class, () -> usuarioService.createUser(input));

        assertTrue(ex.getMessage().contains(UsuarioErrorMessage.USER_EMAIL_ALREADY_REGISTERED));
    }




}
