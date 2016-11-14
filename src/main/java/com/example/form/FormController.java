package com.example.form;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping("/param")
	public Mono<String> param(@RequestParam String name) {
		return Mono.just("Hello " + name);
	}

	// Monoと通常のBeanの両方が引数にある場合は、通常のBeanを引数に取ったときと同様の動き
	// (ReadListenerがリクエストを読み込んでからControllerのメソッドが起動される)
	@RequestMapping("/mix")
	public Mono<String> formMix(Mono<User> user1, User user2) {
		return user1.map(u -> "Hello " + u.getName() + " and " + user2.getName());
	}

	@RequestMapping("/helloAnnotation")
	public Mono<String> helloAnnotation(@ModelAttribute("user") Mono<User> user) {
		return user.map(u -> "Hello " + u.getName());
	}

	@RequestMapping("/hello")
	public Mono<String> hello(Mono<User> user) {
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
