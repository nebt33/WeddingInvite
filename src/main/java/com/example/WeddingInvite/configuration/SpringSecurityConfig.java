package com.example.WeddingInvite.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.WeddingInvite.handlers.CustomAuthenticationFailureHandler;
import com.example.WeddingInvite.handlers.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                	.antMatchers("/webjars/**", "/css/**", "/js/**", "/img/**", "/login**").permitAll()
                	.anyRequest().authenticated()
					.and()
                .formLogin()
					.loginPage("/login")
					.usernameParameter("user_name")
					.passwordParameter("password")
					.defaultSuccessUrl("/about_us")
					.permitAll()
					.failureHandler(customAuthenticationFailureHandler)
					.successHandler(customAuthenticationSuccessHandler)
					.and()
		        .logout().logoutSuccessUrl("/login?logout");
    }

    @Autowired
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}