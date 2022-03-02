package com.sabas.student_apirest.student.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

	private long id;
	
	@NotEmpty
	@Size(min=3, message = "el nombre debe tener 3 caracteres como minimo")
	private String firstName;
	
	@NotEmpty
	@Size(min=3, message = "el apellido debe tener 3 caracteres como minimo")
	private String lastName;
	
	@NotEmpty
	@Email(message = "Porfavor ingrese un email valido")
	private String email;
}
