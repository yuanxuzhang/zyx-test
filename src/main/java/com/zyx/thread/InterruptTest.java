package com.zyx.thread;

/**
 * 非驻留线程应当包含终止的条件
 */
public class InterruptTest {

	public static void main(String[] args) {

		new InterruptThread().start();
	}

}
class InterruptThread extends Thread{

	@Override
	public void run() {
		
		System.out.println("线程开始了！");
		
		int i = 1;
		while(!Thread.currentThread().isInterrupted()){
			System.out.println("线程在运行！" + i++);
			
			/*
			 * 模拟被中断的线程遇到阻塞的情况sleep或者wait
			 */
			if(i == 5){
				try {
					Thread.currentThread().interrupt();
					Thread.currentThread().sleep(300);
				} catch (InterruptedException e) {
					System.out.println("线程在阻塞时被中断了！");
					/*
					 * 如果在中断状态被置位时调用sleep方法，他不会休眠，相反，它会清除这一状态，并抛出InterruptException。
					 */
					System.out.println(Thread.currentThread().isInterrupted());
				}
			}
			
			/*
			 * 条件满足中断线程
			 */
			if(i > 5){
				Thread.currentThread().interrupt();
			}
		}
		
		System.out.println("线程被中断了！");
		
	}
	
}
