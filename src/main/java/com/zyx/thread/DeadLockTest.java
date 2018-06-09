package com.zyx.thread;

public class DeadLockTest {

	public static final int THREAD_NUMBER = 2;
	public static final int NACCOUNTS = 100;
	public static final double INITIAL_BALANCE = 1000;
	
	public static void main(String[] args) throws InterruptedException{
		
		Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
		
		int i;
		for(i = 0; i < THREAD_NUMBER; i++){
			TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
		//Thread.currentThread().join();
	}
}

class TransferRunnable implements Runnable {
	
	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;
	
	public TransferRunnable(Bank b, int from, double max){
		bank = b;
		fromAccount = from;
		maxAmount = max;
	}

	public void run() {
		
		System.out.println(Thread.currentThread().getName() + "：开始了");

		try{
			while(true){
				int toAccount = (int)(bank.size() * Math.random());
				//double amount = maxAmount * Math.random();
				double amount = 10000;
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep((int) (DELAY * Math.random()));
			}
		}
		catch (InterruptedException e){
			// NOP
		} finally{
			System.out.println(Thread.currentThread().getName() + "：结束了");
		}
		
	}
	
}

class Bank{
	private final double[] accounts;
	
	public Bank(int n, double initialBalance){
		
		this.accounts = new double[n];
		for(int i = 0; i < accounts.length; i++){
			accounts[i] = initialBalance;
		}
	}
	
	public double size() {
		
		return accounts.length;
	}

	public synchronized void transfer(int from, int to, double amount){
		if(accounts[from] < amount){
			try {
				System.err.println(String.format("条件不满足，等待阻塞【%s】", Thread.currentThread().getName()));
				wait();
			} catch (InterruptedException e) {
				// NOP
			}
		}
		System.out.println(Thread.currentThread());
		accounts[from] -=amount;
		System.out.printf("%10.2f from %d to %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf("Total Balance: %10.2f%n", getTotalBanlance());
	}

	private Object getTotalBanlance() {

		double sum = 0;
		for(double a : accounts){
			sum += a;
		}
		
		return sum;
	}
}