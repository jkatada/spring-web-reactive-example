package com.example.chat;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Message {

	private LocalDateTime dateTime = LocalDateTime.now();
	private String name;
	private String message;
}
