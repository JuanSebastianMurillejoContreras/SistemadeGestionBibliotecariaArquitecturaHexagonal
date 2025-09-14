package com.biblioteca.sistemadegestionbibliotecaria.testUnitarios.service.usuario;

import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.input.UsuarioCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.input.UsuarioDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.entity.UsuarioEntity;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.mapper.IUsuarioMapper;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.repository.IUsuarioRepo;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.service.impl.UsuarioServiceImpl;
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
    private UsuarioServiceImpl usuarioService;

    @Mock
    private IUsuarioRepo usuarioRepo;
    @Spy
    private IUsuarioMapper usuarioMapper = Mappers.getMapper(IUsuarioMapper.class);

    @Test
    public void givenUsuarioCreateDTOWhenCreateUsuarioThenReturnUsuarioResponseDTO() {
        // Given
        UsuarioCreateDTO input = new UsuarioCreateDTO("Juanito","juanito@mail.com");

        //When
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setName("Juanito");
        usuarioEntity.setEmail("juanito@mail.com");

        Mockito.when(usuarioRepo.save(usuarioEntity)).thenReturn(usuarioEntity);

        UsuarioDTO outputEsperado = new UsuarioDTO("Juanito","juanito@mail.com");
        UsuarioDTO resultado = usuarioService.createUsuario(input);

        //Then
        assertEquals(outputEsperado,resultado);

    }

    @Test
    void givenUsuarioCreateDTOWhenAddUsuarioWithNullEmailThenThrowException() {
        // given
        UsuarioCreateDTO input = new UsuarioCreateDTO("Juan", null);

        // then
        assertThrows(IllegalArgumentException.class, () -> usuarioService.createUsuario(input));
    }

    @Test
    void givenUsuarioCreateDTOWhenAddUsuarioWithEmptyEmailThenThrowException() {
        // given
        UsuarioCreateDTO input = new UsuarioCreateDTO("Juan", "");

        // then
        assertThrows(IllegalArgumentException.class, () -> usuarioService.createUsuario(input));
    }

    @Test
    void givenUsuarioCreateDTOWhenAddUsuarioWithInvalidEmailThenThrowException() {
        // given
        UsuarioCreateDTO input = new UsuarioCreateDTO("Juan", "juanito@@mail");

        // then
        assertThrows(IllegalArgumentException.class, () -> usuarioService.createUsuario(input));
    }

    @Test
    void givenUsuarioCreateDTOWhenAddUsuarioWithEmptyNameThenThrowException() {
        // given
        UsuarioCreateDTO input = new UsuarioCreateDTO("", "juan@mail.com");

        // then
        assertThrows(IllegalArgumentException.class, () -> usuarioService.createUsuario(input));
    }

    @Test
    void givenUsuarioCreateDTOWhenAddUsuarioWithNullNameThenThrowException() {
        // given
        UsuarioCreateDTO input = new UsuarioCreateDTO(null, "juan@mail.com");

        // then
        assertThrows(IllegalArgumentException.class, () -> usuarioService.createUsuario(input));
    }

}
