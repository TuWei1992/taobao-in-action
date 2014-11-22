package com.dream.messaging.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.dream.messaging.MessagingException;

public class RSAUtil {	
	/**
	*typeName:"RSA"
	*customerId:商户号
	*clearStr：明文
	 * @throws MessagingException 
	*/
	public static String encrypt(String customerId, String clearStr,String publickeypath) throws MessagingException{
	        String secureStr = null;

	        try {
	            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	            BASE64Encoder encoder = new BASE64Encoder();
	            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(encoder.encode(getKey(publickeypath).getBytes()).getBytes());
	            PublicKey pk = keyFactory.generatePublic(bobPubKeySpec);
	            Cipher cipher = Cipher.getInstance("RSA");
	            cipher.init(Cipher.ENCRYPT_MODE, pk);
	            byte[] clearData = clearStr.getBytes("UTF8");
	            byte[] secData = new byte[((clearData.length - 1) / 117 + 1) * 128];

	            int m = 0;
	            int n = 0;

	            while (clearData.length - m > 117) {
	                n += cipher.doFinal(clearData, m, 117, secData, n);
	                m += 117;
	            }
	            cipher.doFinal(clearData, m, clearData.length - m, secData, n);
	            secureStr = new String(encoder.encode(secData));
	            secureStr = URLEncoder.encode(secureStr, "UTF-8");

	        } catch (Exception e) {
	        	throw new MessagingException("819991", "加密失败", e);
	        }
	        secureStr = "&reqStr=" + secureStr + "&customerId="+customerId;
	        
	        return secureStr;
	
	}
	
	public static String decrypt(String secureStr,String publickeypath) throws MessagingException{

		String clearStr = null;
        try {
            //从配置文件中读取公钥
        	BASE64Decoder decoder = new BASE64Decoder();

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(decoder.decodeBuffer((getKey(publickeypath))));

            //公钥
            PublicKey pk = keyFactory.generatePublic(bobPubKeySpec);
 
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, pk);

            byte[] secData = decoder.decodeBuffer(secureStr);
            byte[] tmpData = new byte[secData.length];

            int m = 0;
            int n = 0;

            while (secData.length - n != 0) {
                m += cipher.doFinal(secData, n, 128, tmpData, m);
                n += 128;
            }

            clearStr = new String(tmpData, 0, m, "UTF8");

        } catch (Exception e) {
        	throw new MessagingException("819992", "解密失败", e);
        }
        return clearStr;
	}
	
	private static String getKey(String path) throws IOException{
		RandomAccessFile file = null;
		String tmp = null;
		try {
			file = new RandomAccessFile (path,"r");
			file.readLine();
			tmp = file.readLine();
		} finally {
			if(file != null) {
				file.close();
			}
		}
		return tmp;
		
	}
}
