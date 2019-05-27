package com.zyx.string;

/**
 * JVM字符串拘留（intern）测试。
 * 每一个Java虚拟机必须维护一张内部列表，它列出所有在运行程序的过程中已被“拘留”的字符串对象的【引用】。
 * 基本上如果一个字符串在虚拟机的拘留列表上出现，他就是被拘留的。维护这个列表的关键是任何特定的字符序列在这个列表上只出现一次。
 * 
 * 字符串是否被共享关在在于是不是被执行过【拘留】
 * 当虚拟机解析一个字符串文字的常量池入口时，它“拘留”这个字符串。首先，虚拟机检测这个字符串中的字符的顺序是否已经被拘留。
 * 如果是这样，那么虚拟机就会使用与已拘留的字符串同样的引用。否则，它将会创建一个新的String对象，把这个新String对象
 * 的引用加入到已拘留的字符串集合中去，然后再把这个引用赋给新的已拘留的字符串。
 * 
 */
public class StringIntern {
	
	private static final String strF = "Hi!";
	
	private static String strS = "Hi!";
	
	private static String strI = "Hi!";

	public static void main(String[] args) {
		
		String literalString = "Hi!";
		
		System.out.println(args[0]);
		
		//System.out.println();
		//String.
		
		System.out.println("Before assin argZero:");
		if(args[0] == "Hi!"){
			System.out.println("They are the same string object!");
		}
		else {
			System.out.println("They are different string object!");
		}
		
		// [1]命令行参数传入的字符串不会是引用文字，不会被JVM拘留
		String argZero = args[0];
		
		System.out.println("Before interning argZero:");
		if(argZero == literalString){
			System.out.println("They are the same string object!");
		}
		else {
			System.out.println("They are different string object!");
		}
		
		// 拘留
		argZero = argZero.intern();
		System.out.println("After interning argZero:");
		if(argZero == literalString){
			System.out.println("They are the same string object!");
		}
		else {
			System.out.println("They are different string object!");
		}
		
		
		String a="a";
		String a0="a";
		if(a == a0){
			System.out.println("They are the same string object!");
		}
		else {
			System.out.println("They are different string object!");
		}
		
		String b = new String("b");
		//b = b.intern();
		String b0 = "b";
		if(b == b0){
			System.out.println("They are the same string object!");
		}
		else {
			System.out.println("They are different string object!");
		}
		
		if(literalString == strS){
			System.out.println("static equal");
		}
		else {
			System.out.println("static no equal");
		}
		
		if(literalString == strI){
			System.out.println("instance equal");
		}
		else {
			System.out.println("instance no equal");
		}
		
		if(literalString == strF){
			System.out.println("final equal");
		}
		else {
			System.out.println("final no equal");
		}
		
		String a = "a";
		String b = "b";
		String ab = "ab";
		// [2]字符串变量(非常量)的+运算不会被拘留
		System.out.println(ab == (a + b));
		System.out.println(ab == ("a" + "b"));
	}

}
