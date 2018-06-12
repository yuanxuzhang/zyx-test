package com.zyx.thread;

import java.util.Iterator;

/**
 * 线程安全的集合
 * 多线程并发修改一个数据结构，那么很容易会破坏这个数据结构，解决措施：
 * 1）可以通过锁来保护共享数据结构。
 * 2）使用线程安全的结合实现。
 * concurrent包提供了映射表、有序集和队列的高效实现
 * ConcurrentHashMap
 * ConcurrentSkipListMap
 * ConcurrentSkipListSet
 * ConcurrentLinkedQueue
 * ***********************ConcurrentHashMap与HashMap的Collections包装器*************************************
 * 即使是被包装过，在迭代过程中，别线程修改了集合，迭代器会失效，抛出ConcurrentModifyException异常。同步任然是需要的，因此并发的修改可以被可靠
 * 地检测出来。
 * 最好使用java.util.concurrent包重定义的集合，不使用同步包装器中的。特别是，假如它们访问的是不同的桶，由于ConcurrentHashMap已经精心地实现
 * 了，多线程可以放它而且不会彼此阻塞。
 * Collections里边的源码
 * public Iterator<E> iterator() {
            return c.iterator(); // Must be manually synched by user!
        }
 * 有一个例外是经常被修改的数组列表。在那种情况下，同步的Arraylist可以胜过CopyOnWriteArrayList。
 * 
 * 这些集合使用复杂的算法，通过允许【并发访问数据结构的不同部分】来使竞争极小化。Tconrr
 * 并发的散列映射表，可高效地支持大量的读者和一定数量的写着。默认情况下，假定可以有多大16个写着线程同时执行。
 * 
 * 写数组的拷贝:线程安全，其中所有的修改线程对底层数组进行复制。
 * CopyOnWriteArrayList
 * CopyOnWriteArraySet
 */
public class SecurityCollectionTest {

	public static void main(String[] args) {

		
	}

}
