package com.debargha.config;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.Transformation;

@Component
public class CloudinaryConfig {

	private Cloudinary cloudinary;
    @Autowired
    public CloudinaryConfig(@Value("${cloud.key}") String key,
                            @Value("${cloud.secret}") String secret,
                            @Value("${cloud.name}") String cloud) {
        cloudinary = Singleton.getCloudinary();
        cloudinary.config.cloudName=cloud;
        cloudinary.config.apiSecret=secret;
        cloudinary.config.apiKey=key;
    }

    public Map upload(Object file, Map options) {
        try {
            return cloudinary.uploader().upload(file, options);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createUrl(String name, int width, int height, String action) {
        return cloudinary.url()
                .transformation(new Transformation()
                .width(width).height(height)
                .border("2px _solid _black").crop(action))
                .imageTag(name);
    }
}
