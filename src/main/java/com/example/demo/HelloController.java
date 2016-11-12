package com.example.demo;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

import io.reactivex.Single;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

	@RequestMapping("/hello")
	public Mono<String> hello(@RequestBody User user) {
		return Mono.just("Hello " + user.getName() + "!!");
	}

	@RequestMapping("/helloMono")
	public Mono<String> hello(@RequestBody Mono<User> user) {
		return user.map(u -> "Hello " + u.getName() + "!!");
	}
	
	// RxJava
	@RequestMapping("/helloSingle")
	public Single<String> helloPublisher(@RequestBody Mono<User> user) {
		return Single.fromPublisher(user.map(u -> "Hello " + u.getName() + "!!"));
	}
}
