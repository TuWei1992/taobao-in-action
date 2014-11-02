package com.dream.rapid.jsp.taglib;

import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import org.apache.log4j.Logger;

public class RandomGUID extends Object {

	private static Random myRand;
	private static SecureRandom mySecureRand;
	
	static Logger logger = Logger.getLogger(RandomGUID.class);
	static {
		mySecureRand = new SecureRandom();
		long secureInitializer = mySecureRand.nextLong();
		myRand = new Random(secureInitializer);
	}

	public RandomGUID() {
		getRandomGUID(false);
	}

	public RandomGUID(boolean secure) {
		getRandomGUID(secure);
	}
	
	public static String getRandomGUID(){
	    return getRandomGUID(true);
	}
	
	public static String getRandomGUID(boolean secure) {

		MessageDigest md5 = null;
		StringBuffer sbValueBeforeMD5 = new StringBuffer();

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.error("Error: " + e);
		}

		try {
			InetAddress id = InetAddress.getLocalHost();
			long time = System.currentTimeMillis();
			long rand = 0;

			if (secure) {
				rand = mySecureRand.nextLong();
			} else {
				rand = myRand.nextLong();
			}

			sbValueBeforeMD5.append(id.toString());
			sbValueBeforeMD5.append(":");
			sbValueBeforeMD5.append(Long.toString(time));
			sbValueBeforeMD5.append(":");
			sbValueBeforeMD5.append(Long.toString(rand));

			String valueBeforeMD5 = sbValueBeforeMD5.toString();
			md5.update(valueBeforeMD5.getBytes());

			byte[] array = md5.digest();
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < array.length; j++) {
				int b = array[j] & 0xFF;
				if (b < 0x10)
					sb.append('0');
				sb.append(Integer.toHexString(b));
			}

			return sb.toString();
		} catch (Exception e) {
			logger.error("Error:" + e);
		}
		return Long.toString(System.currentTimeMillis());
	}

	public static String toString(String valueAfterMD5) {
		String raw = valueAfterMD5.toUpperCase();
		StringBuffer sb = new StringBuffer();
		sb.append(raw.substring(0, 8));
		sb.append("-");
		sb.append(raw.substring(8, 12));
		sb.append("-");
		sb.append(raw.substring(12, 16));
		sb.append("-");
		sb.append(raw.substring(16, 20));
		sb.append("-");
		sb.append(raw.substring(20));
		return sb.toString();
	}

}