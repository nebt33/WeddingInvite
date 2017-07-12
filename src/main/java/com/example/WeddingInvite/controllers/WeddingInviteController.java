package com.example.WeddingInvite.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WeddingInviteController {
	
	// inject via application.properties
	@Value("${app.welcome.message}")
	private String MESSAGE;

	@Value("${app.welcome.title}")
	private String TITLE;
	
	@RequestMapping(value="/")
	public String welcome(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		return "welcome";
	}
	
	// test 5xx errors
	@RequestMapping("/5xx")
	public String ServiceUnavailable() {
		throw new RuntimeException("ABC");
	}

}
