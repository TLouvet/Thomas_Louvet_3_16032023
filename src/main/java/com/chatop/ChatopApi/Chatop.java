package com.chatop.ChatopApi;

import com.chatop.ChatopApi.service.FileStorageService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// TODO - Connect LocalHost - il faudra très certainement voir pour ne pas avoir d'erreur CORS
// TODO - Exporter le secret jwt
// TODO - Mettre un peu de variable d'environnement parce que là c'est codé en dur
// TODO - Pour le record => JwtProvider / FileStorageService

@SpringBootApplication
public class Chatop implements CommandLineRunner {

	@Resource
	FileStorageService fileStorageService;

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(Chatop.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileStorageService.init();
		System.out.println("App started with success");

	}

}
