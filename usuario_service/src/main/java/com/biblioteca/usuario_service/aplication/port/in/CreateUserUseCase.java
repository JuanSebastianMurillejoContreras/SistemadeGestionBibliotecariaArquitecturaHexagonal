package com.biblioteca.usuario_service.aplication.port.in;

import com.biblioteca.usuario_service.domain.model.Usuario;

public interface CreateUserUseCase {

    Usuario createUser(Usuario usuario);

}
