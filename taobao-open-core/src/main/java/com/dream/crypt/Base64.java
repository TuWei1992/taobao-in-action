package com.dream.crypt;

import java.io.UnsupportedEncodingException;

public class Base64 {
	private org.apache.commons.codec.binary.Base64 b64 = new org.apache.commons.codec.binary.Base64();

	public String encode(String input,String encoding) throws UnsupportedEncodingException{
		byte[] result = b64.encode(input.getBytes(encoding));
		return new String(result,encoding); 
	}
	
	public String decode(String input,String encoding) throws UnsupportedEncodingException{
		byte[] result = b64.decode(input.getBytes(encoding));
		return new String(result,encoding); 
	}

}
