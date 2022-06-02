//package com.debargha;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = { "com.debargha" })
//public class WebMvcConfig extends WebMvcConfigurerAdapter {
//
//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        //resolver.setViewClass(JstlView.class);
//        resolver.setPrefix("/src/main/resources/templates/");
//        resolver.setSuffix(".html");
//        return resolver;
//    }
//}