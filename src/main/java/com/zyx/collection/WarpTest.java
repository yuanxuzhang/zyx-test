package com.zyx.collection;

import java.util.Arrays;
import java.util.List;

/**
 * Arrays类的静态方法asList将返回一个包装了普通Java数组的List包装器，这个方法可以将数组传递给一个期望得到列表或集合变元的方法。
 * 返回的对象不是Arraylist,只是一个视图对象，带有访问底层数组的get和set方法。改变数组大小的所有方法都会抛出一个
 * UnsupportOperationException异常。
 */
public class WarpTest {

	public static void main(String[] args) {
		
		/*
		 * public static <T> List<T> asList(T... a) {
		 *      // 这是Arrays的内部类
		        return new ArrayList<>(a); 
		    }
		 * */
		
		List<String> names = Arrays.asList("Amy", "Bob", "Carl");
		
		// print "Amy"
		System.out.println(names.get(0));
		
		// print Amy-Bob-Carl-
		for(String name : names){
			System.out.print(name + "-");
		}
		
		// print java.lang.UnsupportedOperationException
		names.add("Eric");
	}

}
