package com.example.user.reactor;

import org.springframework.stereotype.Repository;

import com.example.user.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryDummy implements UserRepository {

	@Override
	public Mono<User> findById(long id) {
		return Mono.just(new User(id, "taro", 20));
	}

	@Override
	public Flux<User> findAll() {
		return Flux.just(
				new User(2L, "foo1", 15),
				new User(3L, "foo2", 25),
				new User(4L, "foo3", 35));
	}

	@Override
	public Mono<Void> save(User user) {
		return Mono.empty();
	}

}
