package com.report.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.MimeMessage;

public class EmailUtils {

	@Autowired
    private JavaMailSender emailSender;

    public boolean sendSimpleMessage(
      String to, String subject, String text,File f) {
        
       try {
    	   MimeMessage msg=emailSender.createMimeMessage();
    	   MimeMessageHelper help=new MimeMessageHelper(msg);
    	   help.setSubject(subject);
    	   help.setText(text, true);
    	   help.setTo(to);
    	   help.addAttachment("Citizen Plan Info", f);
    	   emailSender.send(msg);
    	   
       }catch(Exception e) {
    	   e.printStackTrace();
       }
       return true;
       
    }
}
