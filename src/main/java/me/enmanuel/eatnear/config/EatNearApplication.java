package me.enmanuel.eatnear.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "me.enmanuel.eatnear")
public class EatNearApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatNearApplication.class, args);
	}
}
