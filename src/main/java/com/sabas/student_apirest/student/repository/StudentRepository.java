package com.sabas.student_apirest.student.repository;

import org.springframework.stereotype.Repository;

import com.sabas.student_apirest.student.entity.Student;

@Repository
public interface StudentRepository extends IGenericRepository<Student, Long>{

}
