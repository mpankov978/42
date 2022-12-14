package ru.hackandchallenge.investadvisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InvestAdvisorApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestAdvisorApplication.class, args);
	}

}
