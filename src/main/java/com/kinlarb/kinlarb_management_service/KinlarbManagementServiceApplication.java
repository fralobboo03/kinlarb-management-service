package com.kinlarb.kinlarb_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springdoc.core.configuration.SpringDocDataRestConfiguration;
import org.springdoc.core.configuration.SpringDocHateoasConfiguration;

@SpringBootApplication(exclude = {
		SpringDocDataRestConfiguration.class,
		SpringDocHateoasConfiguration.class
})
public class KinlarbManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KinlarbManagementServiceApplication.class, args);
	}

}
