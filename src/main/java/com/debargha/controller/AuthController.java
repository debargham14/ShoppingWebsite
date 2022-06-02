package com.debargha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.debargha.model.UserDto;
import com.debargha.service.UserService;

@RestController
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    UserDto userDto()
    {
        return new UserDto();
    }
    
    @RequestMapping("/")
    public ModelAndView homepage() {
    	//return "home";
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("home");
    	return modelAndView;
    }
    
    @GetMapping("/login")
    public ModelAndView loginForm() {
        //return "login";
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("login");
    	return modelAndView;
    }   
    
    @GetMapping("/loginadmin")
    public ModelAndView loginFormAdmin() {
        //return "loginadmin";
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("loginadmin");
    	return modelAndView;
    }

    @GetMapping("/signup")
    public ModelAndView signupForm() {
        //return "signup";
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("signup");
    	return modelAndView;
    }
    
    @GetMapping("/home")
    public ModelAndView home() {
        //return "temphome";
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("temphome");
    	return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("user") UserDto dto) {
        ModelAndView modelAndView = new ModelAndView();
        
    	if(userService.isRegistered(dto)) {
    		//return "redirect:/signup?error";
    		modelAndView.setViewName("redirect:/signup?error");
    		return modelAndView;
    	}
        userService.save(dto);
        //return "redirect:/signup?success";
        modelAndView.setViewName("redirect:/signup?success");
		return modelAndView;
    }
}
