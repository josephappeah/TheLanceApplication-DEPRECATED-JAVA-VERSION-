package com.josephappeah.corporate.the_lance_application.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.josephappeah.corporate.the_lance_application.services.ComputerVisionResource;
import com.josephappeah.corporate.the_lance_application.services.EmailSenderResource;
import com.josephappeah.corporate.the_lance_application.services.ImageSearchResource;

@ApplicationPath("/the_lance_application")
public class TheLanceApplication extends Application{
	@Override
	public Set<Class<?>> getClasses() {
            HashSet<Class<?>> classes = new HashSet<Class<?>>();
            classes.add(ComputerVisionResource.class);
            classes.add(ImageSearchResource.class);
            classes.add(EmailSenderResource.class);
            return classes;
	}
}
