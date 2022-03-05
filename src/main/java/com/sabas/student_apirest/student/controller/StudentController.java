package com.sabas.student_apirest.student.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.sabas.student_apirest.student.payload.StudentDto;
import com.sabas.student_apirest.student.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
public class StudentController {
	
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	
	// Build create student REST API
	@PostMapping
	public ResponseEntity<StudentDto> saveStudent(@Valid @RequestBody StudentDto studentDto) {
		return new ResponseEntity<>(studentService.saveStudent(studentDto), HttpStatus.CREATED);
	}
	
	// Get all Students REST API
	@GetMapping
	public List<StudentDto> getAllStudents() {
		return studentService.getAllStudents();
	}
	
	// Get Student by Id REST API
	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable(name="id") long studentId) {
		return ResponseEntity.ok(studentService.getStudentById(studentId));                                                             
	}
	
	// Update Student by Id REST API
	@PutMapping("/{id}")
	public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto, @PathVariable(name = "id") long id) {
		StudentDto studentResponse = studentService.updateStudent(studentDto, id);
		return new ResponseEntity<>(studentResponse, HttpStatus.OK);
	}
	
	// Deleted Student by Id REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable(name = "id") long id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<>("Student entity deleted successfully.", HttpStatus.OK);
	}
	
	
}
