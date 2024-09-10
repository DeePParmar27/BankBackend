package com.aurionpro.project.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.project.service.ImageService;


@RestController
@RequestMapping("/app")
public class CloudinaryController {
	

	
	@Autowired
	ImageService imageservice ;
	
    @PostMapping("/upload/{userId}")
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file , @PathVariable int userId) {
        try {
            return imageservice.uploadImage(file , userId);
        } catch (IOException e) {
            e.printStackTrace();
            return Map.of("error", "Image upload failed");
        }
    }
	

}
