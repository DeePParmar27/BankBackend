package com.aurionpro.project.service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.project.entity.Customer;
import com.aurionpro.project.entity.Image;
import com.aurionpro.project.repository.CustomerRepository;
import com.aurionpro.project.repository.ImageRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageService {
	
	@Autowired
	ImageRepository imageRepository ;
	
	@Autowired
	CustomerRepository repo ;
	
	
	@Autowired
	Cloudinary cloud ;

	
    public Map<String, Object> uploadImage(MultipartFile file , int userId) throws IOException {
    
        Map<String, Object> uploadResult = cloud.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        Optional<Customer> userdb = repo.findByCustomerId(userId);
        Customer user = userdb.get();
        Image image = new Image();
        image.setUrl((String) uploadResult.get("url"));
        image.setImageid((String) uploadResult.get("public_id"));
        image.setUser(user);
        imageRepository.save(image);

        return uploadResult;
    }
}
