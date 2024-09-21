package com.petconnect.petconnect.Entities;


import com.petconnect.petconnect.dtos.CreateUserRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "\"user\"")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String cpf;
    private String password_hash;

    public User(CreateUserRequest createUserRequest) {
        this.name = createUserRequest.name();
        this.email = createUserRequest.email();
        this.phone = createUserRequest.phone();
        this.cpf = createUserRequest.cpf();
        this.password_hash = createUserRequest.password();
    }

}
