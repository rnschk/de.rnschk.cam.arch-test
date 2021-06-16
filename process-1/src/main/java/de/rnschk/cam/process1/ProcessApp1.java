package de.rnschk.cam.process1;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class ProcessApp1 {

	public static void main(String[] args) {
		SpringApplication.run(ProcessApp1.class, args);
	}
}
