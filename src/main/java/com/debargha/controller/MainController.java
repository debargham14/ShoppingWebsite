package com.debargha.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.debargha.model.Apparel;
import com.debargha.model.ApparelDto;
import com.debargha.model.Purchase;
import com.debargha.predictorservice.Statistics;
import com.debargha.service.ApparelService;
import com.debargha.service.DealService;
import com.debargha.service.UserService;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@RestController
public class MainController {
    private final ApparelService apparelService;
    private final DealService dealService;
    private final UserService userService;

    public MainController(ApparelService apparelService, DealService dealService, UserService userService) {
        this.apparelService = apparelService;
        this.dealService = dealService;
        this.userService = userService;
    }

    @GetMapping("/user")
    public ModelAndView home(@RequestParam(required = false) String q, Authentication auth, Model model, HttpSession session) {
        Statistics statistics = (Statistics) session.getAttribute("statistics");
        User user = (User) auth.getPrincipal();

        if (statistics == null) {
            statistics = new Statistics();
            userService.getPurchases(user.getUsername()).stream().map(Purchase::getApparel).mapToDouble(Apparel::getPrice).forEach(statistics::update);
            session.setAttribute("statistics", statistics);
        }

        if (q == null || q.isEmpty()) {
        	List<Apparel> userSpecificApparelList = apparelService.listApparel(user.getUsername());
        	
        	List<ApparelDto> apparelList = new ArrayList<ApparelDto>();
        	
        	for (Apparel apparel : userSpecificApparelList) {
        		ApparelDto apparelDto = new ApparelDto (apparel);
        		apparelDto.setDiscountedPrice(dealService.getDiscountedPrice(apparelDto.getId()));
        		apparelList.add(apparelDto);
        	}
      
            model.addAttribute("apparelList", sortAccordingToMetric(apparelList, statistics));
        } else {
        	List<Apparel> userSpecificApparelList = apparelService.listApparel(user.getUsername());
        	
        	List<ApparelDto> apparelList = new ArrayList<ApparelDto>();
        	
        	for (Apparel apparel : userSpecificApparelList) {
        		if (apparel.getGenericName().toLowerCase(Locale.ROOT).startsWith(q.toLowerCase(Locale.ROOT))) {
	        		ApparelDto apparelDto = new ApparelDto (apparel);
	        		apparelDto.setDiscountedPrice(dealService.getDiscountedPrice(apparelDto.getId()));
	        		apparelList.add(apparelDto);
        		}
        	}
            model.addAttribute("apparelList", sortAccordingToMetric(apparelList, statistics));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        //return "index";
        return modelAndView;
    }

    private List<ApparelDto> sortAccordingToMetric(List<ApparelDto> apparels, Statistics statistics) {

        apparels.sort((apparel1, apparel2) -> {
            double left = statistics.getMean() - statistics.getStandardDeviation();
            double right = statistics.getMean() + statistics.getStandardDeviation();
            if (apparel1.getPrice() >= left && apparel1.getPrice() <= right && apparel2.getPrice() >= left && apparel2.getPrice() <= right) {
                return Double.compare(apparel1.getPrice(), apparel2.getPrice());
            } else if (apparel1.getPrice() >= left && apparel1.getPrice() <= right) {
                return -1;
            } else if (apparel2.getPrice() >= left && apparel2.getPrice() <= right) {
                return 1;
            } else {
                return Double.compare(Math.abs(apparel1.getPrice() - statistics.getMean()), Math.abs(apparel2.getPrice() - statistics.getMean()));
            }
        });

        return apparels;
    }
}
