package com.biblioteca.sistemadegestionbibliotecaria.usuario.mapper;

import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.input.UsuarioCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.input.UsuarioDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.input.UsuarioRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.out.UsuarioResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUsuarioMapper {

    // DTO -> DTO
    UsuarioCreateDTO usuarioRequestDTOToUsuarioCreateDTO(UsuarioRequestDTO usuarioRequestDTO);
    UsuarioResponseDTO usuarioDTOToUsuarioResponseDTO(UsuarioDTO usuarioDTO);

    //DTO -> Entity
    UsuarioEntity UsuarioCreateDTOToUsuarioEntity(UsuarioCreateDTO usuarioCreateDTO);

    // Entity -> DTO
    UsuarioDTO UsuarioDTOToUsuarioEntity(UsuarioEntity usuarioEntity);

}
