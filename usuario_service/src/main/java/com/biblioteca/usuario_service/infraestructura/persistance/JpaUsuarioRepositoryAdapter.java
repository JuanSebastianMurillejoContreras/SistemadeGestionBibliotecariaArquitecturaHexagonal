package com.biblioteca.usuario_service.infraestructura.persistance;

import com.biblioteca.library_service.usuario.aplication.port.out.UsuarioRepositoryPort;
import com.biblioteca.library_service.usuario.domain.model.Usuario;
import com.biblioteca.library_service.usuario.infraestructura.mapper.IUsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaUsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final SpringDataUsuarioRepository usuarioRepository;
    private final IUsuarioMapper mapperUsuario;

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity usuarioEntity = mapperUsuario.usuarioToUsuarioEntity(usuario);
        UsuarioEntity save = usuarioRepository.save(usuarioEntity);
        return mapperUsuario.usuarioEntityToUsuario(save);
    }

    @Override
    public boolean existsByName(String name) {
        return usuarioRepository.existsByName(name);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

}
