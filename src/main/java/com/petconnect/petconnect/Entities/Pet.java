package com.petconnect.petconnect.Entities;

import com.petconnect.petconnect.dtos.CreatePetRequest;
import com.petconnect.petconnect.enums.PetGender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"pet\"")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PetGender gender;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private String specie;

    private String race;

    @ElementCollection
    @CollectionTable(name = "pet_vaccine", joinColumns = @JoinColumn(name = "pet_id"))
    @Column(name = "vaccine")
    private List<String> vaccines;

    @ManyToOne
    private User user;

    public Pet (CreatePetRequest request, PetGender gender, User user) {
        name = request.name();
        this.gender = gender;
        birthDate = request.birthDate();
        specie = request.specie();
        race = request.race();
        this.user = user;
    }

}
