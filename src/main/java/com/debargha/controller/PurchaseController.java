package com.debargha.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.debargha.model.Purchase;
import com.debargha.model.PurchaseDto;
import com.debargha.predictorservice.Statistics;
import com.debargha.service.PurchaseService;
import com.debargha.service.UserService;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final UserService userService;


    PurchaseController(PurchaseService purchaseService, UserService userService)
    {
        this.purchaseService = purchaseService;
        this.userService = userService;
    }

    @GetMapping("/user/purchases")
    ModelAndView purchaseList(@RequestParam(required = false) String q, Model model, Authentication auth)
    {
        User user = (User) auth.getPrincipal();
        List<PurchaseDto> purchases = userService
                .getPurchases(user.getUsername())
                .stream()
                .map(PurchaseDto::new)
                .collect(Collectors.toList());
        if(q != null && !q.isEmpty())
        {
            purchases = purchases.stream().filter(purchase -> purchase.getApparel().getGenericName().toLowerCase(Locale.ROOT).startsWith(q.toLowerCase(Locale.ROOT)))
            		.collect(Collectors.toList());
        }
        model.addAttribute("purchases", purchases);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("purchases");
       
        //return "purchases";
        
        return modelAndView;
    }

    @PostMapping("/user/purchase")
    ModelAndView purchase(@RequestParam UUID apparelId, @SessionAttribute Statistics statistics, Authentication auth)
    {
        User user = (User) auth.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        System.out.println ("heloooooooo" + apparelId);
        if(apparelId == null) {
        	modelAndView.setViewName("redirect:/error");
            //return "redirect:/error";
        	return modelAndView;
        }
        Purchase purchase = purchaseService.purchase(apparelId, user.getUsername());
        statistics.update(purchase.getApparel().getPrice());
        //return "redirect:/user/purchases";
        modelAndView.setViewName("redirect:/user/purchases");
    	return modelAndView;
    }
}
