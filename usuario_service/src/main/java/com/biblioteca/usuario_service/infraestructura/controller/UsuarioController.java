package com.biblioteca.usuario_service.infraestructura.controller;

import com.biblioteca.library_service.usuario.aplication.port.in.CreateUserUseCase;
import com.biblioteca.library_service.usuario.domain.model.Usuario;
import com.biblioteca.library_service.usuario.infraestructura.controller.api.UsuarioApi;
import com.biblioteca.library_service.usuario.infraestructura.controller.dto.input.UsuarioDTO;
import com.biblioteca.library_service.usuario.infraestructura.controller.dto.input.UsuarioRequestDTO;
import com.biblioteca.library_service.usuario.infraestructura.controller.dto.out.UsuarioResponseDTO;
import com.biblioteca.library_service.usuario.infraestructura.mapper.IUsuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

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
