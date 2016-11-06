package com.example.chat;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.TopicProcessor;

@Component
public class MessageSender {

	private TopicProcessor<Message> topic = TopicProcessor.share(true);

	public Flux<Message> connect() {
		return Flux.from(topic);
	}

	public Mono<Void> send(Mono<Message> message) {
		return message.doOnNext(m -> topic.onNext(m)).then();
	}

}
