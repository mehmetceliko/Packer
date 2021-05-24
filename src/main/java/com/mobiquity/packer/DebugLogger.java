package com.mobiquity.packer;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DebugLogger {
	
	public static Logger logger = Logger.getLogger("com.mobiquity.packer.DebugLogger");	
  
	public static void setDebug(boolean isActive) {
		
		if (isActive) {
			ConsoleHandler consoleHandler = new ConsoleHandler();			
			consoleHandler.setFormatter(new SimpleFormatter() {
				private static final String format = "%3$s %n";//"[%1$tF %1$tT] [%2$-7s] %3$s %n";//"%4$s: %5$s [%1$tc]%n";//

				@Override
				public synchronized String format(LogRecord lr) {
					return String.format(format,
							new Date(lr.getMillis()),
							lr.getLevel().getLocalizedName(),
							lr.getMessage()
							);
				}
			});
			
			
			logger.setUseParentHandlers(false);			
			
		    logger.addHandler(consoleHandler);
		    logger.setLevel(Level.ALL);
		}else {
			logger.setLevel(Level.OFF);
		}
		
	}
	
	public static void debugLog(String message) {	 
		
		if (logger.getLevel().equals(Constants.logLevel)) {			
			logger.info(message); //System.out.println(message); 
		}
	}
	

}
