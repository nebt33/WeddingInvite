package com.example.WeddingInvite.controllers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.WeddingInvite.repo.AppUserRepository;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WeddingInvite.model.AppUser;
import com.example.WeddingInvite.model.Song;
import com.example.WeddingInvite.repo.SongRepository;

@Controller
public class WeddingInviteController {
	
	@Autowired
	SongRepository songRepoistory;

	@Autowired
	AppUserRepository appUserRepository;
	
	// inject via application.properties
	@Value("${app.welcome.message}")
	private String MESSAGE;

	@Value("${app.welcome.title}")
	private String TITLE;
	
	@GetMapping(value={"/login", "/"})
	public String login(Map<String, Object> model, @RequestParam(value = "error", required = false) String error) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		
		if (error != null && error.equalsIgnoreCase("badCred")) {
	        model.put("badCred", "Invalid username and/or password!");
	    } else if (error != null && error.equalsIgnoreCase("locked")) {
	    	model.put("locked", "Your Account is Locked!");
	    }
		
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
	
	@PostMapping(value = "/addSongPost")
	public String addSongPost(@ModelAttribute Song song, Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		song.setSubmittedBy(auth.getName());
	      
		songRepoistory.save(song);
		
		Pageable pageable = new PageRequest(0,1000,
				Direction.ASC, "title", "artist");
		
		Page<Song> songTable = songRepoistory.findAll(pageable);
		model.put("songTable", songTable);
		return "requestSong";
	}
	
	@GetMapping(value = "/requestSong")
	public String requestSong(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		
		//used to calculate popularity
//		int numOfUsers = appUserRepository.findAll().size();
		
		Pageable pageable = new PageRequest(0,1000,
				Direction.ASC, "title", "artist");

		Page<Song> songTable = songRepoistory.findAll(pageable);
		
		//terrible code to populate a heart for each list spot for mustache
//		for (Song s: songTable) {
//			s.setNumOfHearts(new ArrayList<String>());
//			s.getNumOfHearts().add("heart");
//			for (int i = 0; i < s.getPopularity()/numOfUsers; i++) {
//				if (!(i > 4)) {
//					s.getNumOfHearts().add("heart");
//				} else {
//					break;
//				}
//			}
//		}
		model.put("songTable", songTable);
		return "requestSong";
	}
	
//	@PostMapping(value = "/likeSong")
//	public String likeSong(@RequestParam("title")  String title,
//			               @RequestParam("artist") String artist, 
//			               Map<String, Object> model) {
//		model.put("title", TITLE);
//		model.put("message", MESSAGE);
//		//songRepoistory.save(song);
//		return "requestSong";
//	}
	
//	@GetMapping(value = "/accessDenied")
//	public String accessDeniedPage(Map<String, Object> model) {
//		model.put("title", TITLE);
//		model.put("message", MESSAGE);
//		return "accessDenied";
//	}

}
