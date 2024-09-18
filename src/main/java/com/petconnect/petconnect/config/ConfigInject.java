package com.petconnect.petconnect.config;

import com.petconnect.petconnect.application.gateway.CreateUserGateway;
import com.petconnect.petconnect.application.usecases.CreateUser;
import com.petconnect.petconnect.infrastructure.gateway.RepositorioUsuarioJPA;
import com.petconnect.petconnect.infrastructure.gateway.UsuarioMapperEntity;
import com.petconnect.petconnect.infrastructure.persistence.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
 - você deve incluir classes que dependem da injeção de dependências ou que precisam ser gerenciadas pelo contexto do Spring.
 */
@Configuration
public class ConfigInject {
    @Bean
    UsuarioMapperEntity usuarioMapperEntity() {
        return new UsuarioMapperEntity();
    }

    @Bean
    CreateUser createUserUser(CreateUserGateway createUserGateway) {
        return new CreateUser(createUserGateway);
    }

    @Bean
    RepositorioUsuarioJPA repositorioUsuarioJPA(UsuarioRepository usuarioRepository, UsuarioMapperEntity mapperEntity){
        return new RepositorioUsuarioJPA(usuarioRepository, mapperEntity);
    }


}
