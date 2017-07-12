package com.example.WeddingInvite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
/*@Configuration
@ComponentScan
@EnableAutoConfiguration
@RestController*/
public class WeddingInviteApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeddingInviteApplication.class, args);
	}

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WeddingInviteApplication.class);
	}
	
	@RequestMapping(value="/")
	public String demo() {
		return "Hello ....";
	}*/
	
}
