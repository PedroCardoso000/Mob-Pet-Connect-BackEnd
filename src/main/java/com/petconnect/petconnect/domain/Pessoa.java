package com.petconnect.petconnect.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @Entity(name = "pessoa-breno")
// @Table(name = "pessoaBrenno")
public class Pessoa {
    private String name;
    private String email;
    private Integer age;


}
