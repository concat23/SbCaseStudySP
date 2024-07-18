package com.dev.sbcasetudysp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.Collections;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class SbCasetudySpApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SbCasetudySpApplication.class);
		application.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
		application.run(args);
	}
}
