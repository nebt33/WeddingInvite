package com.example.WeddingInvite.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.WeddingInvite.model.AppUser;
import com.example.WeddingInvite.repo.AppUserRepo;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AppUserRepo appUserRepo;

	@Override
	public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {
		AppUser appUser = Optional.ofNullable(appUserRepo.findOne(user_name)).orElseThrow(() -> new UsernameNotFoundException(user_name));

		return new User(appUser.getUserName(), appUser.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("USER")));
	}

}
