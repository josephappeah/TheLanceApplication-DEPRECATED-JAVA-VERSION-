package com.josephappeah.corporate.the_lance_application.resources;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.the_lance_application.utils.DependencyHandler;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;

public class TheLanceApplicationServer {
	private static final Logger logger = LoggerFactory.getLogger(TheLanceApplicationServer.class);
	private static Integer		portnumber		= null;
	UndertowJaxrsServer 		server 			= new UndertowJaxrsServer();
	
	public TheLanceApplicationServer(DependencyHandler di){
		portnumber = di.getPortNumber();
	}
	
	public void startUp(){
		try{
			logger.debug("++++++++++++++++++++++++++++++++++++++++++++++++++");
			logger.debug("+ Starting up Lance Application undertow server. +");
			logger.debug("++++++++++++++++++++++++++++++++++++++++++++++++++");
			
	    	Undertow.Builder 	builder 		= Undertow.builder().addHttpListener(portnumber, "0.0.0.0");
	    	
	    	logger.debug("Creating homepage servlet.");
	    	DeploymentInfo 		servletbuilder 	= Servlets.deployment()
	        		.setClassLoader(Thread.currentThread().getContextClassLoader())
	        	    .setContextPath("/")
	        	    .setDeploymentName("The-Lance-Application-Homepage")
	        	    .setResourceManager(new ClassPathResourceManager(Thread.currentThread().getContextClassLoader()))
	        	    .addWelcomePage("index.html");
	    	
	    	logger.debug("Starting resource and servlet deployment.");
	    	server.deploy(TheLanceApplication.class);
	    	server.deploy(servletbuilder);
	    	
	    	logger.debug("Deploying Server.");
	    	server.start(builder);
	    	logger.debug("Deployment Complete. Listening on port:{}",portnumber);
	    	
		}catch(Exception e){
			logger.debug("Failed to start up Lance Application undertow server",e);
		}
	}
	
	
	public void shutDown(){
		try{
			logger.debug("++++++++++++++++++++++++++++++++++++++++++++++++++++");
			logger.debug("+ Shutting down Lance Application undertow server. +");
			logger.debug("++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			server.stop();
			
		}catch(Exception e){
			logger.debug("Failed to shut down Lance Application undertow server");
		}
	}

}
