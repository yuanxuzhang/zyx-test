package com.zyx.collection;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 链表集合测试
 * 链表是一个有序集合（ordered collection），每个对象的位置十分重要。LinkedList.add方法将对象添加到链表尾部。但是，常常
 * 需要将元素添加到链表的中间。由于迭代器是描述集合中位置的，所以这种依赖于位置的add方法将由迭代器负责。只有对自然有序的集合使用迭代器
 * 添加元素才有实际意义（Set类型无序），在Iterator接空中就没有add方法。相反的，集合类库提供了子接口【ListIterator】，其中包
 * 含add方法。
 * 添加位置：迭代器当前位置之前。
 */
public class LinkedListTest {

	public static void main(String[] args) {
		
		firstAdd();
		middleAdd();
		lastAdd();
	}
	
	/**
	 * 当用一个刚刚由Iterator方法返回，并且指向链表表头的迭代器调用add操作时，新添加的元素将变成列表的新表头
	 * expect result： 9-1-2-3-  true
	 */
	private static void firstAdd() {
		
		LinkedList<Integer> linkElements = new LinkedList<Integer>();
		linkElements.add(1);
		linkElements.add(2);
		linkElements.add(3);
		ListIterator<Integer> linkIter = linkElements.listIterator();
		linkIter.add(9);
		
		printResult(linkElements);
	}

	/**
	 * 元素被添加到迭代前当前位置之前
	 * expect result ： 1-9-2-3-
	 */
	private static void middleAdd() {
		LinkedList<Integer> linkElements = new LinkedList<Integer>();
		linkElements.add(1);
		linkElements.add(2);
		linkElements.add(3);
		ListIterator<Integer> linkIter = linkElements.listIterator();
		if(linkIter.hasNext()){
			linkIter.next();
			/*
			 * 插入操作是以当前游标为基准，在它的前边插入元素：linkBefore(e, next);
			 * public void add(E e) {
		            checkForComodification();
		            lastReturned = null;
		            if (next == null)
		                linkLast(e);
		            else
		                linkBefore(e, next);
		            nextIndex++;
		            expectedModCount++;
		        }
			 * */
			linkIter.add(9);
		}
		
		printResult(linkElements);
	}
	
	/**
	 * 当迭代器超过链表的最后一个元素时（即hasNext返回false），添加的元素将变成列表的新表尾。
	 * if (next == null) linkLast(e);
	 * expect result ：1-2-3-9-  true。
	 */
	private static void lastAdd() {
		
		LinkedList<Integer> linkElements = new LinkedList<Integer>();
		linkElements.add(1);
		linkElements.add(2);
		linkElements.add(3);
		ListIterator<Integer> linkIter = linkElements.listIterator();
		while(linkIter.hasNext()){
			linkIter.next();
		}
		linkIter.add(9);
		
		printResult(linkElements);
	}
	
	private static void printResult(LinkedList<Integer> linkElements) {
		for(int linkElement : linkElements){
			System.out.print(linkElement + "-");
		}
		System.out.println("");
	}

}
