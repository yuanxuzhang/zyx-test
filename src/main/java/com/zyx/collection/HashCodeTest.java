package com.zyx.collection;

/**
 * As much as is reasonably practical, the hashCode method defined by
 * class {@code Object} does return distinct integers for distinct
 * objects. (This is typically implemented by converting the internal
 * address of the object into an integer, but this implementation
 * technique is not required by the
 * Java&trade; programming language.)
 * 
 * implemented by converting 【the internal address of the object】 into an integer。根据对象内存地址，也就是说new两个状态相同的对象，他们的hashcode值也不相同。
 * 如果自定义了类，就要负责实现这个类的hashCode方法。
 */
public class HashCodeTest {
	
	public static void main(String[] args){
		
		System.out.println(new Object().hashCode());  //29747124
		System.out.println(new Object().hashCode());  //32374789
	}
}
