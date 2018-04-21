package com.zyx.object;

import java.util.HashMap;
import java.util.Map;

/**
 * hashCode方法测试
 * 散列码（hash code）是由对象导出的一个【整型值】，且没规律
 * hashCode方法定义在Object类中，因此，每个对象都有一个默认的散列码，其值为对象的存储地址
 * 如果重新定义了equals方法，就必须重新定义hashCode方法，以便用户可以将对象插入到散列表中,
 * 根据key对象的hash值计算分发到不同的存储队列上，若不重写同类的多个相同状态的对象的值会存到不同的地方，不会覆盖，
 * 取值的时候只要是新建的对象就回取不到之前存入的值。
 * 
 * equals与hashcode的定义必须一致：如果x.equals(y)返回true,那么x.hashCode就必须与y.hashCode具有相同的值
 */
public class HashCodeTest {

	public static void main(String[] args) {
		
		String s = "OK";
		StringBuilder sb = new StringBuilder(s);
		// 2524 31168322
		System.out.println(s.hashCode() + " " + sb.hashCode());
		
		String t = new String("OK");
		StringBuilder tb = new StringBuilder(t);
		// 2524 17225372
		System.out.println(t.hashCode() + " " + tb.hashCode());
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("testKey", "testValue");
		map.get("testKey");
	}

}
