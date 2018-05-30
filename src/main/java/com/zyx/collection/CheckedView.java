package com.zyx.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * JavaSE5.0 增加了一组“检查视图”，用来对泛型类型发声问题时提供支持。
 */
public class CheckedView {

	public static void main(String[] args) {

		List<String> strings = new ArrayList<String>();
		List rawList = strings;
		// 这个错误的add在编译和运行时检测不到，相反，只有在稍后的另一部分代码中调用get会出错。
		rawList.add(new Date());
		
		// ok
		System.out.println(rawList.get(0));
		
		// print  java.lang.ClassCastException: java.util.Date cannot be cast to java.lang.String
		//System.out.println(strings.get(0));
	
		/*
		 * 解决措施
		 * */
		List<String> checkedList = Collections.checkedList(strings, String.class);
		List rawCheckedList = checkedList;
		
		// print  java.lang.ClassCastException: Attempt to insert class java.util.Date element into collection with element type class java.lang.String
		rawCheckedList.add(new Date());
		
	   /*c.add(typeCheck(e);
		 E typeCheck(Object o) {
            if (o != null && !type.isInstance(o))
                throw new ClassCastException(badElementMsg(o));
            return (E) o;
        }*/
	}

}
