package com.moon.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.moonlive.android.Vitnam.util.Logger;

/**
 * PHP默认的加密方式  与  JAVA加密相互解析
 * 需要的包 commons-codec-1.3.jar
 * @author houhualiang
 *
 */
public class AESSecurity {
	
	public static final Logger log = Logger.getInstance();
	
	/**
	 * AES加密
	 * @param input 原文
	 * @param key  秘钥
	 * @return
	 */
	public static String encrypt(String input, String key) {
		byte[] crypted = null;
		try {
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			crypted = cipher.doFinal(input.getBytes());
		} catch (Exception e) {
			log.e(e.toString());
		}
		return new String(Base64.encodeBase64(crypted));
	}
	
	/**
	 * AES解密
	 * @param input 密文
	 * @param key 秘钥
	 * @return
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String decrypt(String input, String key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
		byte[] output = "".getBytes();
		SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, skey);
		output = cipher.doFinal(Base64.decodeBase64(input.getBytes()));
		return new String(output);
	}

}