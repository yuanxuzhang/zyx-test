package com.zyx.classinstance;

/**
 * 静态方法使用场景：
 * 1、一个方法不需要访问对象状态，器所需参数都是通过显示参数提供的
 * 2、一个方法只需要访问类的静态域
 * 附：可以使用类对象访问静态方法，但还是建议使用类名而不是使用对象来调用静态方法
 */
public class StaticMethodTest {

	public static void main(String[] args) {
		
		new StaticMethodTest().staticMethod();
	}
	
	public static void staticMethod(){
		// NOP
	} 

}
