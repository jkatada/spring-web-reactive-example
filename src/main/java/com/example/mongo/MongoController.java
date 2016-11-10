package com.example.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mongo")
public class MongoController {

	@Autowired
	MongoRepository mongoRepository;
	
	@RequestMapping("/insert")
	public Mono<Void> insert(@RequestBody User user) {
		return mongoRepository.insert(user);
	}

	@RequestMapping("/insertMono")
	public Mono<Void> insert(@RequestBody Mono<User> user) {
		return mongoRepository.insert(user);
	}

	@RequestMapping("/findAll")
	public Flux<User> findAll() {
		return mongoRepository.findAll();
	}
	
}
