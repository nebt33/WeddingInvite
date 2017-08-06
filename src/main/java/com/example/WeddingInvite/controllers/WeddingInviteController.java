package com.example.WeddingInvite.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class WeddingInviteController {
	
	// inject via application.properties
	@Value("${app.welcome.message}")
	private String MESSAGE;

	@Value("${app.welcome.title}")
	private String TITLE;
	
	@GetMapping(value={"/login", "/"})
	public String login(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		return "login";
	}
	
	@GetMapping(value="/ceremony")
	public String ceremony(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		return "ceremony";
	}
	
	@GetMapping(value="/reception")
	public String reception(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		return "reception";
	}
	
	@GetMapping(value="/the_cast")
	public String the_cast(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		return "the_cast";
	}
	
	@GetMapping(value="/registry")
	public String registry(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		return "registry";
	}

}
