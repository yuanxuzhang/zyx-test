package com.zyx.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 不可修改的视图
 * Collections存在用于产生集合的不可修改视图，这些视图对现有集合增加了一个运行时的检查，如果发现视图对集合进行修改，就抛异常。
 * 适用于只想查看集合内容，而不去修改的场景。
 * 
 * 不可修改视图并不是集合本身不可修改，任然可以通过集合的原始引用对集合进行修改。
 * 由于视图只是包装了接口而不是实际的集合对象，所以只能访问接口中定义的方法。
 */
public class UnModefiableView {

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		List<String> listView = Collections.unmodifiableList(list);
		
		// print java.lang.UnsupportedOperationException
		listView.add("3");
		
		/*
		 * 类似的方法还有
		 *  Collections.unmodifiableCollection(c);
			Collections.unmodifiableMap(m)
			Collections.unmodifiableSet(s);
			Collections.unmodifiableSortedMap(m);
			Collections.unmodifiableSortedSet(s);
		 * */
		
	}

}
