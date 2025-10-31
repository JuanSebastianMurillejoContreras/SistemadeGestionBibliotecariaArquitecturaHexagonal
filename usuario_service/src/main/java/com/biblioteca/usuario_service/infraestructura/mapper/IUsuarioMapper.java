package com.biblioteca.usuario_service.infraestructura.mapper;

import com.biblioteca.usuario_service.domain.model.Usuario;
import com.biblioteca.usuario_service.infraestructura.controller.dto.input.UsuarioDTO;
import com.biblioteca.usuario_service.infraestructura.controller.dto.input.UsuarioRequestDTO;
import com.biblioteca.usuario_service.infraestructura.controller.dto.out.UsuarioResponseDTO;
import com.biblioteca.usuario_service.infraestructura.persistance.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUsuarioMapper {


    UsuarioEntity usuarioToUsuarioEntity(Usuario usuario);

    Usuario usuarioEntityToUsuario(UsuarioEntity usuarioEntity);

    UsuarioResponseDTO usuarioToUsuarioResponseDTO(Usuario usuario);

    UsuarioDTO UsuarioRequestDTOToUsuarioDTO(UsuarioRequestDTO usuarioRequestDTO);

    Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);

    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

    UsuarioResponseDTO UsuarioDTOToUsuarioResponseDTO(UsuarioDTO usuarioDTO);
}
