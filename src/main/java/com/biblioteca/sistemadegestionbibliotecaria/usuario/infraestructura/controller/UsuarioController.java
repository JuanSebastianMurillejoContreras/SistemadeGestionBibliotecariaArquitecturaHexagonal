package com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.controller;

import com.biblioteca.sistemadegestionbibliotecaria.usuario.aplication.port.in.CreateUserUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.domain.model.Usuario;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.controller.api.UsuarioApi;
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

        Usuario usuario = usuarioMapper.usuarioRequestDTOToUsuario(usuarioRequestDTO);
        Usuario usuarioCreate = createUserUseCase.createUser(usuario);
        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.usuarioToUsuarioResponseDTO(usuarioCreate);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

}
