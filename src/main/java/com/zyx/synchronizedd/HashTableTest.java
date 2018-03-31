package com.zyx.synchronizedd;
/*package com.zyx.synchronizedd;

public class TestHashTable {
	
	public static void main(String[] args) {
		
		SyncThreadClass syncThread1 = new SyncThreadClass();
		SyncThreadClass syncThread2 = new SyncThreadClass();
		Thread thread3 = new Thread(syncThread1, "SyncThread1");
		Thread thread4 = new Thread(syncThread2, "SyncThread2");
		thread3.start();
		thread4.start();
		
	}
}

class SyncThreadHashTable implements Runnable {

	   public SyncThreadHashTable() {
	   }

	   public void run() {
	      synchronized(SyncThreadClass.class) {
	         for (int i = 0; i < 5; i++) {
	            try {
	               System.out.println(Thread.currentThread().getName() + ":" + (count++));
	               Thread.sleep(100);
	            } catch (InterruptedException e) {
	               e.printStackTrace();
	            }
	         }
	      }
	   }

	   public int getCount() {
	      return count;
	   }
	}
*/