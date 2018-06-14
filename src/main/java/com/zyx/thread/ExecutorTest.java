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
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 执行器： 构建一个新的线程是有一定代价的，因为涉及与操作系统的交互。如果程序中创建了【大量】的【生命期很短】的线程，应该使用线程池（Thread
 * pool）。 一个线程池中包含许多准备运行的空闲线程，将Runnable对象交给线程池，就会有一个线程调用run方法。当run方法退出时，线程不会死亡，
 * 而是在池中准备为下一个 请求提供服务。 创建大量线程会大大降低性能甚至使虚拟机崩溃。线程池使用特定的算法，以固定的线程数限制并发线程的总数。
 * 执行器（Executor）类有许多静态工厂方法用来构建线程池。 创建：（Executors）
 * 
 * 它们返回实现了ExecutorService接口的ThreadPoolExecutor。
 * newCachedThreadPool:构建的线程池，对于每个任务，如果有空闲线程可用，立即让他执行，如果没有可用的空线程，则创建一个新线程。
 * newFixedThreadPool：构建一个具有固定大小的线程池，如果提交的任务多于空闲的线程数，那么把得不到服务的任务放置到队列中，
 * 当其他任务完成以后再运行它们。 newSingleThreadExecutor：是一个退化了的大小为1的线程池：由一个线程执行提交的任务，一个接着一个。
 * 
 * 运行：
 * 
 * Future<?> submit(Runnable
 * task):返回的future对象可以调用isDone、cancel或isCancelled，但是，get方法在完成的时候只是简单地返回null；
 * Future<?> submit(Runnable task, T result)：future的get方法在完成的时候返回指定的result对象。
 * Future<?> submit(Callable<T> task)：future对象将在计算结果准备好的时候得到它。
 * 
 * 终结： 当用完一个线程池的时候，调用shutdown。该方法启动该池的关闭序列。被关闭的执行器不再接受新的任务，当所有任务都完成以后，线程池中的线程死亡。
 * shutdownNow：取消尚未开始的所有任务并试图【中断】正在运行的线程。
 * 
 * 总结： 1）调用Executors类中静态的方法newCachedThreadPool或newFixedThreadPool。
 * 2）调用submit提交Runnable或Callable对象。
 * 3）如果想要取消一个任务，或者提交Callable对象，那就要保存好返回的Future对象 4)当不再提交任何任务时，调用shutdown。
 * 
 * 预定执行
 * ScheduledExecutorService接口具有为预定执行（Scheduled Execution）或重复执行任务而设计的方法，它是一种允许使用线程池机制的java.util.
 * Timer的泛化。Executors类的newScheduledThreadPool和newSingleThreadedExecutor方法将返回一个实现了
 * ScheduledExecutorService接口的对象。可以预定任务只执行一尺，也可以是周期性执行。
 */
public class ExecutorTest {

	public static void main(String[] args) {

		String directory = "C:\\Program Files\\Java\\jdk1.8.0_121\\src";
		String keyword = "java";
		
		// 创建线程池
		ExecutorService pool = Executors.newCachedThreadPool();
		
		MatchCounterUseThreadPool counter = new MatchCounterUseThreadPool(new File(directory), keyword, pool);
		// 提交任务
		Future<Integer> result = pool.submit(counter);
		
		try{
			System.out.println(result.get() + "matching files.");
		}
		catch(ExecutionException e){
			// NOP
		}
		catch(InterruptedException e){
			// NOP
		}
		
		// 关闭
		pool.shutdown();
		
		int largestPoolSize = ((ThreadPoolExecutor)pool).getLargestPoolSize();
		System.out.println("largest pool size = " + largestPoolSize);
	}

}

class MatchCounterUseThreadPool implements Callable<Integer> {

	private File directory;
	private String keyword;
	private ExecutorService pool;
	private int count;

	public MatchCounterUseThreadPool(File directory, String keyword, ExecutorService pool) {

		this.directory = directory;
		this.keyword = keyword;
		this.pool = pool;
	}

	@Override
	public Integer call() throws Exception {
		
		count = 0;
		try{
			File[] files = directory.listFiles();
			ArrayList<Future<Integer>> results = new ArrayList<>();
			
			for(File file : files){
				if(file.isDirectory()){
					MatchCounterUseThreadPool counter = new MatchCounterUseThreadPool(file, keyword, pool);
					Future<Integer> result = pool.submit(counter);
					results.add(result);
				}else{
					if(search(file)) count++;
				}
			}
			for(Future<Integer> result : results){
				try{
					count += result.get();
				}
				catch(ExecutionException e){
					// NOP
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
			// NOP
		}
		return false;
	}

}
