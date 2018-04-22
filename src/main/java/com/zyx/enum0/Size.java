package com.zyx.enum0;

/**
 * 这个声明定义的类别是一个类，他刚好有4个实例，在此尽量不要构造新对象，
 * 在比较两个枚举类型的值时，永远不需要调用equals，而直接使用“==”就可以了
 */
public enum Size {
	SMALL, MEDIM, LARGE, EXTRA_LARGE
};
