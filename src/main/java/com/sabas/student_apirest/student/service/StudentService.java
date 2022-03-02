package com.sabas.student_apirest.student.service;

import java.util.List;

import com.sabas.student_apirest.student.payload.StudentDto;

public interface StudentService {
	
	StudentDto saveStudent(StudentDto studentDto);
	List<StudentDto> getAllStudents();
	StudentDto getStudentById(long id);
	StudentDto updateStudent(StudentDto studentDto, long id);
	void deleteStudent(long id);
	
}
