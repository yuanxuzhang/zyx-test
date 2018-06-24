package com.zyx.security.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 对于程序具有插件架构，其中代码的某些部分是作为可选的插件打包的。如果插件被打包为JAR，那就可以直接用URLClassLoader类的实例去加载这些类
 * 
 * URLClassLoader在创建对象的时候如果不指定父类加载，默认会指定应用类加载器。
 *
 */
public class URLClassLoaderTest {
	
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException{
		
		String separator = File.separator;
		URL url = new URL("file:" + "E:"+ separator + "zyx-test.jar");
		@SuppressWarnings("resource")
		URLClassLoader urlLoader = new URLClassLoader(new URL[]{url});
		// 使用全路径名
		urlLoader.loadClass("com.zyx.security.ExtendClassLoaderWithoutPathTest");
		
		System.out.println(urlLoader.toString());
		
		System.out.println(urlLoader.getParent());
		
		//Thread.currentThread().getContextClassLoader().loadClass("com.zyx.security.ExtendClassLoaderWithoutPathTest");
	
		//Class.forName(name, initialize, loader)
	}
}
