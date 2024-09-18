package com.petconnect.petconnect.application.usecases;

import com.petconnect.petconnect.application.gateway.CreateUserGateway;
import com.petconnect.petconnect.domain.Usuario;

public class CreateUser implements CreateUserGateway {
    private final CreateUserGateway createUserGateway;

    public CreateUser(CreateUserGateway createUserGateway) {
        this.createUserGateway = createUserGateway;
    }


    @Override
    public Usuario create(Usuario user) {
        Usuario userCreated = createUserGateway.create(user);

        return userCreated;
    }
}
