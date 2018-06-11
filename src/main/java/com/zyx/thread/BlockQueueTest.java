package com.zyx.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 场景：协调多个线程之间的合作时-------生产和消费--------
 * 开发人员应当尽可能不去使用底层的线程同步控制，而是使用较高层次的并发控制结构，使精力集中到业务生。
 * 对于许多线程问题，可以通过使用一个或多个队列以优雅且安全的方式将其形式化。生产者线程向队列插入元素，消费者线程则取出它们，使用队列，
 * 可以安全地从一个线程向另一个 线程传递数据。 当试图向队列添加元素而队列已满，或者想从队列移除元素而队列为空时，阻塞队列（blocking
 * queue）导致线程阻塞。【在协调多个线程之间的合作时】，阻塞队列是一个
 * 有用工具。工作线程可以周期性地将中间结果存储在阻塞队列中。其他的工作线程移出中间结果并进一步加以修改，队列会自动平衡负载，如果第一个线程运行的比第二个慢，
 * 第二个 线程集在等待结果时会阻塞。如果第一个线程集运行得快，它将等待第二个队列集赶上来。 take put阻塞 remove add element
 * 会抛异常 offer poll peek 给出错误提示 LinkedBlockingQueue 双端 不限容量 ArrayBlockingQueue
 * 需指定容量 PriorityBlockingQueue 优先级队列 DelayQueue getDelay负值表示延迟已经结束，方可从队列删除。
 * TransferQueue接口 允许生产者线程等待，知道消费者准备就绪可以接收一个元素。
 */
public class BlockQueueTest {
	
	public static void main(String[] args){
		
		String directory = "C:\\Program Files\\Java\\jdk1.8.0_121\\src";
		String keyWord = "java";
		
		final int FILE_QUEUE_SIZE = 10;
		final int SEARCH_THREADS = 100;
		
		BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
		
		FileEnumerationTask enumerator = new FileEnumerationTask(queue, new File(directory));
		
		new Thread(enumerator).start();
		for(int i = 1; i<= SEARCH_THREADS; i++){
			new Thread(new SearchTask(queue, keyWord)).start();
		}
	}

}

class FileEnumerationTask implements Runnable {

	// 退出指示器
	public static File DUMMY = new File("");
	private BlockingQueue<File> queue;
	private File startingDirectory;

	public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {
		this.queue = queue;
		this.startingDirectory = startingDirectory;
	}

	public void run() {

		try {
			enumerate(startingDirectory);
			queue.put(DUMMY);
		} catch (InterruptedException e) {
			// NOP
		}

	}

	private void enumerate(File directory) throws InterruptedException {

		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				enumerate(file);
			} else {
				queue.put(file);
			}
		}
	}

}

class SearchTask implements Runnable {

	private BlockingQueue<File> queue;
	private String keyWord;

	public SearchTask(BlockingQueue<File> queue, String keyWord) {

		this.queue = queue;
		this.keyWord = keyWord;
	}

	public void run() {

		try {
			boolean done = false;
			while (!done) {
				File file;
				file = queue.take();
				// 退出条件
				if (file == FileEnumerationTask.DUMMY) {
					queue.put(file);
					done = true;
				} else {
					seach(file);
				}
			}
		} catch (Exception e) {
			//NOP
		} 
	}

	private void seach(File file) throws Exception {

		try(Scanner in = new Scanner(file)){
			int lineNumber = 0;
			while(in.hasNextLine()){
				lineNumber ++;
				String line = in.nextLine();
				if(line.contains(keyWord)){
					System.out.println(String.format("%s: %d: %s%n", file.getPath(), lineNumber, line));
				}
			}
		}
	}

}
