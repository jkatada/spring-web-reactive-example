package com.example.model;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Min(1)
	private long id;
	
	@NotEmpty
	private String name;
	
	@Min(20)
	private int age;
}
