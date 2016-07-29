package com.josephappeah.corporate.the_lance_application.interfaces;

import javax.ws.rs.core.Response;

import com.josephappeah.corporate.the_lance_application.utils.ComputerVisionHandler;
import com.josephappeah.corporate.the_lance_application.utils.ImageSearchHandler;

public interface RestResource {
	public Response returnMetadata(String imagesearchquery);
	public Response returnMetadata(byte[] imagebytes);
	public void getComputerVisionHandler(ComputerVisionHandler cvh);
	public void getImageSearchHandler(ImageSearchHandler ish);
	public void processRequest();
}
