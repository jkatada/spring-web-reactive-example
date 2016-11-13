package com.example.mongo;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MongoRepository {

	private final ObjectMapper mapper;
	private final MongoCollection<Document> col;

	@Autowired
	public MongoRepository(MongoDatabase db, ObjectMapper mapper) {
		this.mapper = mapper;
		this.col = db.getCollection("user");
	}

	public Mono<Void> insert(Mono<User> user) {
		return user.flatMap(u -> col.insertOne(Document.parse(toJson(u)))).then();
	}

	public Flux<User> findAll() {
		return Flux.from(col.find()).map(d -> new User(d.getInteger("id"),
				d.getString("name"), d.getInteger("age")));
	}
	
	private String toJson(User user) {
		try {
			return mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public Mono<Void> insert(User user) {
		return Mono.empty(col.insertOne(Document.parse(toJson(user))));
	}
	
}
