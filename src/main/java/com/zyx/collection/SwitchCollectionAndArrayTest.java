package com.zyx.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Java平台API中的大部分内容都是在集合框架创建之前设计的，所以，有时候需要在传统的数组和现代的集合之间进行转换。
 */
public class SwitchCollectionAndArrayTest {

	public static void main(String[] args) {

		/*
		 * Array To List
		 * */
		String[] values = new String[]{"1", "2", "3"}; 
		// 构造函数的参数是迭代器接口即可 集合接口的实现类即可
		Set<String> staff = new HashSet<String>(Arrays.asList(values));
		
		/*
		 * 直接toArray返回的数组是一个Object数组，无法改变其类型。
		 * 必须实用类外一种toArray方法，并将其设计为所希望的元素类型且长度为0的数组，随后返回的数组将与所创建的数组一样。
		 */
		staff.toArray();
		staff.toArray(new String[0]);
		// 可以构造一个指定大小的数组
		staff.toArray(new String[staff.size()]);
	}

}
