package com.josephappeah.corporate.the_lance_application.utils;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailHandler {
	private static final Logger logger  = LoggerFactory.getLogger(EmailHandler.class);
	   public void SendEmail(String emailContent)
	   {   
		  logger.debug("Setting email properties");
		  
	      String to = "josephappeah@gmail.com";
	      String from = "josephappeah@gmail.com";
	      String host = "smtp.gmail.com";
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.smtp.host", host);
	      properties.put("mail.smtp.port", "587");
	      properties.put("mail.smtp.user","josephappeah@gmail.com"); 
	      properties.put("mail.debug", "true"); 
	      properties.put("mail.smtp.auth", "true"); 
	      properties.put("mail.smtp.starttls.enable","true"); 
	      properties.put("mail.smtp.EnableSSL.enable","true");

	      properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
	      properties.setProperty("mail.smtp.socketFactory.fallback", "false");   
	      properties.setProperty("mail.smtp.port", "465");   
	      properties.setProperty("mail.smtp.socketFactory.port", "465"); 
	      
	      Session session = Session.getInstance(properties,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("josephappeah@gmail.com", "babbatts");
				}
			  });

	      try{
	    	 logger.debug("Sending Email");
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         message.setSubject("Email From Lance Application Client");
	         message.setText(emailContent);
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException e) {
	    	 logger.error("Failed to send email",e);
	      }
	   }
}

