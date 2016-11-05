package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.User;

import reactor.core.publisher.Mono;

@RestController
public class HelloController {

	@PostMapping("/hello")
	public Mono<String> hello(@RequestBody Mono<User> user) {
		return user.map(u -> "Hello " + u.getName() + "(" + u.getAge() + ")" + "!!");
	}
	
}
