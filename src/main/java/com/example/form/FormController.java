package com.example.form;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.example.model.User;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/form")
public class FormController {

	@RequestMapping("/simple")
	public Mono<String> form(User user) {
		return Mono.just("Hello " + user.getName());
	}

	@RequestMapping("/simpleMono")
	public Mono<String> form(Mono<User> user) {
		return user.map(u -> "Hello " + u.getName());
	}

	@RequestMapping("/validate")
	public Mono<String> formValidate(@Validated User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Mono.just("Error!");
		}
		return Mono.just("Hello " + user.getName());
	}

	// Not work!
	@RequestMapping("/validateMonoWithBindingResult")
	public Mono<String> formValidate(@Validated Mono<User> user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Mono.just("Error!");
		}
		return user.map(u -> "Hello " + u.getName());
	}
	
	@RequestMapping("/validateMono")
	public Mono<String> formValidate(@Validated Mono<User> user) {
		return user
				.map(u -> "Hello " + u.getName())
				.otherwise(WebExchangeBindException.class, e -> Mono.just("Error!"));
	}


}
