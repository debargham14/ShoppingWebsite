package com.debargha.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cloudinary.utils.ObjectUtils;
import com.debargha.config.CloudinaryConfig;
import com.debargha.model.Apparel;
import com.debargha.model.ApparelDto;
import com.debargha.service.ApparelService;

@RestController
@RequestMapping("/admin/apparel")
public class ApparelController {

    private ApparelService apparelService;
    
    @Autowired
    CloudinaryConfig cloudc;
    
    public ApparelController(ApparelService apparelService) {
        this.apparelService = apparelService;
    }

    @PostMapping("")
    ModelAndView createApparel(@ModelAttribute("apparel") ApparelDto dto, @RequestParam("file")MultipartFile file)
    {
    	ModelAndView modelAndView = new ModelAndView();
    	if (file.isEmpty()) {
    		modelAndView.setViewName("redirect:/");
            //return "redirect:/";
    		return modelAndView;
    	}
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
              
              dto.setImageUrl(uploadResult.get("url").toString());
              
              Apparel apparel = apparelService.save(dto);
              
              if(apparel == null)
              {
                  //return "redirect:/admin?apparelError";
            	  modelAndView.setViewName("redirect:/admin?apparelError");
            	  return modelAndView;
              }
              System.out.println ("hi " + apparel.getImageUrl());
              //return "redirect:/admin?apparelSuccess";
              modelAndView.setViewName("redirect:/admin?apparelSuccess");
        	  return modelAndView;
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.setViewName("redirect:/");
            //return "redirect:/";
    		return modelAndView;
        }
    }

}
