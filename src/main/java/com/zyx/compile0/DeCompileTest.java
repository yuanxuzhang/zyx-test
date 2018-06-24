package com.zyx.compile0;

/**
 * 使用javac 编译编译该文件，然后使用javap反编译class文件
 * 
 * javap -c print
 * Compiled from "DeCompileTest.java"
	public class com.zyx.compile0.DeCompileTest extends java.lang.Object{
	public com.zyx.compile0.DeCompileTest();
	  Code:
	   0:   aload_0
	   1:   invokespecial   #1; //Method java/lang/Object."<init>":()V
	   4:   return
    public static void main(java.lang.String[]);
	  Code:
	   0:   iconst_1
	   1:   istore_1
	   2:   iconst_2
	   3:   istore_2
	   4:   iload_1
	   5:   iload_2
	   6:   iadd
	   7:   istore_3
	   8:   return
 */
public class DeCompileTest {

	public static void main(String[] args) {

		int i, j, k;
		i = 1;
		j = 2;
		k = i + j;
		
		return;
	}

}
