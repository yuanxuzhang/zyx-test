package com.zyx.synchronizedd;

/**
 * 测试加锁与不加锁同时访问的情况
 */
public class TestSyschronizedAndNone {

	public static void main(String[] args) {

		SyncThreadAndNone syncThread = new SyncThreadAndNone();
		Thread thread1 = new Thread(syncThread, "NoneSyncThread");
		Thread thread2 = new Thread(syncThread, "SyncThread");
		thread1.start();
		thread2.start();

	}

}

class SyncThreadAndNone implements Runnable {

	private int count;

	public SyncThreadAndNone() {
		count = 0;
	}

	public void run() {
		{
			if ("SyncThread".equals(Thread.currentThread().getName())) {
				addWithSyschronized();
			} else if ("NoneSyncThread".equals(Thread.currentThread().getName())) {
				addWithNoneSyschronized();
			}
		}
	}

	private void addWithSyschronized() {
		synchronized (this) {
			addCount();
		}
	}

	private void addWithNoneSyschronized() {
		addCount();
	}

	private void addCount() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + ":" + (count++));
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getCount() {
		return count;
	}
}
