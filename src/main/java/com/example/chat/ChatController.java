package com.example.chat;

import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.TopicProcessor;

@RestController
@RequestMapping("/chat")
public class ChatController {

	private TopicProcessor<Message> topic = TopicProcessor.share(true);

	@GetMapping("/connect")
	public Flux<String> connect() {
		return Flux.from(topic.connect().map(m -> {
			String dateTime = m.getDateTime()
					.format(DateTimeFormatter.ofPattern("MM/dd HH:mm"));
			return dateTime + " [" + m.getName() + "]: " + m.getMessage();
		}));
	}

	@PostMapping("/send")
	public Mono<Void> send(@RequestBody Mono<Message> message) {
		return message.doOnNext(m -> topic.onNext(m)).then();
	}

}
