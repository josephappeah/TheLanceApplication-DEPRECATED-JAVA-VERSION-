package com.josephappeah.corporate.the_lance_application.interfaces;

public interface GetImageMetadata {
	public void setUpURLConnnection(String query);
	public void getRequestResponseStream();
	public void analyseResponseStream();
	public String execute(String imagesearchquery);
	public void setUpURLConnnection(byte[] image);
	public String execute(byte[] image);
}
