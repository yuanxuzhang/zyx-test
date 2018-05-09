package com.zyx.exception.logger;

import java.util.logging.Logger;

public class LoggerTest {

	public static void main(String[] args) {
		
		java.util.logging.Logger logger = Logger.getLogger("getClass().getSimpleName().toLowerCase().toString()");
		logger.info("XXX");
		
		Logger.global.info("XXX");
	
	}

}
