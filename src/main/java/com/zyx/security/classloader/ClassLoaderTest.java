package com.zyx.security.classloader;

public class ClassLoaderTest {

	public static void main(String[] args) throws ClassNotFoundException {

		// 引导类加载器不存在ClassLoader对象，使用C语言实现
		System.out.println(String.class.getClassLoader());
		
		/*
		 * 将包含ExtendClassLoaderWithoutPathTest.class的文件或者JAR放入jre/lib/ext，【不添加路径】即可加载。
		 * 将包含ExtendClassLoaderWithoutPathTest.class的文件或者JAR放入非jre/lib/ext，【不添加路径】， 加载不到类文件。
		 */
		Class.forName("ExtendClassLoaderWithoutPathTest");
		
		// AppClassLoader
		System.out.println(ClassLoaderTest.class.getClassLoader());
		
		// ExtClassLoader
		System.out.println(ClassLoaderTest.class.getClassLoader().getParent());
		
		// ExtClassLoader
		System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent());
	}

}
