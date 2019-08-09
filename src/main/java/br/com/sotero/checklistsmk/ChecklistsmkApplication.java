package br.com.sotero.checklistsmk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ChecklistsmkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChecklistsmkApplication.class, args);
	}

}
