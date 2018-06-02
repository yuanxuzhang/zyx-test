package com.zyx.collection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 属性映射（property map）是一种存储键/值对的数据结构。属性映射经常被用来存放【配置信息】。
 * 键和值都是字符串
 * 键/值对可以很容易地写入文件或从文件读出
 * 使用二级表存储默认值
 */
public class PropertyTest {

	public static void main(String[] args) {

		String userDir = System.getProperty("user.home");
		System.out.println(userDir);
		File propertiesDir = new File(userDir);
		if(!propertiesDir.exists()){
			propertiesDir.mkdir();
		}
		File propertiesFile = new File(propertiesDir,"program.properties");
		
		Properties defaultSetting = new Properties();
		defaultSetting.put("left", "0");
		defaultSetting.put("top", "0");
		defaultSetting.put("width", "0");
		defaultSetting.put("height", "0");
		defaultSetting.put("title", "0");
		
		Properties settings = new Properties(defaultSetting);
		
		if(propertiesFile.exists()){
			try {
				FileInputStream in = new FileInputStream(propertiesFile);
				settings.load(in);
			} catch (IOException e) {
				// NOP
			}
		}
		
		System.out.println(settings.getProperty("left"));
		System.out.println(settings.getProperty("top"));
		System.out.println(settings.getProperty("width"));
		System.out.println(settings.getProperty("height"));	
	}

}
