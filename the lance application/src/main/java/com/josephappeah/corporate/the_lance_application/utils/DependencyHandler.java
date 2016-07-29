/*
 * 
 * author: josephappeah
 * 
 * 
 * */

package com.josephappeah.corporate.the_lance_application.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.the_lance_application.interfaces.DependencyInjector;

public class DependencyHandler implements DependencyInjector{
	
	private static final Logger logger = LoggerFactory.getLogger(DependencyHandler.class);
	
	private static String propertiesPath    = System.getenv("API_HOME")+File.separator+"the_lance_application.properties";
	private static String computervisionkey = null;
	private static String imagesearchkey    = null;
	private static String computervisionurl = null;
	private static Integer portnumber 		= null;
	private static String imagesearchurl    = null;
	
	Properties props = new Properties();
	
	public DependencyHandler(){
		logger.debug("Loading Properties from {}",propertiesPath);
		try{
			props.load(new FileInputStream(propertiesPath));
		}catch(Exception e){
			logger.debug("Failed to Load Properties File");
		}
		
		logger.debug("xxxxxxxxxxxxxxxxxxxx Properties Loaded xxxxxxxxxxxxxxxxxxxx");
		//props.forEach((key,value) -> logger.info("{} : {}",key,value));
		logger.debug("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		
		logger.debug("Assigning properties to values");
		try{
			computervisionkey	= (String) props.get("computer_vision_key");
			imagesearchkey 		= (String) props.get("image_search_key");
			computervisionurl 	= (String) props.get("computer_vision_url");
			portnumber 			= Integer.parseInt(props.get("port_number").toString());
			imagesearchurl 		= (String) props.get("image_search_url");
			
			
		}catch(Exception e){
			logger.debug("Failed to Assign properties to values");
		}
	}
	
	
	@Override
	public String getComputerVisionKey() {
		return computervisionkey;
	}
	
	@Override
	public String getImageSearchKey() {
		return imagesearchkey;
	}
	
	@Override
	public String getComputerVisionURL() {
		return computervisionurl;
	}

	@Override
	public String getImageSearchURL() {
		return imagesearchurl;
	}

	@Override
	public Integer getPortNumber() {
		return portnumber;
	}


}
