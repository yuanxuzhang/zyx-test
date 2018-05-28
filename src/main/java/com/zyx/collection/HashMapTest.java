package com.zyx.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapTest {

	public static void main(String[] args) {
		
		Map<String, String> map;
		map = new HashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		
		/*
		 * 优雅的迭代
		 */
		Set<Entry<String, String>> entrySet = map.entrySet();
		for(Entry<String, String> entry : entrySet){
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		//可用null 作为键,值不可为null
		map.put(null, "");
		
		new HashSet().add(null);
	}

}
