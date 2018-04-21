package com.zyx.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组列表测试
 * 数组大小一旦确定（编译或者运行时）将不可再改变
 * 数组列表管理着对象引用的一个内部数组，如果调用add且内部数组已经满了，数组列表就将自动地创建一个更大的数组，并且将所有的对象从较小的
 * 数组中拷贝到较大的数组中。
 * 数组列表的容量与数组的大小有一个非常重要的区别。如果为数组分配100个元素的存储空间，数组就有100个空位置可以使用。尔容量为100个元素
 * 的数组列表只是拥有保存100个元素的潜力（实际上，重新分配空间的话，将会超过100），但是在最初，甚至完成初始化构造之后，数组列表根本就
 * 不含有任何元素,由一个size属性记录着当前列表的数据个数。
 * 使用的是【顺序存储结构】---不适用于元素较多，又经常需要在中间位置插入、删除元素的情况----链表
 * ArrayList类并不是Java程序设计语言的一部分，它只是一个由某些人编写且被放在标准库中的一个实用类。
 * 使用add方法为数组添加新元素，而不要使用set方法，它只能替换数组中已经存在的元素内容。
 * 
 */
public class ArrayListTest {

	public static void main(String[] args) {
		
		// 默认大小是{}
		List arrayList = new ArrayList<String>();
		arrayList.size();
		arrayList.remove(0);
		
		/**
		 * 灵活扩展数组，方便地访问数组元素
		 * */
		List<String> list = new ArrayList<String>();
		int i = 0;
		while(i < 20){
			list.add("XXX");
			i++;
		}
		String[] array = new String[list.size()];
		list.toArray(array);
	}

}
