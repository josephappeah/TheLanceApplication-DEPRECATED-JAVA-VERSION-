package com.josephappeah.corporate.the_lance_application.services;


import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.the_lance_application.interfaces.GetImageMetadata;
import com.josephappeah.corporate.the_lance_application.interfaces.RestResource;
import com.josephappeah.corporate.the_lance_application.utils.ComputerVisionHandler;
import com.josephappeah.corporate.the_lance_application.utils.ImageSearchHandler;

@Path("/computervision")
public class ComputerVisionResource implements RestResource{
	public static final Logger logger = LoggerFactory.getLogger(ImageSearchResource.class);
	public static GetImageMetadata 	gimd 			= 	null;
	public static String 			searchresult	= 	null;
	
	@POST
	@Produces("application/json")
	
	@Override
	public Response returnMetadata(byte[] image){
		try{
			logger.debug("Eagerly obtaining imagesearch data");
			searchresult = gimd.execute(image);
		}catch(Exception e){
			logger.debug("Failed to obtain imagesearch data",e);
		}
		
		
		JSONObject jsonobject = null;
		JSONArray  jsonarray = null;
		JSONObject  captions  = null;
		
		try{
			logger.debug("Parsing computer vision json data.");
			logger.debug("{}",searchresult);
			jsonobject=  (JSONObject) new JSONObject(searchresult).get("description"); //jsonparser.parse(cv_data_recieved);
			jsonarray = jsonobject.getJSONArray("captions");
			captions  = (JSONObject) jsonarray.get(0);
		}catch(Exception e){
			logger.error("Failed to parse computer vision json data.",e);
		}

		logger.debug("{}",captions.get("text"));
		String imagedescription = captions.get("text").toString();
		logger.debug("Returning imagesearch data");
		return Response.status(200)
				.entity(imagedescription)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
				.build();
	}
	

	@Override
	public void getComputerVisionHandler(ComputerVisionHandler cvh) {
		gimd = cvh;
	}


	@Override
	public void getImageSearchHandler(ImageSearchHandler ish) {
			
	}


	@Override
	public void processRequest() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Response returnMetadata(String imagesearchquery) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
