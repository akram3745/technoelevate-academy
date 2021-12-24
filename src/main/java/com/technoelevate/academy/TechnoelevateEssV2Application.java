package com.technoelevate.academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class TechnoelevateEssV2Application {

	public static void main(String[] args) {
		SpringApplication.run(TechnoelevateEssV2Application.class, args);
	}

	@Bean
	public ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		return mapper;
	}
	@Bean
	public RestTemplate getRestTemplate() {
	return	new RestTemplate();
	}
}
