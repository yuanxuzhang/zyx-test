package com.zyx.reflective;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * class和classLoader的获取资源的getResource模式
 * 
 * class.getResource(String path)
 * path  以'/'开始，默认是从此类所在的包下获取资源
 *       以'/'开始，则是从【项目】的ClassPath根下获取资源
 * class.getClassLoader().getResource(String path)
 * path  不可以'/'开头，path是指类加载器的加载范围，在资源加载过程中，使用逐级向上委托的形式加载的，'/'表示Boot ClassLoader中的加载范围，
 *       因为这个类加载器是C++实现的，所以加载范围为null。
 * 
 * Class.getResource和ClassLoader.getresource本质上是一样的，都使用ClassLoader.getResource加载资源的。
 */
public class ResourcePathTest {

	public static void main(String[] args) throws IOException {

		Class<ResourcePathTest> cla = ResourcePathTest.class;
		// print file:/D:/myProject/workspace/zyx-test/target/classes/com/zyx/reflective/
		System.out.println(cla.getResource(""));
		Properties props = new Properties();
		try(InputStream in = cla.getResourceAsStream("note")){
			props.load(in);
		}
		// print file:/D:/myProject/workspace/zyx-test/target/classes/
		System.out.println(cla.getResource("/"));
		try(InputStream in = cla.getResourceAsStream("note")){
			props.load(in);
		}
		
		ClassLoader classLoader = ResourcePathTest.class.getClassLoader();
		// print file:/D:/myProject/workspace/zyx-test/target/classes/
		System.out.println(classLoader.getResource(""));
		
		// print null
		System.out.println(classLoader.getResource("/"));
		
		classLoader = ClassLoader.getSystemClassLoader();
		// print file:/D:/myProject/workspace/zyx-test/target/classes/
		System.out.println(classLoader.getResource(""));
		
		// print null
		System.out.println(classLoader.getResource("/"));
		
		// print file:/D:/myProject/workspace/zyx-test/target/classes/
		System.out.println(ClassLoader.getSystemResource(""));
		
	}

}
