package com.example.WeddingInvite.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
//@EnableWebMvc
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    
    public void addViewControllers (ViewControllerRegistry viewControllerRegistry) {
    	viewControllerRegistry.addViewController("login").setViewName("login");
    	viewControllerRegistry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    	viewControllerRegistry.addViewController("/").setViewName("forward:/login");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
    	registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
    	registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
