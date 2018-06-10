package com.zyx.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁测试
 * 线程在调用lock方法来获得另一个线程所持有的锁的时候，很可能发生阻塞。应该更加谨慎地申请。trylock方法试图申请一个锁，在成功获得锁后返回true，否则，立即返回
 * false，而且线程可以立即离开去做其他事情。
 * lock方法不能被中断，如果一个线程在等待获得一个锁时，中断线程在获得锁之前一直处于阻塞状态。如果出现死锁，那么，lock方法就无法终止。
 * 如果调用带有超时参数的trylock,那么线程在等待期间被中断，将抛出InterruptException异常，这是一个非常有用的特性，可以允许程序打破死锁线程。
 */
public class TryLockTest {
	
	private static final int THREAD_COUNT = 3;

	public static void main(String[] args) {
		
		TryLockrunnable runnable = new TryLockrunnable();
		for(int i = 0; i < THREAD_COUNT; i++){
			
			Thread t = new Thread(runnable);
			t.start();
		}
	}


}

class TryLockrunnable implements Runnable{
	
	private Lock lock = new ReentrantLock();

	public void run() {
		
		String name = Thread.currentThread().getName();

		try {
			if(lock.tryLock(1010, TimeUnit.MILLISECONDS)){
				try {
					Thread.currentThread().sleep(900);
				} catch (InterruptedException e) {
					// NOP
				} finally{
				System.out.println(String.format("【%s】成功获得锁", name));
				lock.unlock();
				}
			}else{
				System.out.println(String.format("【%s】获得锁失败", name));
			}
		} catch (InterruptedException e) {
			// NOP
		}
	}
	
}
