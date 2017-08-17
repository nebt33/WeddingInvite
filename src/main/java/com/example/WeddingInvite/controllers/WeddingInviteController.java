package com.example.WeddingInvite.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.WeddingInvite.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
// 
//    @RequestMapping("/partials/{page}")
//    String partialHandler(@PathVariable("page") final String page) {
//        return page;
//    }
	
	@PostMapping(value = "/addSongPost")
	public String addSongPost(@ModelAttribute Song song, Map<String, Object> model,Pageable pageable) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		songRepoistory.save(song);
		Page<Song> songTable = songRepoistory.findAll(pageable);
		model.put("songTable", songTable);
		return "requestSong";
	}
	
	@GetMapping(value = "/requestSong")
	public String requestSong(Pageable pageable, Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		
		//used to calculate popularity
		int numOfUsers = appUserRepository.findAll().size();

		Page<Song> songTable = songRepoistory.findAll(pageable);
		
		//terrible code to populate a heart for each list spot for mustache
		for (Song s: songTable) {
			s.setNumOfHearts(new ArrayList<String>());
			s.getNumOfHearts().add("heart");
			for (int i = 0; i < s.getPopularity()/numOfUsers; i++) {
				if (!(i > 4)) {
					s.getNumOfHearts().add("heart");
				} else {
					break;
				}
			}
		}
		model.put("songTable", songTable);
		return "requestSong";
	}
	
	@GetMapping(value = "/accessDenied")
	public String accessDeniedPage(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		return "accessDenied";
	}

}
