package com.zyx.thread;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Runnable.Callable和Future
 * Runnable封装一个 异步运行的任务，可以把它想象成为【一个没有参数和返回值】的【异步方法】。
 * Callable与Runnable类似，但是有返回值。Callable接口是一个参数化的类型，只有一个方法call。类型参数是返回值。
 * Future保存异步计算的结果。可以启动一个计算，将Future对象交给某个线程，然后忘掉他。Future对象的所有者在结果计算好之后就可获得它。
 * Future方法包含：get、cancel、isCancelled和isDone方法
 * get方法的调用被阻塞，直到计算完成。带超时参数的调用，如果计算完成之前，第二个方法的调用超时，就抛出一个TimeoutException异常。若运行的该计算的线程
 * 被中断，两个方法都将抛出InterruptException，如果计算已完成，那么get方法立即返回。
 * 如果计算还在进行，isDone方法返回false；如果完成了，则返回true。
 * 可以调用cancel方法取消计算。计算还没有开始的话，他被取消且不再开始。如果计算处于运行之中，那么如果mayInterrupt参数为真，它就被中断。
 * 
 * FutureTask包装器是一种非常便利的机制，可将Callable转换成【Future和Runnable】，他同时实现二者的接口。
 * 
 *
 */
public class ThreadInterfaceTest {

	public static void main(String[] args) {

		/*String directory = "C:\\Program Files\\Java\\jdk1.8.0_121\\src";
		String keyword = "java";
		
		MatchCounter counter = new MatchCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<>(counter);
		
		Thread t = new Thread(task);
		t.start();
		
		try{
			System.out.println(task.get() + "matching files.");
		}
		catch(ExecutionException e){
			// NOP
		}
		catch(InterruptedException e){
			// NOP
		}*/
		
		CallableTest callable = new CallableTest();
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<Integer> call = threadPool.submit(callable);
		try {
			System.err.println(call.get());
		} catch (InterruptedException e) {
			// NOP
		} catch (ExecutionException e) {
			// NOP
		}
	}

}

class MatchCounter implements Callable<Integer>{
	
	private File directory;
	private String keyword;
	private int count;
	
	public MatchCounter(File directory, String keyword){
		
		this.directory = directory;
		this.keyword = keyword;
	}

	@Override
	public Integer call() throws Exception {

		count = 0;
		try{
			File[] files = directory.listFiles();
			ArrayList<Future<Integer>> results = new ArrayList<>();
			
			for(File file : files){
				if(file.isDirectory()){
					MatchCounter counter = new MatchCounter(file, keyword);
					FutureTask<Integer> task = new FutureTask<>(counter);
					results.add(task);
					Thread t = new Thread(task);
					t.start();
				}
				else{
					if(search(file)) count++;
				}
				
				for(Future<Integer> result : results){
					try{
						count +=result.get();
					}
					catch(ExecutionException e){
						// NOP
					}
				}
				
			}
			
		}
		catch(InterruptedException e){
			// NOP
		}
		return count;
	}

	private boolean search(File file) {

		try{
			try(Scanner in = new Scanner(file)){
				boolean found = false;
				while(!found && in.hasNextLine()){
					String line = in.nextLine();
					if(line.contains(keyword)){
						found = true;
					}
				}
				return found;
			}
		}
		catch(IOException e){
			return false;
		}
	}
	
}

class CallableTest implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {

		return 10;
	}
	
}
