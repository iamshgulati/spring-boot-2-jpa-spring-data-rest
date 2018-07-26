package com.example.springboot.jpa.spring.data.rest.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.springboot.jpa.spring.data.rest.entity.Student;
import com.example.springboot.jpa.spring.data.rest.exception.StudentNotFoundException;
import com.example.springboot.jpa.spring.data.rest.repository.StudentRepository;

@RestController
public class StudentResource {

	@Autowired
	private StudentRepository studentRepository;

	@RequestMapping(value = "/student-service/find-all-students", method = RequestMethod.GET)
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}

	@RequestMapping(value = "/student-service/find-student/{id}", method = RequestMethod.GET)
	public Student findById(@RequestParam(value = "id") Long id) throws StudentNotFoundException {
		Optional<Student> student = studentRepository.findById(id);
		if (!student.isPresent()) {
			throw new StudentNotFoundException("Student ID: " + id);
		}
		return student.get();
	}

	@RequestMapping(value = "/student-service/delete-student/{id}", method = RequestMethod.DELETE)
	public void deleteById(@RequestParam(value = "id") Long id) {
		studentRepository.deleteById(id);
	}

	@RequestMapping(value = "/student-service/create-student", method = RequestMethod.POST)
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/student-service/update-student/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @RequestParam(value = "id") Long id) {
		Optional<Student> studentOptional = studentRepository.findById(id);
		if (!studentOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			student.setId(id);
			studentRepository.save(student);
			return ResponseEntity.noContent().build();
		}
	}
}
