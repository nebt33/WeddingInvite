package com.example.WeddingInvite.controllers;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.security.crypto.bcrypt.BCrypt;
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
		
		if (error != null && error.equalsIgnoreCase("badCred")) {
	        model.put("badCred", "Invalid username and/or password!");
	    } else if (error != null && error.equalsIgnoreCase("locked")) {
	    	model.put("locked", "Your Account is Locked!");
	    }
		
		return "login";
	}
	
	@GetMapping(value="/about_us")
	public String about_us(Map<String, Object> model) {
		model.put("title", TITLE);
		return "about_us";
	}
	
	@GetMapping(value="/ceremony")
	public String ceremony(Map<String, Object> model) {
		model.put("title", TITLE);
		return "ceremony";
	}
	
	@GetMapping(value="/reception")
	public String reception(Map<String, Object> model) {
		model.put("title", TITLE);
		return "reception";
	}
	
	@PostMapping(value = "/addSongPost")
	public String addSongPost(@ModelAttribute Song song, Map<String, Object> model) {
		model.put("title", TITLE);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		song.setSubmittedBy(auth.getName());
	    song.setSubmittedByDate(new Date());  
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

		Pageable pageable = new PageRequest(0,1000,
				Direction.ASC, "title", "artist");

		Page<Song> songTable = songRepoistory.findAll(pageable);
		
		model.put("songTable", songTable);
		return "requestSong";
	}
	
	@GetMapping(value="/registry")
	public String registry(Map<String, Object> model) {
		model.put("title", TITLE);
		return "registry";
	}
	
	@GetMapping(value="/the_cast")
	public String the_cast(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		return "the_cast";
	}
	
	@GetMapping(value="/my_account")
	public String my_account(Map<String, Object> model) {
		model.put("title", TITLE);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser appUser = appUserRepository.findOne(auth.getName());
		
		model.put("appUserName", appUser.getUserName());
		model.put("appUserRole", appUser.getRole());
		
		return "my_account";
	}
	
	@GetMapping(value="/my_account_change_password")
	public String my_account_change_password(Map<String, Object> model) {
		model.put("title", TITLE);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser appUser = appUserRepository.findOne(auth.getName());
		
		model.put("appUserName", appUser.getUserName());
		model.put("appUserRole", appUser.getRole());
		
		return "my_account_change_password";
	}
	
	@PostMapping(value="/my_account_change_password")
	public String my_account_change_password(@RequestParam(value = "newPassword", required = false) String newPassword, Map<String, Object> model) {
		model.put("title", TITLE);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser appUser = appUserRepository.findOne(auth.getName());
		
		model.put("appUserName", appUser.getUserName());
		model.put("appUserRole", appUser.getRole());
		
		String salt = BCrypt.gensalt(12);
		String hashed_password = BCrypt.hashpw(newPassword, salt);
		
		appUser.setPassword(hashed_password);
		appUserRepository.save(appUser);
		
		return "my_account_change_password";
	}
	
	@GetMapping(value="/my_account_change_email")
	public String my_account_change_email(Map<String, Object> model) {
		model.put("title", TITLE);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser appUser = appUserRepository.findOne(auth.getName());
		
		model.put("appUserName", appUser.getUserName());
		model.put("appUserRole", appUser.getRole());
		model.put("appUserEmail", appUser.getEmail());
		
		return "my_account_change_email";
	}
	
	@PostMapping(value="/my_account_change_email")
	public String my_account_change_email(@RequestParam(value = "newEmail", required = false) String newEmail, Map<String, Object> model) {
		model.put("title", TITLE);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser appUser = appUserRepository.findOne(auth.getName());
		
		model.put("appUserName", appUser.getUserName());
		model.put("appUserRole", appUser.getRole());
		
		appUser.setEmail(newEmail);
		appUserRepository.save(appUser);
		
		model.put("appUserEmail", appUser.getEmail());
		
		return "my_account_change_email";
	}
	
	@GetMapping(value="/my_account_change_address")
	public String my_account_change_address(Map<String, Object> model) {
		model.put("title", TITLE);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser appUser = appUserRepository.findOne(auth.getName());
		
		model.put("appUserName", appUser.getUserName());
		model.put("appUserRole", appUser.getRole());
		model.put("appUserAddress", appUser.getAddress());
		
		return "my_account_change_address";
	}
	
	@PostMapping(value="/my_account_change_address")
	public String my_account_change_address(@RequestParam(value = "newAddress", required = false) String newAddress, Map<String, Object> model) {
		model.put("title", TITLE);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser appUser = appUserRepository.findOne(auth.getName());
		
		model.put("appUserName", appUser.getUserName());
		model.put("appUserRole", appUser.getRole());
		
		appUser.setAddress(newAddress);
		appUserRepository.save(appUser);
		
		model.put("appUserAddress", appUser.getAddress());
		
		return "my_account_change_address";
	}
	
	@GetMapping(value="/my_account_email_reminders")
	public String my_account_email_reminders(Map<String, Object> model) {
		model.put("title", TITLE);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser appUser = appUserRepository.findOne(auth.getName());
		
		model.put("appUserName", appUser.getUserName());
		model.put("appUserRole", appUser.getRole());
		model.put("appUserEmailReminder", appUser.getEmailReminder());
		
		return "my_account_email_reminders";
	}
	
	@PostMapping(value="/my_account_email_reminders")
	public String my_account_email_reminders(@RequestParam(value = "checkboxID", required = false) String checkboxID, Map<String, Object> model) {
		model.put("title", TITLE);
			
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser appUser = appUserRepository.findOne(auth.getName());
		
		model.put("appUserName", appUser.getUserName());
		model.put("appUserRole", appUser.getRole());
		
		if (checkboxID == null)
			appUser.setEmailReminder(false);
		else
			appUser.setEmailReminder(true);
		appUserRepository.save(appUser);
		
		model.put("appUserEmailReminder", appUser.getEmailReminder());
		
		return "my_account_email_reminders";
	}

}
