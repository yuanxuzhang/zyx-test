package com.zyx.enum0;

/**
 * 如果需要的话，可以在枚举类型中添加一些构造器、方法和域。当然构造器只是在构造，枚举常量的时候被调用。
 */
public enum SizeWithOther {
	
	SMALL("S"), MEDIUM("M"), LARGE("L"),EXTRA_LARGE("XL");
	
	private String abbreviation;
	
	// 构造器只是在构造，枚举常量的时候被调用
	private SizeWithOther(String abbreviation){
		this.abbreviation = abbreviation;
	}
	
	public String getAbbreviation(){
		return abbreviation;
	}
}
