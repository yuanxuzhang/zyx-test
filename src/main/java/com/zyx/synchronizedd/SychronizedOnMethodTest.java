package com.zyx.synchronizedd;

public class SychronizedOnMethodTest {

	public static void main(String[] args) {
		SyncThreadOnMethod syncThread = new SyncThreadOnMethod();
		Thread thread1 = new Thread(syncThread, "NoneSyncThread");
		Thread thread2 = new Thread(syncThread, "SyncThread");
		thread1.start();
		thread2.start();
	}

}

/**
 * 锁住类对象 <tbody></tbody>
 */
class SyncThreadOnMethod implements Runnable {

	private int count;

	public SyncThreadOnMethod() {
		count = 0;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			if ("SyncThread".equals(Thread.currentThread().getName())) {
				System.out.println(Thread.currentThread().getName() + ":" + add1());
			} else if ("NoneSyncThread".equals(Thread.currentThread().getName())) {
				System.out.println(Thread.currentThread().getName() + ":" + add2());
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized int add1() {
		return ++count;
	}

	public synchronized int add2() {
		return ++count;
	}

	/*
	 * public int add1() { synchronized(this){ return ++count; } }
	 * 
	 * public int add2() { synchronized(this){ return ++count; } }
	 */

	/*
	 * public int add1() { return ++count; }
	 * 
	 * public int add2() { return ++count; }
	 */

	public int getCount() {
		return count;
	}
}
