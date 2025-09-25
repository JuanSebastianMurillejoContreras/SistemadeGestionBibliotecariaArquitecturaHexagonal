package com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.mapper;

import com.biblioteca.sistemadegestionbibliotecaria.usuario.domain.model.Usuario;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.controller.dto.input.UsuarioCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.controller.dto.input.UsuarioDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.controller.dto.input.UsuarioRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.controller.dto.out.UsuarioResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.persistance.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUsuarioMapper {

    // DTO -> DTO
    Usuario usuarioRequestDTOToUsuario(UsuarioRequestDTO usuarioRequestDTO);
    UsuarioResponseDTO usuarioDTOToUsuarioResponseDTO(UsuarioDTO usuarioDTO);

    //DTO -> Entity
    UsuarioEntity UsuarioCreateDTOToUsuarioEntity(UsuarioCreateDTO usuarioCreateDTO);

    // Entity -> DTO
    UsuarioDTO UsuarioDTOToUsuarioEntity(UsuarioEntity usuarioEntity);

    UsuarioEntity usuarioToUsuarioEntity(Usuario usuario);

    Usuario usuarioEntityToUsuario(UsuarioEntity usuarioEntity);

    UsuarioResponseDTO usuarioToUsuarioResponseDTO(Usuario usuario);

}
