package com.javatpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.javatpoint")
public class MetricBoetApiApplication {

	public static void main(String[] args) {
		System.setProperty("log4j.configurationFile", "log4j2.xml");
		SpringApplication.run(MetricBoetApiApplication.class, args);
	}

}
