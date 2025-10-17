package com.biblioteca.library_service.usuario.aplication.service;

import com.biblioteca.library_service.usuario.aplication.port.in.CreateUserUseCase;
import com.biblioteca.library_service.usuario.aplication.port.out.UsuarioRepositoryPort;
import com.biblioteca.library_service.usuario.domain.exception.UsuarioErrorMessage;
import com.biblioteca.library_service.usuario.domain.exception.UsuarioException;
import com.biblioteca.library_service.usuario.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements CreateUserUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public Usuario createUser(Usuario usuario) {
        if (usuario.name() == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo");
        }

        if (usuario.email() == null) {
            throw new IllegalArgumentException("El email no puede ser nulo");
        }

        if (usuario.name().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede esta vacío");
        }

        if (usuario.email().isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }

        if (!usuario.email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("El email registrado debe tener un formato válido: ejemplo@mail.com");
        }

        List<String> errors = new ArrayList<>();

        if (usuarioRepositoryPort.existsByName(usuario.name()))
            errors.add(UsuarioErrorMessage.USER_NAME_ALREADY_REGISTERED + ": " + usuario.name());

        if (usuarioRepositoryPort.existsByEmail(usuario.email()))
            errors.add(UsuarioErrorMessage.USER_EMAIL_ALREADY_REGISTERED + ": " + usuario.email());

        if(!errors.isEmpty()) throw new UsuarioException(String.join("; ", errors));

        return usuarioRepositoryPort.save(usuario);
    }
}
