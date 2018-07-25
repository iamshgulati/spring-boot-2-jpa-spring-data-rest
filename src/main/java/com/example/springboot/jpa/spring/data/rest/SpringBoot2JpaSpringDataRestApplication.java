package com.example.springboot.jpa.spring.data.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springboot.jpa.spring.data.rest.repository.StudentRepository;

@SpringBootApplication
public class SpringBoot2JpaSpringDataRestApplication {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2JpaSpringDataRestApplication.class, args);
	}
}
