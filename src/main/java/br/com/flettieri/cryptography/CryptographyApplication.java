package br.com.flettieri.cryptography;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.flettieri.cryptography.repository")
public class CryptographyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptographyApplication.class, args);
	}

}
