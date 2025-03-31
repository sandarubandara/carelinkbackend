package com.example.carelinkbackend;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.carelinkbackend.model.EnumRole;
import com.example.carelinkbackend.model.Role;
import com.example.carelinkbackend.model.User;
import com.example.carelinkbackend.repository.RoleRepository;
import com.example.carelinkbackend.repository.UserRepository;

@SpringBootApplication
public class CarelinkbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarelinkbackendApplication.class, args);
	}

	/*
	@Bean

	ApplicationRunner init(RoleRepository repository) {
		return args -> {

			Role admin = new Role(EnumRole.ROLE_ADMIN);
			Role parent = new Role(EnumRole.ROLE_PARENT);
			Role nanny = new Role(EnumRole.ROLE_NANNY);
			repository.save(admin);
			repository.save(parent);
			repository.save(nanny);



			// repository.save(new User("testuser1","user@gmail.com","password"));
			// repository.findAll().forEach(System.out::println);
		};
	}
		*/

}
