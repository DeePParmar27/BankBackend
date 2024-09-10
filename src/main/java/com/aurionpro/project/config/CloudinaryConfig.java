package com.aurionpro.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
	
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
        		"cloud_name", "dj69qvihn",
        		"api_key", "195794611771292",
        		"api_secret", "TNMXlfOaU0irIkj1VzD5P1cHVtc"
        ));
    }

}
