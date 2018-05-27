package com.zyx.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 如果在某个迭代器修改集合时，另一个迭代器对其进行遍历，一定会出现混乱的状况。例如，一个迭代器指向另一个迭代器刚刚删除的元素前面，现在
 * 这个迭代器是无效的，并且不应该再使用。【链表迭代】的设计使它能检测到这种修改，如果迭代器发现它的集合被另一个迭代器修改了，或者被集合
 * 自身的方法修改了，就会抛出一个Concurrent ModificationException异常。
 * 
 * 建议措施：可以根据需要给容器附加许多的迭代器，但是这些迭代器只能读取列表。另外，再单独附加一个既能读取又能写的迭代器。
 */
public class MultiIteraorOperation {

	public static void main(String[] args) {

		List<Integer> list;
		//list = getLinkedListInstance();
		list = getArrayListInstance();
		
		list.add(1);
		list.add(2);
		list.add(3);
		
		ListIterator<Integer> iter1 = list.listIterator();
		ListIterator<Integer> iter2 = list.listIterator();
		iter1.next();
		iter1.remove();
		iter2.next();   // throws ConcurrentModificationException
	}

	private static LinkedList<Integer> getLinkedListInstance() {
		return new LinkedList<Integer>();
	}
	
	private static ArrayList<Integer> getArrayListInstance() {
		return new ArrayList<Integer>();
	}

}
