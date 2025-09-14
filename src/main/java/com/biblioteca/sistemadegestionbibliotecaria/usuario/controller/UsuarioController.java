package com.biblioteca.sistemadegestionbibliotecaria.usuario.controller;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.input.ReservationDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.out.ReservationResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.api.UsuarioApi;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.input.UsuarioCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.input.UsuarioDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.input.UsuarioRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.out.UsuarioResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.mapper.IUsuarioMapper;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.service.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuario")
public class UsuarioController implements UsuarioApi {

    private final IUsuarioService usuarioService;
    private final IUsuarioMapper usuarioMapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {

        UsuarioCreateDTO usuarioCreateDTO = usuarioMapper.usuarioRequestDTOToUsuarioCreateDTO(usuarioRequestDTO);
        UsuarioDTO usuarioDTO = usuarioService.createUsuario(usuarioCreateDTO);
        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.usuarioDTOToUsuarioResponseDTO(usuarioDTO);

        return ResponseEntity.ok(usuarioResponseDTO);
}

}
