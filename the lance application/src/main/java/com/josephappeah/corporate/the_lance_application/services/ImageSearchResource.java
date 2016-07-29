package com.josephappeah.corporate.the_lance_application.services;


import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.the_lance_application.interfaces.GetImageMetadata;
import com.josephappeah.corporate.the_lance_application.interfaces.RestResource;
import com.josephappeah.corporate.the_lance_application.utils.ComputerVisionHandler;
import com.josephappeah.corporate.the_lance_application.utils.ImageSearchHandler;

@Path("/imagesearch")
public class ImageSearchResource implements RestResource{
	public static final Logger logger = LoggerFactory.getLogger(ImageSearchResource.class);
	public static GetImageMetadata 	gimd 			= 	null;
	public static String 			searchresult	= 	null;
	
	@GET
	@OPTIONS
	@Produces("application/json")
	
	@Override
	public Response returnMetadata(@QueryParam("query") String imagesearchquery){
		if(imagesearchquery.split(" ").length > 4){
			
		}
		
		
		try{
			logger.debug("Eagerly obtaining imagesearch data");
			searchresult = gimd.execute(imagesearchquery);
		}catch(Exception e){
			logger.debug("Failed to obtain imagesearch data",e);
		}
		
		logger.debug("Returning imagesearch data");
		return Response.status(200)
				.entity(searchresult)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
				.build();
	}
	


	@Override
	public void getComputerVisionHandler(ComputerVisionHandler cvh) {
		// TODO Auto-generated method stub
	}


	@Override
	public void getImageSearchHandler(ImageSearchHandler ish) {
		gimd = ish;	
	}


	@Override
	public void processRequest() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Response returnMetadata(byte[] image) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
