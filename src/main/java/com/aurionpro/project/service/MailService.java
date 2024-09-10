package com.aurionpro.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.aurionpro.project.entity.MailStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender ;
	
	
	@Value("spring.mail.username")
	private String fromMail ;
	
	
	public void sendMail(String mail , MailStructure mailstructure) throws MessagingException {
	    MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

		
        helper.setFrom(fromMail);
        helper.setSubject(mailstructure.getSubject());
        helper.setText(mailstructure.getMessage());

		helper.setTo(mail);
		
         mailSender.send(message);		
		
		mailSender.send(message);
		
	}

}
