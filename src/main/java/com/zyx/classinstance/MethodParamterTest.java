package com.zyx.classinstance;

/**
 * Java程序设计语言总是采用按值调用。也就是说，方法得到的是所有参数值得一个拷贝，
 * 特别是，方法不能修改传递给它的任何参数变量的内容，
 * 方法参数使用总结：
 * 1、一个方法不能修改一个基本数据类型的参数（数值型和布尔型）
 * 2、一个方法可以改变一个对象的状态
 * 3、一个方法不能让对象参数引用一个新的对象
 */
public class MethodParamterTest {

	public static void main(String[] args) {

		String str = "String";
		System.out.println(addString(str));
		System.out.println(str);
		
		int count = 2;
		System.out.println(addInt(count));
		System.out.println(count);
	}
	
	public static String addString(String str){
		
		str = String.format("%s_new", str);
		return str;
	}
	
	public static int addInt(int count){
		
		count = count + 1;
		return count;
	}

}
