package com.zyx.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 如果很多线程从一个数据结构读取数据而很少线程修改其中数据，ReentrantReadWriteLock适合这种情况，允许对读者线程共享访问，线者线程依然必须互斥访问。
 */
public class ReadWriteLockTest {

	private static final int THREAD_COUNT = 3;

	public static void main(String[] args) {
		
		/*!
		 * 瞬间获得读权限，写权限的等前边线程释放之后才可
		 */
		ReadWriteLockRunnable runnable = new ReadWriteLockRunnable();
		for(int i = 0; i < THREAD_COUNT; i++){
			
			Thread t = new Thread(runnable);
			t.start();
		}
	}

}

class ReadWriteLockRunnable implements Runnable{
	
	private static final int READ_COUNT = 5;
	
	// 构造读写锁对象
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	/*!
	 * 抽取读写锁
	 */
	// 可被多个读操作共用的读锁
	private Lock readLock = rwl.readLock();
	// 排斥所有其他的读操作和写操作
	private Lock writeLock = rwl.writeLock();
	private String name = "initial";

	public String getName() {
		readLock.lock();
		try {
			System.out.println(String.format("【%s】获得---读---权限", Thread.currentThread().getName()));
			Thread.currentThread().sleep(3000);
			return name;
		}
		catch (InterruptedException e) {
			// NOP
			return "出错了";
		} finally {
			readLock.unlock();
		}
		
	}

	public void setName(String name) {
		writeLock.lock();
		try {
			System.out.println(String.format("【%s】获得----写---权限", Thread.currentThread().getName()));
			Thread.currentThread().sleep(3000);
			this.name = name;
		}
		catch (InterruptedException e) {
			// NOP
		} finally {
			writeLock.unlock();
		}
	}

	public void run() {
		
		//getName();
		setName(Thread.currentThread().getName());
		getName();
	}
	
}
