package com.zyx.enum0;

import java.util.Arrays;

/**
 * 枚举测试
 */
public class EnumTest {

	public static void main(String[] args) {
		
		/*
		 * 所有的枚举类型都是Enum类的子类。它们继承了这个类的许多方法：
		 * 其中最有用的一个是toString，这个方法能够 返回【枚举常量名】。
		 * toString的逆方法是静态方法valueOf。 Enum.valueOf(Size.class, "LARGE")
		 * */
		System.out.println(Size.LARGE);
		System.out.println(SizeWithOther.LARGE);
		System.out.println(SizeWithOther.LARGE.getAbbreviation());
		System.out.println(Size.LARGE.toString());
		Size large = Enum.valueOf(Size.class, "LARGE");
		System.out.println(large);
		
		// 每一个枚举类型都有一个静态的values方法，它将返回一个包含全部枚举值得数组
		Size[] size = Size.values();
		System.out.println(Arrays.toString(Size.values()));
		
		// ordinal方法返回enum声明中枚举常量的位置，位置从0开始计数
		System.out.println(Size.LARGE.ordinal());
		System.out.println(SizeWithOther.LARGE.ordinal());
	}

}

