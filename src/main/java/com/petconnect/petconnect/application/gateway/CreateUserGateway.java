package com.petconnect.petconnect.application.gateway;

import com.petconnect.petconnect.domain.Usuario;

public interface CreateUserGateway {
    // Login
    Usuario create(Usuario user);
}
