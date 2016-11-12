package com.example.demo;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.example.model.User;

import io.reactivex.Single;
import reactor.core.publisher.Flux;
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
	
	// BODYのJSONのルート要素を[]で配列にするか、BODYにJSONを連続で書くとFluxで複数受けれる
	// 本当はHTTP streaming でつないでJSONを適宜流し込んでいくような使い方だと思われる
	@RequestMapping("/helloFlux")
	public Flux<String> helloFlux(@RequestBody Flux<User> user) {
		return user
				.map(u -> "Hello " + u.getName());
	}

	@RequestMapping("/helloFluxValidate")
	public Flux<String> helloFluxValidate(@Validated @RequestBody Flux<User> user) {
		return user
				.map(u -> "Hello " + u.getName())
				.onErrorResumeWith(WebExchangeBindException.class, e -> Mono.just("Error!"));
	}
}
