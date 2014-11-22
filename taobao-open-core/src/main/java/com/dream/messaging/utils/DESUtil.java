package com.dream.messaging.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.dream.messaging.MessagingException;
/**
 * 使用DES加密与解密
 * @author yandx
 */
public class DESUtil {
	   
	 //解密
	 public static String decrypt(String message, String key) throws MessagingException{
		try{
		    byte[] bytesrc = convertHexString(message);
		    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		    DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		    SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		    IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		    cipher.init(2, secretKey, iv);
		    byte[] retByte = cipher.doFinal(bytesrc);
		    return new String(retByte,"UTF-8");
	    }catch(Exception e){
	    	throw new MessagingException("819992", "解密失败", e);
	    }
	  }
	//加密
	  public static byte[] encrypt(String message, String key) throws MessagingException{
	  
	  	try{
	    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
	    DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	    SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
	    IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
	    cipher.init(1, secretKey, iv);
	    return cipher.doFinal(message.getBytes("UTF-8"));
	  	}catch(Exception e){
	    	throw new MessagingException("819991", "加密失败", e);
	    }
	  }
	 
	  public static byte[] convertHexString(String ss)
	  {
	    byte[] digest = new byte[ss.length() / 2];
	    for (int i = 0; i < digest.length; ++i)
	    {
	      String byteString = ss.substring(2 * i, 2 * i + 2);
	      int byteValue = Integer.parseInt(byteString, 16);
	      digest[i] = (byte)byteValue;
	    }
	    return digest; }
	 
	  public static String toHexString(byte[] b) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < b.length; ++i) {
	      String plainText = Integer.toHexString(0xFF & b[i]);
	      if (plainText.length() < 2)
	        plainText = "0" + plainText;
	      hexString.append(plainText);
	    }
	 
	    return hexString.toString();
	  }
	 /**
	  * @param args
	  */
//	 public static void main(String[] args) {
////	  DESUtil e =new DESUtil();
//	  String key="ghjbraq2";
//	  String xmlstr="<?xml version=\"1.0\" encoding=\"utf-8\" ?>"+
//	   "<inPutDatas>"+
//	    "<controlInfo>"+
//	      "<sourceCode>1000</sourceCode>"+
//	      "<destCode>CMSB</destCode>"+
//	      "<funcCode>stockIn</funcCode>"+
//	     "<shipOrderType>1</shipOrderType>"+
//	    "</controlInfo>"+
//	    "<resultSet rows=\"\">"+
//	     "<row>"+
//	        "<shipOrderId>MS2013030210843</shipOrderId>"+
//	        "<shipOrderStatus>5</shipOrderStatus>"+
//	        "<createDate>2013-03-21 16:23:54</createDate>"+
//	        "<products>"+
//	          "<product>"+
//	            "<ITEM_ID>71330</ITEM_ID>"+
//	            "<ITEM_UPC/>"+
//	            "<ITEM_COUNT>1</ITEM_COUNT>"+
//	           "<RECEIVE_COUNT>1</RECEIVE_COUNT>"+
//	         "</product>"+
//	       "</products>"+
//	     "</row>"+
//	    "</resultSet>"+
//	  "</inPutDatas>";
//	  //String msg="aec68b4bfd8debc9b82c6066e34770b999be021d7bf562d3936efc6f2fee8092e6d0da803a5734cd93b7912f78e1c15677171bbbfefe3533d606a0efa49849052b0a41a84e16b430c77e458f18c7a57c644614ec8a4b0b18560cf850882d9980e1d20f870d0cc02af20905b823738f266d69887282c9ffd1bc919303d60a6d971ebe7ad420f9b1f0b59e64d660494e9b5d4f44b74821c6acdd32b65dab1dc4f58ab3cef416bb7a1a35f8bff2071357b2c926cf334f8b6c6ad4517f0385bea8516154928a7002d3380b457d900c805abf5b908423022e31f12ce8bdd607481fd906b5b9c77cae870aa044e5b7555fed2b44590418d0d04f6416a261150554072f2ee221a0c9b49470f5700a0784731125e5f81338baca6191c1e952b11065350d45142528f34d8e50eff0d12c0007eabc3a8ae2cc5dcd6451cf96938723571389d975ac39ba77ae0147389a7bccd77b3de019e9a4346bbdfcaf029cdda3bcc14cc4d860e69c5377c8cd7ddf703570c2abcfe906bf477b99c4b8acbc9277171bb2ee550b02d14d377a5b51fd250e8fff9820ae2a3905eb19066e8d5b927fc431b408b5ef7a6ac08a533256f76f1a5838d61f0913d1f2eaba716ce835d8b2b71acbf511befb5e1fb988ddf33e0594cf80c5de05c6429599364b6427d3ac41b6c78073cb8cf517f2a07bf280701841cbbc3d07f862060e098b33";
//	  String msg="aec68b4bfd8debc9b82c6066e34770b999be021d7bf562d3936efc6f2fee809293a7d035c2c1bc1956b0521ae08f031382a3d5ced2986301c9919e846f0ca29271e4a54bc14d03573fdfbba72efb274e239a5b5772f32a10896baa2c61281f2ed7602ed763c96acb2a61c4860d89528e9ef3d528fb7f2951a088528da67653491f5d59820ba09df3cca047106652333530d2aa87e0efda0d6ca784ae690cd012ed4d069b50411411bcdf9e9203200b96e794a83332e6fe704cad7e1988a26aee0761f7edebb42d0497ef8d5b3e62f7bbc9318585168bd9e9ff916efa08528507e54839d51afc2ef7cca22406efd2f61554fc7583cf5f680fde25f29c356c87cbf1c9e21e9698b853e7ca97951b6ac735058ba8d10b49a2aeb33a8026c015521010085ee75b6b17a9add87a630bd2a824f776a9653e91b347be017ae8945d3a8f44371e7efdef4a7d5de9294d8674399e83fabd72a06c1e222036319abf16aeb8375cf667b2db93d408d213827fc35057a2ccd7fd4254664286c8cb85e5bf4352695194731a80e3451ea105fecba050cb8274228aee86be1c1bb376ce326133aef52980c51850909b39233dc5218841c451766415f6a72904101878f63aee99f5148b64c5e3626ad75ba5779894d6c94f7c34ef5a4a398e53e82553cd5364516f3cea8a69ef56113f6b873443430206702fd2f0f588cd78d9aa393ecbc53fe536b81d86368b84ca4b7dbebdb7f8ec3287db8dc3acd27d60c44773f5c0e74c56421bed96a2f90f7caa6395cc411c19a292b010451e30f3e9c5c91d8cf7428c4e9f609f3fe42426fcfec3b789e30d8561b6";
//	  String msg2="";
//	  try {
//	   String Str=DESUtil.decrypt(msg, key);
//	   //byte[] retByt=e.encrypt(xmlstr, key);
//	   //String Str=e.toHexString(retByt);
//	   System.out.println(Str);
//	  } catch (Exception e1) {
//	  }
//	 
//	 }
}   

