package com.example.springboot.jpa.spring.data.rest.exception;

public class StudentNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public StudentNotFoundException(String exMessage) {
		super(exMessage);
	}
	
}
