package com.zyx.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * 相对于使用迭代器一次遍历一个元素去遍历集合，可以使用类库中的批操作（bulk operation）避免频繁地使用迭代器。
 */
public class BulkOperationTest {

	public static void main(String[] args) {

		Set<Integer> set1 = new HashSet<Integer>();
		Set<Integer> set2 = new HashSet<Integer>();
		set1.add(1);
		set1.add(2);
		set1.add(3);
		set1.add(4);
		set1.add(5);
		
		set2.add(4);
		set2.add(5);
		set2.add(6);
		set2.add(7);
		set2.add(8);
		
		/*
		 * 求交集
		 * */
		Set<Integer> intersection = new HashSet<Integer>(set1);
		intersection.retainAll(set2);
		
		// print 1-2-3-4-5-
		print(set1);
		// print 4-5-6-7-8-
		print(set2);
		// print 4-5-
		print(intersection);
	}

	private static void print(Set<Integer> set) {
		for(int num : set){
			System.out.print(num + "-");
		}
		System.out.println();
	}

}
