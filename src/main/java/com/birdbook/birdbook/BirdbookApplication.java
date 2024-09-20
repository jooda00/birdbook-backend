package com.birdbook.birdbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BirdbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BirdbookApplication.class, args);
	}

}
