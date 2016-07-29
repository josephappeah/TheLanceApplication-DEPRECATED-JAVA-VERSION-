package com.josephappeah.corporate.the_lance_application.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.josephappeah.corporate.the_lance_application.resources.TheLanceApplicationServer;
import com.josephappeah.corporate.the_lance_application.services.ComputerVisionResource;
import com.josephappeah.corporate.the_lance_application.services.ImageSearchResource;
import com.josephappeah.corporate.the_lance_application.utils.ComputerVisionHandler;
import com.josephappeah.corporate.the_lance_application.utils.ImageSearchHandler;


public class TheLanceApplicationApp {
	private static final Logger logger = LoggerFactory.getLogger(TheLanceApplicationApp.class);

	public static void main(String[] args){
		
		ClassPathXmlApplicationContext 		ctx 		= null;
		
		try{
			logger.debug("Gathering required resources.");
			ctx = new ClassPathXmlApplicationContext("TheLanceApplication.xml");
		} catch (Exception e){
			logger.debug("Failed to gather resources. Initialization error: {}", e.getMessage(), e);
		}
		
		
		logger.debug("\n\n\n");
		logger.debug("-------------------------------");
		logger.debug("Launching The Lance Application");
		logger.debug("-------------------------------");
		logger.debug("\n\n\n");
		
		
		try{
			ImageSearchHandler 			ish		= (ImageSearchHandler) ctx.getBean("imagesearch");
			ComputerVisionHandler		cvh		= (ComputerVisionHandler) ctx.getBean("computervision");
			
			
			ImageSearchResource 		ims 	= (ImageSearchResource) ctx.getBean("imagesearchresource");
			ims.getImageSearchHandler(ish);
			
			ComputerVisionResource 		cvr 	= (ComputerVisionResource) ctx.getBean("computervisionresource");
			cvr.getComputerVisionHandler(cvh);
			
			logger.debug("Starting server from main.");
			TheLanceApplicationServer 	tlas 	= (TheLanceApplicationServer) ctx.getBean("lanceapplicationserver");
			tlas.startUp();
		}catch (Exception e){
			logger.debug("Failed to start server from main.",e);
		}
			

	}
}
