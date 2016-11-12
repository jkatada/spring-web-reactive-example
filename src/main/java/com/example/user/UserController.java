package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/find")
	public Mono<User> find(@RequestParam("id") long id) { 
		return userRepository.findById(id);
	}

	@GetMapping("/listAdult")
	public Flux<User> listAdult() {
		// 20歳以上のユーザを返す
		return userRepository.findAll()
				.filter(u -> u.getAge() >= 20);
	}
}
