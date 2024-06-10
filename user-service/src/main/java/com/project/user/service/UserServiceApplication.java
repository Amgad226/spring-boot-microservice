package com.project.user.service;

import com.project.user.service.model.User;
import com.project.user.service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(UserRepository userRepository){
		return args -> {

			User amjad = new User();
			amjad.setName("Amjad");
			amjad.setRole("Company");

			User abood = new User();
			abood.setName("abood");
			abood.setRole("Customer");
			userRepository.save(amjad);
			userRepository.save(abood);
		};
	}
}
