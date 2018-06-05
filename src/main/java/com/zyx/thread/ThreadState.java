package com.zyx.thread;

public class ThreadState {

	public static void main(String[] args) throws InterruptedException {
		
		/*
		 * New
		 */
		ThreadState0 thread = new ThreadState0();
		System.out.println(thread.getState());
		
		thread.start();
		System.out.println(thread.getState());
		
		/*
		 * TIMED_WAITING
		 */
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// NOP
		}
		System.out.println(thread.getState());
		
		/*
		 * TERMINATED
		 */
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// NOP
		}
		System.out.println(thread.getState());
		
		ThreadState1 thread1 = new ThreadState1();
		ThreadState2 thread2 = new ThreadState2();
		thread2.start();
		Thread.currentThread().sleep(3000);
		thread1.start();
		System.out.println(thread1.getState());
		
	}

}

class ThreadState0 extends Thread{

	@Override
	public void run() {

		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			// NOP
		}
	}
	
}

class ThreadState1 extends Thread{

	@Override
	public void run() {

		synchronized(ThreadState.class){
			System.out.println("ThreadState1开始了");
			try {
				Thread.currentThread().sleep(3000);
			} catch (InterruptedException e) {
				// NOP
			}
		}
	}
	
}

class ThreadState2 extends Thread{

	@Override
	public void run() {
		
		synchronized(ThreadState.class){
			System.out.println("ThreadState2开始了");
			try {
				Thread.currentThread().sleep(8000);
			} catch (InterruptedException e) {
				// NOP
			}
			System.out.println("ThreadState2结束了");
		}
	}
	
}
