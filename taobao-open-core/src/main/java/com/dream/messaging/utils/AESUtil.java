package com.dream.messaging.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.dream.messaging.MessagingException;
/**
 * 使用AES加密与解密
 * @author yandx
 */
public class AESUtil {
	private static final String ALGORITHM = "AES";

	private static final int KEY_SIZE = 128;

	public final static String ENCODING = "UTF-8";
	
	/**
	 * <p>加密 </p>
	 * @param data 需要加密内容
	 * @param key 密钥
	 * @return
	 * @throws MessagingException 
	 * @throws Exception
	 */
	public static byte[] encrypt(String data, String key) throws MessagingException {
		try {
			

	         
			KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
		    secureRandom.setSeed(key.getBytes());  
			kgen.init(KEY_SIZE,secureRandom);
			SecretKey secretKey = kgen.generateKey();
			
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, ALGORITHM);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
			byte[] byteContent = data.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (Exception e) {
			throw new MessagingException("819991", "加密失败", e);
		}
		
	}
	
    /**解密
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     * @throws MessagingException 
     */
    public static byte[] decrypt(byte[] content, String key) throws MessagingException {
    	try {
    		
	         
			KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
		    secureRandom.setSeed(key.getBytes());  
			kgen.init(KEY_SIZE,secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, ALGORITHM);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, keySpec);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		}catch(Exception e){
			throw new MessagingException("819992", "解密失败", e);
		}

    }
    
    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < buf.length; i++) {
                    String hex = Integer.toHexString(buf[i] & 0xFF);
                    if (hex.length() == 1) {
                            hex = '0' + hex;
                    }
                    sb.append(hex.toUpperCase());
            }
            return sb.toString();
    }
    
    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
            if (hexStr.length() < 1)
                    return null;
            byte[] result = new byte[hexStr.length()/2];
            for (int i = 0;i< hexStr.length()/2; i++) {
                    int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
                    int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
                    result[i] = (byte) (high * 16 + low);
            }
            return result;
    }
}
