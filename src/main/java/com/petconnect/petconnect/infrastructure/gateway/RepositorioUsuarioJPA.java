package com.petconnect.petconnect.infrastructure.gateway;

import com.petconnect.petconnect.application.gateway.CreateUserGateway;
import com.petconnect.petconnect.domain.Usuario;
import com.petconnect.petconnect.infrastructure.persistence.UsuarioEntity;
import com.petconnect.petconnect.infrastructure.persistence.UsuarioRepository;

public class RepositorioUsuarioJPA implements CreateUserGateway {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapperEntity mapperEntity;

    public RepositorioUsuarioJPA(UsuarioRepository usuarioRepository, UsuarioMapperEntity mapperEntity) {
        this.usuarioRepository = usuarioRepository;
        this.mapperEntity = mapperEntity;
    }


    @Override
    public Usuario create(Usuario user) {
        UsuarioEntity usuarioEntity = mapperEntity.toEntity(user);
        usuarioRepository.save(usuarioEntity);
        return mapperEntity.toDomain(usuarioEntity);
    }
}
