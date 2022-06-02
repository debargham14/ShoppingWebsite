package com.debargha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.debargha.model.Deal;
import com.debargha.model.DealDto;
import com.debargha.service.DealService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin/deal")
public class DealController {
    private final DealService dealService;


    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping("")
    ModelAndView createDeal(@ModelAttribute("deal") DealDto dto)
    {
        ModelAndView modelAndView = new ModelAndView();
    	if(dto.getValidFrom() == null || dto.getValidFrom().isEmpty())
        {
            dto.setValidFrom(LocalDateTime.now().toString());
        }

        Deal deal = dealService.save(dto);
        if(deal == null)
        {
            //return "redirect:/admin?dealError";
        	modelAndView.setViewName("redirect:/admin?dealError");
    		return modelAndView;
        }
        //return "redirect:/admin?dealSuccess";
        modelAndView.setViewName("redirect:/admin?dealSuccess");
		return modelAndView;
    }


}
