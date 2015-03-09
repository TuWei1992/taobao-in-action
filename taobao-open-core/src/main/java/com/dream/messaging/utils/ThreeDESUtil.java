package com.dream.messaging.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
/**
 * 使用3DES加密与解密
 * @author yandx
 */
public class ThreeDESUtil {
	 
	 private static final String CRYPT_ALGORITHM = "DESede";
	 
	 //解密
	 public static String decrypt(String value,String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), CRYPT_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            
            byte[] decodedByte = Base64.decodeBase64(value.getBytes());
            byte[] decryptedByte = cipher.doFinal(decodedByte);            
            return new String(decryptedByte);
        } catch(Exception e) {
            return null;
        }
    }
	//加密
	 public static String encrypt(String value,String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), CRYPT_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            
            byte[] encryptedByte = cipher.doFinal(value.getBytes());
            byte[] encodedByte = Base64.encodeBase64(encryptedByte);
            return new String(encodedByte);
        } catch(Exception e) {
            return null;
        }
    }
	 
	 /**
	  * @param args
	  */
	public static void main(String[] args) {
		String szSrc = "This is a 3DES test. 测试";
		String key = "v3VC7LfCq6IL5KgIglwZrQ1a";
//		logger.debug("加密前的字符串:" + szSrc);
	
		String encoded = encrypt(szSrc,key);
//		logger.debug("加密后的字符串:" + encoded);
	
	
		String srcBytes = decrypt(encoded,key);
//		logger.debug("解密后的字符串:" + srcBytes);
	
	} 
}   

