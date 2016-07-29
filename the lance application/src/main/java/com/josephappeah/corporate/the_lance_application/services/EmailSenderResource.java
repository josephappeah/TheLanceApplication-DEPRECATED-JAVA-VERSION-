package com.josephappeah.corporate.the_lance_application.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.the_lance_application.utils.EmailHandler;

@Path("/email")
public class EmailSenderResource {
private static final Logger logger = LoggerFactory.getLogger(EmailSenderResource.class);

@POST
@OPTIONS
@Consumes("text/plain")
public Response sendEmail(String emailcontent){
	EmailHandler eh = new EmailHandler();
	try{
		logger.debug("Attempting to pass recieved content to email handler");
		eh.SendEmail(emailcontent);
	}catch(Exception e){
		logger.error("Failed to send email",e);
	}
	return Response.status(200).entity("Done")
			.header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
			.build();
}
}
