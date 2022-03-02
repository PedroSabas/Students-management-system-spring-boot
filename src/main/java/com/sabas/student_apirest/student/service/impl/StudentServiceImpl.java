package com.sabas.student_apirest.student.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.sabas.student_apirest.student.entity.Student;
import com.sabas.student_apirest.student.exception.ResourceNotFoundException;
import com.sabas.student_apirest.student.payload.StudentDto;
import com.sabas.student_apirest.student.repository.StudentRepository;
import com.sabas.student_apirest.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	private StudentRepository studentRepository;
	private ModelMapper mapper;
	
	public StudentServiceImpl(StudentRepository studentRepository, ModelMapper mapper) {
		super();
		this.studentRepository = studentRepository;
		this.mapper = mapper;
	}

	
	@Override
	public StudentDto saveStudent(StudentDto studentDto) {
		Student student = mapToEntity(studentDto);	
		Student newStudent = studentRepository.save(student);
		return mapToDTO(newStudent);
	}
	
	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> listOfStudent = studentRepository.findAll();
		return listOfStudent.stream()
				.map(student->mapToDTO(student))
				.collect(Collectors.toList()); 
	}
	
	
	@Override
	public StudentDto getStudentById(long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Student", "id", id));
		return mapToDTO(student);
	}
	
	
	@Override
	public StudentDto updateStudent(StudentDto studentDto, long id) {
		
		Student dbstudent = studentRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

		dbstudent.setFirstName(studentDto.getFirstName());
		dbstudent.setLastName(studentDto.getLastName());
		dbstudent.setEmail(studentDto.getEmail());
		
		Student updateStudent = studentRepository.save(dbstudent);
		
		return mapToDTO(updateStudent);
	}
	
	
	@Override
	public void deleteStudent(long id) {
		// Check if exists the Student with that ID.
		studentRepository.findById(id)
						 .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
		studentRepository.deleteById(id);	
	}

	
	
	// Convert Comment to CommentDto
	private StudentDto mapToDTO(Student student) {
		StudentDto StudentDto = mapper.map(student, StudentDto.class);
		return StudentDto;
	}

	// Convert CommentsDto to Comment
	private Student mapToEntity(StudentDto studentDto) {
		Student student = mapper.map(studentDto, Student.class);
		return student;
	}
	
}
