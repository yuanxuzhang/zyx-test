package com.zyx.classinstance;

/**
 * 调用构造器时数据域的初始化步骤：
 * 1、所有的数据域被初始化为默认值（0，false或null）
 * 2、按照在类声明中出现的次序，依次执行所有域【初始化语句】和【初始化块】
 * 3、如果构造器【第一行】调用第二个构造器，则执行第二个构造器主体
 * 4、执行这个构造器主体
 */
public class InitializeTest {

	public static void main(String[] args) {
		
		new InitializeTest();
	}
	
	/*private int count = 1;
	
	{
		count = 2;
	}*/
	
	{
		count = 2;
	}
	
	private int count = 1;

	public InitializeTest(){
		count = 3;
	}
}
