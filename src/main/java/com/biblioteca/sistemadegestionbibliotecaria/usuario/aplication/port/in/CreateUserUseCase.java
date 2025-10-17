package com.biblioteca.library_service.usuario.aplication.port.in;

import com.biblioteca.library_service.usuario.domain.model.Usuario;

public interface CreateUserUseCase {

    Usuario createUser(Usuario usuario);

}
