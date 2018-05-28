package com.zyx.collection;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * 如果要插入自定义的对象，就必须通过实现Comparable接口自定义排序顺序。
 */
public class TreeSetElementTest {

	public static void main(String[] args) {
		
		TreeSet<Object> treeSet;
		
		/*
		 *  Exception in thread "main" java.lang.ClassCastException: java.lang.Object cannot be cast to java.lang.Comparable
			at java.util.TreeMap.compare(TreeMap.java:1294)
			at java.util.TreeMap.put(TreeMap.java:538)
			at java.util.TreeSet.add(TreeSet.java:255)
			at com.zyx.collection.TreeSetElementTest.main(TreeSetElementTest.java:13)
		 *
		 *Object 没有默认的实现Comparable接口。
		 * */
		/*treeSet = new TreeSet<Object>();
		treeSet.add(new Object());*/
		
		/*
		 * 构造可比较的treeset方案
		 * 通常将没有数据的对象（只持有方法），称为函数对象（function object），函数对象通常“动态”定义，即定义为匿名内部类
		 * */
		treeSet = new TreeSet<Object>(
			new Comparator(){
			public int compare(Object o1, Object o2) {
				return o1.hashCode() - o2.hashCode();
			}
		});
		treeSet.add(new Object());
		treeSet.add(new Object());
		treeSet.add(new Object());
		treeSet.add(new Object());
		
		for(Object object : treeSet){
			System.out.println(object);
		}
	}

}
