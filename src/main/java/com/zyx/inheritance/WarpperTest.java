package com.zyx.inheritance;

/**
 * 对象包装器与自动装箱测试
 * 场景：泛型模式下不支持基本类型，算数表达式中基本类型支持运算符操作。
 * 有时候需要将int这样的基本类型转换为对象，所有的基本类型都有一个与之对应的类，这些类称为包装器。它们分别是：
 * Integer、Long、Float、Double、Short、Byte（前边都继承自Number）、Character、Void和Boolean。
 * 【对象包装器类是不可变的】，即一旦构造了包装器，就不允许更改包装在其中的值，同时【包装器类还是Final】，因此不能定义它们的子类。
 * 自动装箱（autoboxing）XXX.valueOf();
 * 自动拆箱XXX.xxxValue();
 * 
 * 装箱和拆箱是编译器认可的，而不是虚拟机。编译器在生成类的字节码时，插入必要的方法调用，虚拟机只是执行这些字节码。
 * 
 * 使用数值对象包装还有另外一个好处，可以将某些基本方法放置在包装器中，例如，XXX.parseXxx();
 * 
 */
public class WarpperTest {

	public static void main(String[] args) {
		
		/*
		 * 在很多情况下，容易有一种假象，即基本类型与它们的对象包装器是一样的，只是它们的相等性不同。==运算符也可以应用于包装器对象，
		 * 只不过检测的是对象是否指向同一个存储区域，因此，通常不会成立。单数，Java实现却有可能让他成立。如果将经常出现的值包装到同
		 * 一个对象中，这种比较就有可能成立。这种不确定的结果并不是我们所希望的。解决这个问题的办法是在两个包装器对象比较时调用equals
		 * 方法。
		 * 自动装箱规范要求boolean、byte、char<=127,结余-128~127之间的short和int被包装到固定的对象中。
		 * */
		Integer a = 1000;
		Integer b = 1000;
		// false
		System.out.println(a == b);
		
		a = 10;
		b = 10;
		// true
		System.out.println(a == b);
	}

}
