package com.zyx.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Java密码扩展包含了一个Cipher类，该类是所有加密算法的超类，通过getInstance方法可以获得一个密码对象。
 * 
 * DES:数据加密标准
 * AES：高级加密标准
 */
public class CipherTest {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

		// 获取密码对象
		Cipher cipher = Cipher.getInstance("DES");
		/*
		 *  通过设置模式和密钥来对它初始化
		 */
		// 密钥生成
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		SecureRandom random = new SecureRandom();
		keygen.init(random);
		SecretKey key = keygen.generateKey();
		
		cipher.init(Cipher.ENCRYPT_MODE, key);
	}

}
