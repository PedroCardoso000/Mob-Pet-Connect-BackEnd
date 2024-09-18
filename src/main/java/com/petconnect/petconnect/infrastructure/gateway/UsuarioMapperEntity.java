package com.petconnect.petconnect.infrastructure.gateway;

import com.petconnect.petconnect.domain.Usuario;
import com.petconnect.petconnect.infrastructure.persistence.UsuarioEntity;

public class UsuarioMapperEntity {
    public UsuarioEntity toEntity(Usuario usuario){
        return new UsuarioEntity(usuario.getId(), usuario.getName(), usuario.getEmail(),
                usuario.getPhone(), usuario.getCpf(), usuario.getPassword_hash());
    }

    public Usuario toDomain(UsuarioEntity entity){
        return new Usuario(entity.getId(), entity.getName(),
                entity.getEmail(), entity.getPhone(), entity.getCpf(), entity.getPassword_hash());
    }
}
