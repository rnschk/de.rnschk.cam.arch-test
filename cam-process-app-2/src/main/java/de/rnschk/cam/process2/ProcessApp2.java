package de.rnschk.cam.process2;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class ProcessApp2 {

	public static void main(String[] args) {
		SpringApplication.run(ProcessApp2.class, args);
	}
}
