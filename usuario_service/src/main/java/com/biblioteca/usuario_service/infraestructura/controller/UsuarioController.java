package com.biblioteca.usuario_service.infraestructura.controller;

import com.biblioteca.usuario_service.aplication.port.in.CreateUserUseCase;
import com.biblioteca.usuario_service.domain.model.Usuario;
import com.biblioteca.usuario_service.infraestructura.controller.api.UsuarioApi;
import com.biblioteca.usuario_service.infraestructura.controller.dto.input.UsuarioDTO;
import com.biblioteca.usuario_service.infraestructura.controller.dto.input.UsuarioRequestDTO;
import com.biblioteca.usuario_service.infraestructura.controller.dto.out.UsuarioResponseDTO;
import com.biblioteca.usuario_service.infraestructura.mapper.IUsuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
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
