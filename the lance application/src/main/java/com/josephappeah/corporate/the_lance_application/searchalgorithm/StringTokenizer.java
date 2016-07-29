package com.josephappeah.corporate.the_lance_application.searchalgorithm;

import java.io.FileInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import opennlp.tools.tokenize.TokenizerModel;

public class StringTokenizer {
	private static final Logger logger = LoggerFactory.getLogger(StringTokenizer.class);
	
	public void Tokenize(String statement) throws Exception{
		String pathtofile = System.getenv("API_HOME");
		
		InputStream modelIn = new FileInputStream("en-token.bin");

		try {
			TokenizerModel model = new TokenizerModel(modelIn);
		}catch (Exception e) {
			logger.error("Failed to initialize tokenizer",e);
		}
		finally {
			if (modelIn != null) {
	    try {
	      modelIn.close();
	    }
	    catch (Exception e) {
	    	logger.error("",e);
	    }
	    }
	  }
	}
}
