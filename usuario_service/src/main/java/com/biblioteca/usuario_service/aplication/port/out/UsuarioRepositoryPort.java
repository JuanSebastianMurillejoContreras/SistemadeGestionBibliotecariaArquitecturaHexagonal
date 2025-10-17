package com.biblioteca.usuario_service.aplication.port.out;

import com.biblioteca.library_service.usuario.domain.model.Usuario;

public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}
