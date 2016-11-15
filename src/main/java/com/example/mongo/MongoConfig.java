package com.example.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;

@Configuration
@Profile("mongodb")
public class MongoConfig {

	@Bean
	MongoDatabase mongoDatabase() {
		return MongoClients.create().getDatabase("demo");
	}

}
