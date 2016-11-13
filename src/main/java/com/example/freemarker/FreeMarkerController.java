package com.example.freemarker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.User;

import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/freemarker")
public class FreeMarkerController {

	@RequestMapping("/test")
	public String test(Model model) {
		List<User> list = new ArrayList<>();
		list.add(new User(1, "name1", 10));
		list.add(new User(2, "name2", 20));
		list.add(new User(3, "name3", 30));

		model.addAttribute("dataSource", Flux.just(list).log());

		return "freemarker/list";
	}

}
