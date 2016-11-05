package com.example.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private long id;
	private String name;
	private int age;
}
