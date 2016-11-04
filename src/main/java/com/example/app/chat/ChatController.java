package com.example.app.chat;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	MessageSender messageSender;

	@GetMapping("/connect")
	public Flux<String> connect() {
		return messageSender.connect().map(m -> {
			String dateTime = m.getDateTime()
					.format(DateTimeFormatter.ofPattern("MM/dd HH:mm"));
			return dateTime + " [" + m.getName() + "]: " + m.getMessage();
		});
	}

	@PostMapping("/send")
	public Mono<Void> send(@RequestBody Mono<Message> message) {
		return messageSender.send(message.log());
	}
	
}
