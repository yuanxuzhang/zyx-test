package com.zyx.object;

/**
 * toString测试
 * 用于返回表示对象值得字符串，一般遵循格式为getClass().getName + "[属性1=" + 属性1值 "..." + "]"
 * 
 * 只要对象与一个字符串通过操作符"+"链接起来，【Java编译】就会自动地调用toString方法，以便获得这个对象得字符串描述。
 * 
 * Object类定义了toString方法，用来打印输出对象所属的类名和散列码。
 * public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
    
 * 数组继承了Object类的toString方法，数组类型将按照旧的格式打印，形如[I@2e45",I表明整形数组。修正的方式是Array.toString(XXX)
 * 
 */
public class ToStringTest {

	public static void main(String[] args) {
		
		
	}

}
