package com.example.mongo;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.reactivestreams.client.MongoDatabase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MongoRepository {

	@Autowired
	MongoDatabase mongodb;

	@Autowired
	ObjectMapper mapper;

	public Mono<Void> insert(User user) {
		return Mono.empty(
				mongodb.getCollection("user").insertOne(Document.parse(toJson(user))));
	}

	public Mono<Void> insert(Mono<User> user) {
		return user.flatMap(
				u -> mongodb.getCollection("user").insertOne(Document.parse(toJson(u))))
				.then();
	}

	public Flux<User> findAll() {
		return Flux.from(mongodb.getCollection("user").find())
				.map(d -> new User(d.getInteger("id"), d.getString("name"),
						d.getInteger("age")));
	}

	private String toJson(User user) {
		try {
			return mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
