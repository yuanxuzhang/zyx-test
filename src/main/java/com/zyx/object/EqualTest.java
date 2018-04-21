package com.zyx.object;

/**
 * 1、物理相等性判断
 * 判断两个对象是否具有相同的引用，如果两个对象具有相同的引用，它们一定是相等的。
 * == equal
 * == 比较的是两个变量的显示实际字面值,变量里边存的内容， 它们的hashcode值
 * equal比较的是两个对象变量是否引用的同一个对象，内存地址hashcode值是否相等
 * public boolean equals(Object obj) {
 *      return (this == obj);
 * }
 *2、逻辑相等性判断【对象状态相等性判断】
 *
 *3、杂记：
 *相等性应当是：自反的，对称的、传递的、一致的（多次重复调用）和 非空和null不相等
 *getclss与instanceof取舍：
 *如果子类能够拥有自己的相等概念，则对称性需求将强制采用getClass进行检测【需要对比的含子类的属性】。
 *如果由超类决定相等的概念，那么就可以使用instanceof进行检测，这样可以在不同子类的对象之间进行相等性比较。【不需要比较子类属性】
 *全面的对象比较姿势：
 *1）显示参数命名为otherObject，稍后需要将它转换成另一个叫做other的变量。
 *2）检测this与otherObject是否引用同一个对象（同意项）
 *3）检测otherObject是否为null，若果为null，返回fasle（否决项）
 *4）比较this与otherObject是否属于同一个类。如果equals语义在每个子类中有所修改，就是用getClass检测，如果所有的子类都拥有统一的语义，
 *就是用instanceof检测（同意项）
 *5）将otherObject转换为相应的类类型变量
 *6）现在开始对所有需要比较的域进行比较了。实用==比较基本类型域，实用equals比较对象域。如果所有的域都匹配，就返回true，否则返回false。
 */
public class EqualTest {
	
	public static void main(String[] args){
		
		EqualTest a, b, c;
		a = new EqualTest();
		b = a;
		c = new EqualTest();
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
		// true a和b的变量中保存的内存地址是相同的
		System.out.println(a == b);
		
		// false
		System.out.println(a == c);
		
		// true
		System.out.println(a.equals(b));
		
		// false
		System.out.println(a.equals(c));
		
	}
}
