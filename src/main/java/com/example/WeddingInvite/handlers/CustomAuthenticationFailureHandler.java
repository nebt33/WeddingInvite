package com.example.WeddingInvite.handlers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.example.WeddingInvite.model.AppUser;
import com.example.WeddingInvite.repo.AppUserRepository;

@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private String DEFAULT_FAILURE_URL = "/login?error";

	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		if (exception instanceof BadCredentialsException) {
			lockUser(request.getParameter("user_name"), response); 
		}

	}

	private void lockUser(String userName, HttpServletResponse response) throws IOException {
		AppUser appUser = null;
		
		try {
;			appUser = Optional.ofNullable(appUserRepository.findOne(userName)).orElseThrow(() -> new UsernameNotFoundException(userName));
		} catch (UsernameNotFoundException u) {
			System.out.println("User : " + userName + " not found!");
			response.sendRedirect("login?error=unknownUser");
		}

		if (appUser != null) {
			int failedCount = appUser.getFailedLogins() + 1;
			appUser.setFailedLogins(failedCount);

			if (failedCount > 4)
				appUser.setLocked(true);
				
			appUserRepository.save(appUser);
			
			if (failedCount > 4)
				response.sendRedirect("login?error=locked");
			else
				response.sendRedirect("login?error=badCred");
				
		}
	}

}