package com.zyx.exception;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 异常处理不能代替简单的测试
 * 10000000此空栈，出栈操作性能：
 * 280
 * 17623
 * 事件性能相差很大
 * 结论：与执行简单的测试相比，捕获异常所花费的时间大大超过了前者，因此使用异常的基本规则是：只在异常情况下使用异常机制。
 */
public class UseExceptionOrAvoidTest {
	
	public static void main(String[] args){
		
		int length = 10000000;
		Stack<Object> stack = new Stack<Object>();
		long begin1 = System.currentTimeMillis();
		for(int i = 0; i < length; i++){
			if(stack.size() != 0){
				stack.pop();
			}
		}
		long end1 = System.currentTimeMillis();
		System.out.println(end1 - begin1);
		
		long begin2 = System.currentTimeMillis();
		for(int i = 0; i < length; i++){
			try {
				stack.pop();
			} catch (EmptyStackException e) {
				// NOP
				//System.err.println("XXX");
			}
		}
		long end2 = System.currentTimeMillis();
		System.out.println(end2 - begin2);
		
		
	}
}
