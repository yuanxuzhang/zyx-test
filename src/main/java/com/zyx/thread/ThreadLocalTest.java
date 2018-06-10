package com.zyx.thread;

import java.util.Set;
import java.util.TreeSet;

/**
 * 总结：
 * 1）通过ThreadLocal创建的副本是存储在每个线程自己的threadLocals中的
 * 2）每个线程中可有多个threadLocal变量，所以ThreadLocalMap的键值是实例化的ThreadLocal对象。
 * 3）在进行get之前，必须先set，否则会报空指针异常。
 */
public class ThreadLocalTest {
	
	private static final int THREAD_COUNT = 3;

	public static void main(String[] args) {
		
		ThreadLocalRunnable runnable = new ThreadLocalRunnable();
		for(int i = 0; i < THREAD_COUNT; i++){
			
			Thread t = new Thread(runnable);
			t.start();
		}
	}

}

class ThreadLocalRunnable implements Runnable{
	
	private final static int COUNT = 10;
	
	/*!
	 * 每个线程只会在自己的内部实例set内操作，所以每个线程添加到它们中的数据统一数量为：COUNT
	 */
	private ThreadLocal<Set<String>> set = new ThreadLocal(){
		
		public Set<String> initialValue(){
			return new TreeSet();
		}
	};
	
	/*!
	 * 各个相乘共享，set集合的数量存在不确定性，彼此的操作相互影响。
	 */
	//private Set<String> set = new TreeSet<String>();

	public void run() {
		
		String name = Thread.currentThread().getName();
		Set<String> innerSet = set.get();
		
		System.out.println("启动：" + name);
		
		for(int i = 0; i < COUNT; i++){
			innerSet.add(String.format("%s : %s", name, i));
		}
		
		/*for(String str : set.get()){
			System.out.print(str + "--");
		}*/
		//System.out.println();
		System.err.println(String.format("【%s】线程的数量【%s】", name, innerSet.size()));
		
		System.out.println("关闭：" + name);
	}
	
}
