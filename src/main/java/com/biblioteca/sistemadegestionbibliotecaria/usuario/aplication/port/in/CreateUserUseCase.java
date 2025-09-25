package com.biblioteca.sistemadegestionbibliotecaria.usuario.aplication.port.in;

import com.biblioteca.sistemadegestionbibliotecaria.usuario.domain.model.Usuario;

public interface CreateUserUseCase {

    Usuario createUser(Usuario usuario);

}
