package com.zyx.collection;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * 调用 LinkedHashMap<K, V>(initialCapacity, loadFactor, true),将会实现：链接散列映射表将访问顺序，而不是插入
 * 顺序，对映射条目进行迭代。每次调用get或put，受到影响的条目将从当前的位置删除，并放到【条目链表】的尾部。
 * 注意：只有在条目链表中的位置会受影响，而散列表中的桶不会受影响。一个条目总位于与散列码对应的桶中。
 * 访问顺序对于实现高速缓存的“最近最少使用”原则十分重要。例如，可能希望将访问频率高的元素放在内存中，而访问频率低的元素则从数据库中读取。
 * 当在表中找不到元素项且表又已经满时，可以将迭代器加入到表中，并将枚举的前几个元素删除掉。这些是近期最少使用的几个元素。
 */
public class LinkedHashMapTest {

	public static void main(String[] args) {

		LinkedHashMap<String, String> map;
		map = new LinkedHashMap<String, String>(16, (float) 0.75, true);  // 1
		//map = new LinkedHashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
		
		// print key1:value1-key2:value2-key3:value3-key4:value4-
		for(Entry<String, String> entry : map.entrySet()){
			System.out.print(entry.getKey() + ":");
			System.out.print(entry.getValue() + "-");
		}
		System.out.println("");

		//print key1:key2:key3:key4:
		for(String key : map.keySet()){
			System.out.print(key + ":");
		}
		System.out.println("");
		
		// print value1:value2:value3:value4:
		for(String value : map.values()){
			System.out.print(value + ":");
		}
		System.out.println("");
		
		map.get("key2");                                  // 2
		//map.put("key2", "value22");
		//print key1:value1-key3:value3-key4:value4-key2:value2-
		for(Entry<String, String> entry : map.entrySet()){
			System.out.print(entry.getKey() + ":");
			System.out.print(entry.getValue() + "-");
		}
		System.out.println("");
		
		// print key1:key3:key4:key2:
		for(String key : map.keySet()){
			System.out.print(key + ":");
		}
		System.out.println("");
		
		// print value1:value3:value4:value2:
		for(String value : map.values()){
			System.out.print(value + ":");
		}
	}

}
