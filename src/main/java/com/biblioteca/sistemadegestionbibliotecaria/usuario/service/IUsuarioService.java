package com.biblioteca.sistemadegestionbibliotecaria.usuario.service;

import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.input.UsuarioCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.input.UsuarioDTO;

public interface IUsuarioService {

    UsuarioDTO createUsuario(UsuarioCreateDTO usuarioCreateDTO);

}
