package com.zyx.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 泛型集合接口优点：算法只需要实现一次。
 */
public class AlgorithmTest {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		
		// 混排
		Collections.shuffle(list);
		// print d-b-a-c-e-
		print(list);

		// 排序
		Collections.sort(list);
		// print a-b-c-d-e-
		print(list);
		
		// 倒排
		Collections.reverse(list);
		// print e-d-c-b-a-
		print(list);
		
		System.out.println(Collections.binarySearch(list, "c"));
		
		/** (-6)
		 * if (i < 0 )
		 * -i - 1 为未找到元素的插入位置
		 */
		System.out.println(Collections.binarySearch(list, "f"));
		print(list);
		
		/*
		 * 最大值
		 * print e
		 */
		System.out.println(Collections.max(list));
		
		/*
		 * 最小值
		 * print a
		 */
		System.out.println(Collections.min(list));
		
		/*
		 * 拷贝
		 */
		List<String> newList = new ArrayList<String>(list.size() + 1);
		Collections.copy(newList, list);
		print(newList);
		
		
		
	}

	private static void print(List<String> list) {
		
		for(String str : list){
			System.out.print(str + "-");
		}
		System.out.println();
	}

}
