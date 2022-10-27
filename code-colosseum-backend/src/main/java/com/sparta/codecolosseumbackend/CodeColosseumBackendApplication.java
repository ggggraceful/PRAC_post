package com.sparta.codecolosseumbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CodeColosseumBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeColosseumBackendApplication.class, args);
	}

}
