package com.petconnect.petconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetconnectApplication {
	public static void main(String[] args) {
		SpringApplication.run(PetconnectApplication.class, args);
		System.out.println("------------------------------");
		System.out.println("Pet Connect Application");
		System.out.println("------------------------------");
	}
}
