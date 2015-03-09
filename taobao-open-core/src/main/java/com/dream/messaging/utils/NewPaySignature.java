package com.dream.messaging.utils;
//package com.dream.messaging.utils;
//
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.net.URLEncoder;
//
//import com.cmbc.cc.gateway.security.jca.SignatureDecrypt;
//import com.cmbc.cc.gateway.security.jca.SignatureEncrypt;
//import com.cmbc.cc.gateway.security.jce.DESDecrypt;
//import com.cmbc.cc.gateway.security.jce.DESEncrypt;
//import com.cmbc.cc.gateway.security.jce.RSADecrypt;
//import com.cmbc.cc.gateway.security.jce.RSAEncrypt;
//
//public class NewPaySignature {
//	
//	// 商户私钥文件存储路径
//	private static String privatekeypath;
//	// 民生银行支付网关公钥文件存储路径
//	private static String publickeypath;
//	//商户ID
//	private static String customerId;
//	
//	public String getPrivatekeypath() {
//		return privatekeypath;
//	}
//
//	public void setPrivatekeypath(String privatekeypath) {
//		this.privatekeypath = privatekeypath;
//	}
//
//	public String getPublickeypath() {
//		return publickeypath;
//	}
//
//	public void setPublickeypath(String publickeypath) {
//		this.publickeypath = publickeypath;
//	}
//
//	public String getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(String customerId) {
//		this.customerId = customerId;
//	}
//
//	public static String encryPtion(String dataStr) throws Exception {
//		// 数据加密
//		DESEncrypt desEncrypt = new DESEncrypt();
//		String keyStr = desEncrypt.encrypty(dataStr);
//		
//		// 生成数字信封
//		RSAEncrypt rsaEncrypt = new RSAEncrypt(getKey(publickeypath).replace("\n", ""));
//		String str = rsaEncrypt.encrypty(keyStr);
//		
//		// 对加密数据进行签名
//		SignatureEncrypt signatureEncypt = new SignatureEncrypt(getKey(privatekeypath).replaceAll("\n", ""));
//		String encyptStr = signatureEncypt.encrypty(str);
//		
//		// 针对特殊字符，需对上送数据进行UTF8 转码
//		String tmppath = "?reqStr=" + URLEncoder.encode(encyptStr, "UTF-8");
//		
//		tmppath += "&customerId="+customerId;
//		
//		// 需上传的加密数据
//		logger.debug("加密后的数据为：" + tmppath);
//		
//		return tmppath;
//	}
//
//	public static String decipher(String dataStr) throws Exception {	
//		// 对加密数据进行验签解密
//		SignatureDecrypt signaturedecypt = new SignatureDecrypt(getKey(publickeypath), dataStr.split(",")[1]);
//		String signaturedecyptStr = signaturedecypt.decrypty(dataStr.split(",")[0]);
//		
//		RSADecrypt rsaDecrypt = new RSADecrypt(getKey(privatekeypath));
//		String rsaDecryptStr = rsaDecrypt.decrypty(signaturedecyptStr);
//		
//		DESDecrypt desDecrypt = new DESDecrypt();
//		String tmppath = desDecrypt.decrypty(rsaDecryptStr);
//		
//		logger.debug("解密后的数据为：" + tmppath);
//		
//		return tmppath;
//	}
//	
//	/**
//	 * 获取密钥信息 文件中第二行为密钥信息
//	 * 
//	 * @param path
//	 * @return
//	 * @throws IOException
//	 */
//	private static String getKey(String path) throws IOException {
//		RandomAccessFile file = new RandomAccessFile(path, "r");
//		file.readLine();
//		String tmp = file.readLine();
//		file.close();
//		return tmp;
//	}
//
//}
