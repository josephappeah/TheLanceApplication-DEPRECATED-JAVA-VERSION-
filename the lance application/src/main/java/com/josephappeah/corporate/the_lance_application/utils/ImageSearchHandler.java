package com.josephappeah.corporate.the_lance_application.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
//import org.json.JSONArray;
//import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.the_lance_application.interfaces.GetImageMetadata;


public class ImageSearchHandler implements GetImageMetadata{
	
	private static final Logger logger = LoggerFactory.getLogger(ImageSearchHandler.class);
	private static String 				imagesearchurl 		= null;
	private static String 				imagesearchkey  	= null;
	public  static String[]				urlcomponents		= null;
	private static URL 					url 				= null;
	private static String 				queryurl			= null;
	private static HttpURLConnection 	connection 			= null;
	private static InputStream			responsestream		= null;
	private static String 				responsedata		= null;
	//private static ArrayList<String>	imageurlstorage		= null;
	//private static JSONArray 			responsemetadata  	= null;
	
	
	public ImageSearchHandler(DependencyHandler di){
		imagesearchkey	= di.getImageSearchKey();
		imagesearchurl 	= di.getImageSearchURL();
		urlcomponents	= imagesearchurl.split("QUERYPARAMGOESHERE");
	}
		
	
	@Override
	public void setUpURLConnnection(String query) {
		
		queryurl = urlcomponents[0] + query + urlcomponents[1];
		try{
			logger.debug("Creating request url for query '{}'",query);
			url = new URL(queryurl);
		} catch(Exception e){
			logger.debug("failed to create request url",e);
		}

		
		try{
			logger.debug("Setting up httpurlconnection.");
			connection = (HttpURLConnection) url.openConnection();
		}catch (Exception e){
			logger.debug("failed to set up httpurlconnection.",e);
		}
		
		
		try{
			logger.debug("Configuring connection headers.");
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Ocp-Apim-Subscription-Key",imagesearchkey);	
		}catch(Exception e){
			logger.debug("Failed to configure connection headers.",e);
		}
		
	}
	
	
	
	@Override
	public void getRequestResponseStream(){
		try{
			logger.debug("Eagerly obtaining response data.");
			responsestream = connection.getInputStream();
		}catch(Exception e){
			logger.debug("Failed to get response data.",e);
		}
		
	}

	
	@Override
	public void analyseResponseStream() {
		
		try{
			logger.debug("Converting response data to string.");
			responsedata = IOUtils.toString(responsestream);
		} catch(Exception e){
			logger.debug("Failed to convert response data to string.",e);
		}
		
		
		//Returns an arraylist
		/*
		try{
            logger.debug("Parsing response data input to json object");
            responsemetadata = (JSONArray) new JSONObject(responsedata).get("value");
        }catch(Exception e){
        	logger.debug("Failed to parse response data input to json object",e);
		}
        
		
		try{
            logger.debug("Eagerly obtaining image urls from response data");
            for(int count = 0; count < responsemetadata.length();count++){
                JSONObject thumbnail = (JSONObject) responsemetadata.get(count);
                imageurlstorage.add((String) thumbnail.get("thumbnailUrl"));
            }
		}catch(Exception e){
			logger.debug("Failed to obtain image urls from response data",e);
		}*/
                    

		
	}


	@Override
	public String execute(String imagesearchquery) {
		setUpURLConnnection(imagesearchquery);
		getRequestResponseStream();
		analyseResponseStream();
		
		try{
			logger.debug("Returning image search data as arraylist.");
			return responsedata;
		}catch (Exception e){
			logger.debug("Failed to return image search data as arraylist.");
		}
		return null;
	}


	@Override
	public void setUpURLConnnection(byte[] image) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String execute(byte[] image) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
