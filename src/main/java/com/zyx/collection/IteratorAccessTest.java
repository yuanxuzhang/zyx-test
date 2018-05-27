/**
 * 
 */
package com.zyx.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器 读取测试
 * 在调用next之前调用hasNext方法。如果迭代器对象还有多个共访问的元素，这个方法就返回true。如果想要查看集合中的所有元素，就请求一个
 * 迭代器，并在hasNext返回true时反复调用next方法（末尾next方法将抛出一个NoSuchElementException）。
 * JavaSE5.0起，这个循环可以采用一种更优雅的缩写方式。用“for each”循环可以更加简练地表示同样的循环操作。
 * 【编译器简单地将“for each”循环翻译为带有迭代器的循环，“for each”循环可以与任何实现了Iterable接口的对象一起工作】。
 * Collection接口扩展了Iterable接口，因此，对于标准类库中的任何集合都可以使用“for each”。
 */
public class IteratorAccessTest {

	public static void main(String[] args) {
		List<Integer> elements = new ArrayList<Integer>();
		/*elements.add(1);
		elements.add(2);*/
		
		Iterator<Integer> iter = elements.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		// 优雅实现
		for(int element : elements){
			System.out.println(element);
		}
		
	}

}
