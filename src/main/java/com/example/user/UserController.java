package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/listAdult")
	public Flux<User> listAdult() {
		// 20歳以上のユーザを返す
		return userRepository.findAll()
				.filter(u -> u.getAge() >= 20);
	}

}
