package com.debargha.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;
import com.debargha.config.CloudinaryConfig;
import com.debargha.model.Apparel;
import com.debargha.model.ApparelDto;
import com.debargha.service.ApparelService;

@Controller
@RequestMapping("/admin/apparel")
public class ApparelController {

    private ApparelService apparelService;
    
    @Autowired
    CloudinaryConfig cloudc;
    
    public ApparelController(ApparelService apparelService) {
        this.apparelService = apparelService;
    }

    @PostMapping("")
    String createApparel(@ModelAttribute("apparel") ApparelDto dto, @RequestParam("file")MultipartFile file)
    {
    	if (file.isEmpty()) {
            return "redirect:/";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
              
              dto.setImageUrl(uploadResult.get("url").toString());
              
              Apparel apparel = apparelService.save(dto);
              
              if(apparel == null)
              {
                  return "redirect:/admin?apparelError";
              }
              System.out.println ("hi " + apparel.getImageUrl());
              return "redirect:/admin?apparelSuccess";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }

}
