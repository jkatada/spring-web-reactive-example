package com.example.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

@RestController
public class UserBlockingController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/findBlocking")
	public User findBlocking(@RequestParam("id") long id) { 
		return userRepository.findById(id).block();
	}

	@GetMapping("/listAdultBlocking")
	public List<User> listAdult() {
		List<User> list = new ArrayList<>();
		// 20歳以上のユーザを返す
		userRepository.findAll()
				.filter(u -> u.getAge() >= 20)
				.toIterable()
				.forEach(list::add);
		return list;
	}
}
