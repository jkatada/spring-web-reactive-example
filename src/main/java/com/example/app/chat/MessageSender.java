package com.example.app.chat;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.TopicProcessor;

@Component
@Slf4j
public class MessageSender {

	private TopicProcessor<Message> topic = TopicProcessor.create();

	public Flux<Message> connect() {
		return Flux.from(topic);
	}

	public Mono<Void> send(Mono<Message> message) {
		return message.doOnNext(m -> topic.onNext(m)).then();
	}

}
