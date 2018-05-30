package com.zyx.collection;

import java.util.Collections;
import java.util.HashMap;

/**
 *类库设计者使用视图机制来确保常规集合的线程安全，而不是实现线程安全的集合类。
 */
public class SysnchronizedView {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        
		/*public static <K,V> Map<K,V> synchronizedMap(Map<K,V> m) {
		 *  内部持有的类
	        return new SynchronizedMap<>(m);
	    }
		public int size() {
            synchronized (mutex) {return m.size();}
        }*/
		Collections.synchronizedMap(new HashMap<String, String>());
		
	}

}
