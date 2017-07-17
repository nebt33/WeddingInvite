package com.example.WeddingInvite.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class WeddingInviteController {
	
	// inject via application.properties
	@Value("${app.welcome.message}")
	private String MESSAGE;

	@Value("${app.welcome.title}")
	private String TITLE;
	
	@GetMapping(value="/")
	public String welcome(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		return "welcome";
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

}
