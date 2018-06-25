package com.zyx.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要（meassage digest）
 * 数据块的数字指纹，SHA1可以将任何数据块，无论其数据有多长，都压缩为160（20字节）位的序列。2的160次幂空间大小。
 * 两个基本特性：
 * 1）如果数据的1位或者几位改变了，那么消息摘要也将改变。
 * 2）拥有给定消息的伪造者不能创建与原消息具有相同摘要的假消息。
 * 
 * Java编程语言实现了SHA1和MD5。MessageDigest类是用于创建封装了指纹算法的“工厂”。
 */
public class MessageDigestTest {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

		MessageDigest alg = MessageDigest.getInstance("SHA-1");
		
		try(InputStream in = MessageDigestTest.class.getResourceAsStream("MessageDigestTest.class")){
			
			int ch;
			while((ch = in.read()) != -1){
				alg.update((byte) ch);
			}
			
			byte[] hash = alg.digest();
			
			for(byte b : hash){
				System.out.print(b + " ");
			}
		}
	}

}
