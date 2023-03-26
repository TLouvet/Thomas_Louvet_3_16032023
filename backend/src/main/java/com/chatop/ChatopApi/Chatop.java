package com.chatop.ChatopApi;

import com.chatop.ChatopApi.service.FileStorageService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
