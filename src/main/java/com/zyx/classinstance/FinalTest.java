package com.zyx.classinstance;

/**
 * 可以将实例域定义为final。构造对象是必须初始化这样的域。也就是说。
 * 必须确保在每一个构造器执行之后，这个域的值被设置，并且在后面的操作
 * 中，不能再对它进行修改
 */
public class FinalTest {

	public static void main(String[] args) {
		
		FinalTest finalTest = new FinalTest(2);
		System.out.println(finalTest.number);
	}
	
	public final int number;
	
	public FinalTest(int number){
		this.number = number;
	}

}
