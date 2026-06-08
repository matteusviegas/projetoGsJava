package br.com.globalsolution.agrosat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgrosatApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgrosatApplication.class, args);
	}

}
