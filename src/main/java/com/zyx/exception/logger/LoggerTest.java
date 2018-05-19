package com.zyx.exception.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerTest {

	public static void main(String[] args) {
		
		//java.util.logging.Logger logger = Logger.getLogger("getClass().getSimpleName().toLowerCase().toString()");
		//Logger.global.setLevel(Level.SEVERE);
		//logger.setLevel(Level.SEVERE);
		//logger.info("XXX");
		
		//logger.info(logger.getName());
		
		
		/*Logger.global.setLevel(Level.INFO);
		Logger.global.info("XXX");*/
		
		/*try {
			Integer.parseInt("q");
		} catch (NumberFormatException e) {
			
			// 捕获并打印系统异常信息
			//logger.throwing("sourceClass", "sourceMethod", e);
			logger.log(Level.INFO, "sourceMethod", e);
		}*/
		
		/*
		 *  默认的父日志记录器
		 *  五月 19, 2018 8:44:36 下午 java.util.logging.LogManager$RootLogger log
		 *  信息:
		 * */
		Logger parentLogger = Logger.getLogger("");
		parentLogger.setLevel(Level.SEVERE);
		parentLogger.info(parentLogger.getName());
		
		/* 
		         五月 19, 2018 8:44:37 下午 com.zyx.exception.logger.LoggerTest main
		         信息: child
		         五月 19, 2018 8:44:37 下午 com.zyx.exception.logger.LoggerTest main
		         信息: 
		   Exception in thread "main" java.lang.NullPointerException
		   at com.zyx.exception.logger.LoggerTest.main(LoggerTest.java:39)
		 * */
		Logger childLogger = Logger.getLogger("child");
		childLogger.setLevel(Level.INFO);
		childLogger.info(childLogger.getName());
		childLogger.info(childLogger.getParent().getName());
		//childLogger.info(childLogger.getParent().getParent().getName());
		
	}

}
