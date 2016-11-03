package com.example.app.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class HelloController {

	@GetMapping("/hello")
	public Mono<String> hello() {
		log.info("aaa");
		
		return Mono.just("hello");
	}
	
}
