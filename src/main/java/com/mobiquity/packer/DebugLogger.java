package com.mobiquity.packer;

import java.util.logging.Logger;

public class DebugLogger {
	public static Logger logger = Logger.getLogger("com.mobiquity.packer.DebugLogger");
	
	
	
	public static void LogDebugInfo(String message) {
		
		 
		logger.fine(message);
		
		if (logger.getLevel().equals(Constants.logLevel)) {
			System.out.println(message);
		}
	}
	

}
