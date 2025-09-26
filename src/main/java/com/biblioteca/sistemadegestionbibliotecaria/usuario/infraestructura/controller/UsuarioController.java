package com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.controller;

import com.biblioteca.sistemadegestionbibliotecaria.usuario.aplication.port.in.CreateUserUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.domain.model.Usuario;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.controller.api.UsuarioApi;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.controller.dto.input.UsuarioDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.controller.dto.input.UsuarioRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.controller.dto.out.UsuarioResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.mapper.IUsuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuario")
public class UsuarioController implements UsuarioApi {

    private final CreateUserUseCase createUserUseCase;
    private final IUsuarioMapper usuarioMapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {

        UsuarioDTO usuarioDTO = usuarioMapper.UsuarioRequestDTOToUsuarioDTO(usuarioRequestDTO);
        Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
        Usuario usuarioCreate = createUserUseCase.createUser(usuario);
        UsuarioDTO usuarioDTOResponse = usuarioMapper.usuarioToUsuarioDTO(usuarioCreate);

        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.UsuarioDTOToUsuarioResponseDTO(usuarioDTOResponse);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

}
