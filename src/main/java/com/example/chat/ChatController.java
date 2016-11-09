package com.example.chat;

import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.ReplayProcessor;

@RestController
@RequestMapping("/chat")
public class ChatController {

	private FluxProcessor<Message, Message> processor = ReplayProcessor.<Message> create()
			.serialize();

	@GetMapping("/connect")
	public Flux<String> connect() {
		return processor.connect().map(m -> {
			String dateTime = m.getDateTime()
					.format(DateTimeFormatter.ofPattern("MM/dd HH:mm"));
			return dateTime + " [" + m.getName() + "]: " + m.getMessage();
		}).log();
	}

}
