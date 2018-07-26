package com.example.springboot.jpa.spring.data.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.springboot.jpa.spring.data.rest.entity.Student;

@RepositoryRestResource(collectionResourceRel="students", path="students")
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	List<Student> findByName(@Param("name") String name);
	
}
