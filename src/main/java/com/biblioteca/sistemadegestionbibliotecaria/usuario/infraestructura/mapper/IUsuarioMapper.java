package com.biblioteca.library_service.usuario.infraestructura.mapper;

import com.biblioteca.library_service.usuario.domain.model.Usuario;
import com.biblioteca.library_service.usuario.infraestructura.controller.dto.input.UsuarioDTO;
import com.biblioteca.library_service.usuario.infraestructura.controller.dto.input.UsuarioRequestDTO;
import com.biblioteca.library_service.usuario.infraestructura.controller.dto.out.UsuarioResponseDTO;
import com.biblioteca.library_service.usuario.infraestructura.persistance.UsuarioEntity;
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
