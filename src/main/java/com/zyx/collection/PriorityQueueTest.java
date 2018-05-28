package com.zyx.collection;

import java.util.PriorityQueue;

/**
 * 优先级队列测试
 * 元素可以按照任意顺序插入，却总是按照排序的顺序进行检索。也就是说，无论和是调用remove方法，总会获得当前优先级队列中最小的元素。
 * 然而，优先级队列并没有对所有的元素进行排序。如果用迭代的方式处理这些元素，并不需要对它们进行排序，优先级队列使用了一个优雅且高效
 * 的数据结构：堆（heap）。堆是一个可以自我调整的二叉树，对树执行添加和删除操作，可以让最小的元素移动到根，而不必话费事件对元素排序。
 * 优先级队列可以保存实现了Comparable接口的类对象，包括在构造器中传入比较器。
 * 应用：任务调度，每个任务有一个优先级，任务以随机顺序添加到队列中，每当启动一个新的任务时，都将优先级高的任务取出。
 */
public class PriorityQueueTest {

	public static void main(String[] args) {
		
		PriorityQueue<String> priorityQueue = new PriorityQueue<String>();
		priorityQueue.add("c");
		priorityQueue.add("f");
		priorityQueue.add("e");
		priorityQueue.add("a");
		priorityQueue.add("d");
		priorityQueue.add("b");
		
		/*
		 * expect first is a 
		 * result:a-c-b-f-d-e- 
		 */
		for(String element : priorityQueue){
			System.out.print(element + '-');
		}
		System.out.println("");
		
		/*
		 * expect a-b-c-d-e-f-
		 * result:a-b-c-d-e-f-
		 * */
		for(int i = 0; i < 6; i++){
			System.out.print(priorityQueue.poll() + '-');
		}
	
	}

}
