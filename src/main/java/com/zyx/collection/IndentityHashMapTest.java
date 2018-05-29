package com.zyx.collection;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class IndentityHashMapTest {

	public static void main(String[] args) {

		IdentityHashMap<String, String> map = new IdentityHashMap<String, String>();
		String str1 = new String("String");
		String str2 = new String("String");
		map.put(str1, "value1");
		map.put(str2, "value2"); 
		
		// false 使用==比较
		System.out.println(map.containsKey(new String("String")));

		// size 2 print String:value1--String:value2--
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.print(entry.getKey() + ":" + entry.getValue() + "--");
		}
		System.out.println();

		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put(str1, "value1");
		hashMap.put(str2, "value2");
		// size 1 print String:value2--
		for (Entry<String, String> entry : hashMap.entrySet()) {
			System.out.print(entry.getKey() + ":" + entry.getValue() + "--");
		}
		System.out.println();
		
		// true 使用equal比较
		System.out.println(hashMap.containsKey(new String("String")));
		
		// -1808118735---1808118735
		System.out.println(str1.hashCode() + "--" + str2.hashCode());
	}

}
