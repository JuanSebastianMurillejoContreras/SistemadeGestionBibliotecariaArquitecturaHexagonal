package com.biblioteca.sistemadegestionbibliotecaria.testUnitarios.service.usuario;

import com.biblioteca.sistemadegestionbibliotecaria.usuario.aplication.port.out.UsuarioRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.aplication.service.UsuarioService;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.domain.model.Usuario;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.controller.dto.input.UsuarioCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.controller.dto.input.UsuarioDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.mapper.IUsuarioMapper;
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

}
