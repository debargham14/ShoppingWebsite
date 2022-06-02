package com.debargha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.debargha.model.Apparel;
import com.debargha.model.ApparelDto;
import com.debargha.model.DealDto;
import com.debargha.model.UserDto;
import com.debargha.service.ApparelService;
import com.debargha.service.DealService;
import com.debargha.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin")
public class AdminController {
    private final UserService userService;
    private final ApparelService apparelService;
    private final DealService dealService;

    public AdminController(UserService userService, ApparelService apparelService, DealService dealService) {
        this.userService = userService;
        this.apparelService = apparelService;
        this.dealService = dealService;
    }

    @ModelAttribute("user")
    UserDto userDto() {
        return new UserDto();
    }

    @ModelAttribute("apparel")
    ApparelDto apparelDto()
    {
        return new ApparelDto();
    }

    @ModelAttribute("deal")
    DealDto dealDto()
    {
        return new DealDto();
    }

    @GetMapping("/signup")
    public ModelAndView signupForm() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("signup");
    	return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("user") UserDto dto) {
        
    	ModelAndView modelAndView = new ModelAndView();
    	if (userService.isRegistered(dto)) {
            //return "redirect:/admin/signup?error";
    		modelAndView.setViewName("redirect:/admin/signup?error");
    		return modelAndView;
    	}
        userService.saveAdmin(dto);
        modelAndView.setViewName("redirect:/admin/signup?success");
        //return "redirect:/admin/signup?success";
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView adminPanel(@RequestParam(required = false) String queryString, Model model) {
      
    	if (queryString == null || queryString.isEmpty()) {
        	List<Apparel> apparelList = new ArrayList<Apparel>();
        	
        	for (Apparel apparel : apparelService.listApparel()) {
        		ApparelDto apparelDto = new ApparelDto (apparel);
        		
        		apparelDto.setDiscountedPrice(dealService.getDiscountedPrice(apparelDto.getId()));
        		apparel.setPrice(apparelDto.getDiscountedPrice());
        		
        		apparelList.add(apparel);
        	}
        	model.addAttribute("apparelList", apparelList);
        }
        else {
        	List<Apparel> apparelList = new ArrayList<Apparel>();
        	
        	for (Apparel apparel : apparelService.listApparel()) {
        		if (apparel.getGenericName().toLowerCase(Locale.ROOT).startsWith(queryString.toLowerCase(Locale.ROOT))) {
	        		ApparelDto apparelDto = new ApparelDto (apparel);
	        		
	        		apparelDto.setDiscountedPrice(dealService.getDiscountedPrice(apparelDto.getId()));
	        		apparel.setPrice(apparelDto.getDiscountedPrice());
	        		
	        		apparelList.add(apparel);
        		}
        	}
        	model.addAttribute("apparelList", apparelList);        }
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("admin");
        return modelAndView;
    }

}
