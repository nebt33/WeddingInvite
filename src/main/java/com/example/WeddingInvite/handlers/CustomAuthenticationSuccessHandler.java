package com.example.WeddingInvite.handlers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.WeddingInvite.model.AppUser;
import com.example.WeddingInvite.repo.AppUserRepository;

@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler  {
	
	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		resetUser(request.getParameter("user_name"));
		
		response.sendRedirect("about_us");
		
	}
	
	private void resetUser(String userName) {
		AppUser appUser = null;
		
		try {
			appUser = appUserRepository.findOne(userName);
		} catch (UsernameNotFoundException u) {
			System.out.println("User : " + userName + " not found!");
		}

		if (appUser != null) {
			appUser.setFailedLogins(0);
			appUser.setLocked(false);
			appUser.setLastLoginDate(new Date());
			appUserRepository.save(appUser);
		}
	}

}