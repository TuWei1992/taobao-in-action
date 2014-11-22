/**************************************************************************
 * Licensed Material - Property of Dawn InfoTek                           *
 * Copyright (c) Dawn InfoTek Inc. 1999, 2004, 2008 -All rights reserved. * 
 * (<http://www.dawninfotek.com>)                                         *
 *                                                                        *
 * This file contains proprietary intellectual property of                *
 * Dawn InfoTek Inc. The contents of and information in this file         *
 * is only to be used in conjunction with a valid Dawn4J license          *
 * as specified in the Dawn4J license agreement. All other use            *
 * is prohibited.                                                         *
 **************************************************************************/
package com.dream.messaging.utils;

/**
 * The Class BitConverter provides several methods for converting a primitive type to a byte array and a byte array to a primitive type.
 * 
 * <p>In addition,this class contains the useful methods to display byte array for the convenience to check the message details. 
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class BitConverter {

	/**
	 * reverse the bytes.
	 * 
	 * @param b the b
	 * 
	 * @return the byte[]
	 */
	public static byte[] bytesReverseOrder(byte[] b) {
		int length = b.length;
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
			result[length - i - 1] = b[i];
		}
		return result;
	}

	/**
	 * Bytes to int.
	 * 
	 * @param bytes the bytes
	 * 
	 * @return the int
	 */
	public static int bytesToInt(byte[] bytes) {
		int res = 0;
		for (int i = 0; i < bytes.length; i++) {
			int tmp = bytes[i] & 0xFF;
			res = (res << 8) + tmp;
		}
		return res;
	}

	/**
	 * print bytes，display the byte as ascii.
	 * 
	 * @param bytes the bytes
	 * 
	 * @return the string
	 */
	public static String displayBytesAsByte(byte[] bytes) {
		if (bytes == null)
			return null;
		StringBuffer sb = new StringBuffer();
		StringBuffer ascSB = new StringBuffer();
		String LineSeparator = System.getProperty("line.separator");
		sb.append(LineSeparator);
		sb
				.append("-Displace-   -0--1--2--3--4--5--6-HEX Value-A--B--C--D--E--F-  ---ASCII Code---");
		for (int i = 0; i < bytes.length; i++) {
			if (i % 16 == 0) {
				//print ascii code
				///**************insert ascii as byte *********************************
				if (i != 0) {
					String ascStr = ascSB.toString();
					ascSB.setLength(0);
					//if (ascStr != null && !ascStr.equals("")) {
					sb.append("  " + ascStr);
				}
				// ************************************************************************/
				//print line number
				sb.append(LineSeparator);
				String hexs = Integer.toHexString(i);
				sb.append(fill('0', 9 - hexs.length()) + hexs + ":   ");
			}
			//打印bytes
			String byteHex = Integer.toHexString(bytes[i]);
			if (byteHex.length() > 2)
				byteHex = byteHex.substring(byteHex.length() - 2);
			sb.append(fill('0', 2 - byteHex.length()) + byteHex + " ");
			///***************print ISO control ascii as byte*******************************
			//if (System.getProperty("os.name").indexOf("Windows") < 0)
			ascSB.append(Character.isISOControl((char) (bytes[i])) ? '.'
					: (char) (bytes[i]));
			//else
			//    ascSB.append((char) (bytes[i]));
			// *******************************************************************/
		}
		///**************print ascii as byte *********************************
		String ascStr = ascSB.toString();
		ascSB.setLength(0);
		if (ascStr != null && !ascStr.equals("")) {
			sb.append(fill(' ', 3 * (16 - ascStr.length())) + "  " + ascStr);
		}
		// ************************************************************************/
		return sb.toString();
	}

	/**
	 * print bytes，display as char.
	 * 
	 * @param bytes the bytes
	 * 
	 * @return the string
	 */
	public static String displayBytesAsChar(byte[] bytes) {

		if (bytes == null)
			return null;
		StringBuffer sb = new StringBuffer();
		byte[] ascBytes = new byte[16];
		String LineSeparator = System.getProperty("line.separator");
		sb.append(LineSeparator);
		sb
				.append("-Displace-   -0--1--2--3--4--5--6-HEX Value-A--B--C--D--E--F-  ---ASCII Code---");
		for (int i = 0; i < bytes.length; i++) {
			if (i % 16 == 0) {
				//print ascii code
				///*************ascii as char *********************************
				if (i != 0) {
					String ascStr = new String(ascBytes);
					char[] chars = ascStr.toCharArray();
					for (int j = 0; j < chars.length; j++) {
						if (Character.isISOControl(chars[j]))
							chars[j] = '*'; //System.getProperty("os.name").indexOf("Windows") < 0 &&
					}
					sb.append("  " + new String(chars));

					for (int j = 0; j < 16; j++)
						ascBytes[j] = 0;
				}
				//************************************************************************/
				//print line number
				sb.append(LineSeparator);
				String hexs = Integer.toHexString(i);
				sb.append(fill('0', 9 - hexs.length()) + hexs + ":   ");
			}
			//print bytes
			String byteHex = Integer.toHexString(bytes[i]);
			if (byteHex.length() > 2)
				byteHex = byteHex.substring(byteHex.length() - 2);
			sb.append(fill('0', 2 - byteHex.length()) + byteHex + " ");

			ascBytes[i % 16] = bytes[i];
		}
		///*************ascii as char *********************************
		int len = (bytes.length) % 16;
		if (len == 0)
			len = 16;
		String ascStr = new String(ascBytes, 0, len);
		char[] chars = ascStr.toCharArray();
		for (int j = 0; j < chars.length; j++) {
			if (Character.isISOControl(chars[j]))
				chars[j] = '*'; //System.getProperty("os.name").indexOf("Windows") < 0 &&
		}
		sb.append(fill(' ', 3 * (16 - len)) + "  " + new String(chars));

		return sb.toString();
	}

	/**
	 * Fill the given character into a string Object.
	 * 
	 * @param src the src
	 * @param len the len
	 * 
	 * @return the string
	 */
	public static String fill(char src, int len) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			sb.append(src);
		}
		return sb.toString();
	}

	/**
	 * convert  BIG_ENDIAN bytes (networks bytes) to float.
	 * 
	 * @param b byte[]
	 * 
	 * @return float
	 */
	public static float hBytesToFloat(byte[] b) {
		int i = 0;
		i = ((((b[0] & 0xff) << 8 | (b[1] & 0xff)) << 8) | (b[2] & 0xff)) << 8
				| (b[3] & 0xff);
		return Float.intBitsToFloat(i);
	}

	/**
	 * convert  BIG_ENDIAN bytes (networks bytes) to int.
	 * 
	 * @param b byte[]
	 * 
	 * @return int
	 */
	public static int hBytesToInt(byte[] b) {
		int s = 0;
		for (int i = 0; i < 3; i++) {
			if (b[i] >= 0) {
				s = s + b[i];
			} else {
				s = s + 256 + b[i];
			}
			s = s * 256;
		}
		if (b[3] >= 0) {
			s = s + b[3];
		} else {
			s = s + 256 + b[3];
		}
		return s;
	}

	/**
	 * convert  BIG_ENDIAN bytes (networks bytes) to short.
	 * 
	 * @param b byte[]
	 * 
	 * @return short
	 */
	public static short hBytesToShort(byte[] b) {
		int s = 0;
		if (b[0] >= 0) {
			s = s + b[0];
		} else {
			s = s + 256 + b[0];
		}
		s = s * 256;
		if (b[1] >= 0) {
			s = s + b[1];
		} else {
			s = s + 256 + b[1];
		}
		short result = (short) s;
		return result;
	}

	/**
	 * Hex string to bytes.
	 * 
	 * @param hexString the hex string
	 * @param limit the limit
	 * 
	 * @return the byte[]
	 */
	public static byte[] hexStringToBytes(String hexString, int limit) {
		byte[] bytes = new byte[limit];
		if (hexString == null)
			return bytes;
		hexString = hexString.trim();
		int len = hexString.length();
		if (len % 2 != 0)
			hexString = "0" + hexString;
		len = hexString.length() / 2;

		if (limit == 0) {
			bytes = new byte[len];
		} else {
			//bytes = new byte[limit];
			if (len > limit)
				len = limit;
		}

		for (int i = 0; i < len; i++) {
			bytes[i] = (byte) (Integer.parseInt(hexString.substring(i * 2,
					i * 2 + 2), 16) & 0xFF);
		}
		return bytes;
	}

	/**
	 * convert  LITTLE_ENDIAN bytes to float.
	 * 
	 * @param b byte[]
	 * 
	 * @return float
	 */
	public static float lBytesToFloat(byte[] b) {
		int i = 0;
		i = ((((b[3] & 0xff) << 8 | (b[2] & 0xff)) << 8) | (b[1] & 0xff)) << 8
				| (b[0] & 0xff);
		return Float.intBitsToFloat(i);
	}

	/**
	 * convert  LITTLE_ENDIAN bytes to int.
	 * 
	 * @param b byte[]
	 * 
	 * @return int
	 */
	public static int lBytesToInt(byte[] b) {
		int s = 0;
		for (int i = 0; i < 3; i++) {
			if (b[3 - i] >= 0) {
				s = s + b[3 - i];
			} else {
				s = s + 256 + b[3 - i];
			}
			s = s * 256;
		}
		if (b[0] >= 0) {
			s = s + b[0];
		} else {
			s = s + 256 + b[0];
		}
		return s;
	}

	/**
	 * convert  LITTLE_ENDIAN bytes to short.
	 * 
	 * @param b byte[]
	 * 
	 * @return short
	 */
	public static short lBytesToShort(byte[] b) {
		int s = 0;
		if (b[1] >= 0) {
			s = s + b[1];
		} else {
			s = s + 256 + b[1];
		}
		s = s * 256;
		if (b[0] >= 0) {
			s = s + b[0];
		} else {
			s = s + 256 + b[0];
		}
		short result = (short) s;
		return result;
	}

	/**
	 * reverse the float by reverse its bytes.
	 * 
	 * @param f float
	 * 
	 * @return float
	 */
	public static float reverseFloat(float f) {
		float result = BitConverter.hBytesToFloat(BitConverter.toLH(f));
		return result;
	}

	/**
	 * reverse the int by reverse its bytes.
	 * 
	 * @param i int
	 * 
	 * @return int
	 */
	public static int reverseInt(int i) {
		int result = BitConverter.hBytesToInt(BitConverter.toLH(i));
		return result;
	}

	/**
	 * reverse the short by reverse its bytes.
	 * 
	 * @param s short
	 * 
	 * @return short
	 */
	public static short reverseShort(short s) {
		short result = BitConverter.hBytesToShort(BitConverter.toLH(s));
		return result;
	}

	/**
	 * convert float to BIG_ENDIAN bytes (network bytes).
	 * 
	 * @param f the f
	 * 
	 * @return the byte[]
	 */
	public static byte[] toHH(float f) {
		return toHH(Float.floatToRawIntBits(f));
	}

	/**
	 * convert int to BIG_ENDIAN bytes (network bytes).
	 * 
	 * @param n int
	 * 
	 * @return byte[]
	 */
	public static byte[] toHH(int n) {
		byte[] b = new byte[4];
		b[3] = (byte) (n & 0xff);
		b[2] = (byte) (n >> 8 & 0xff);
		b[1] = (byte) (n >> 16 & 0xff);
		b[0] = (byte) (n >> 24 & 0xff);
		return b;
	}

	/**
	 * convert short to BIG_ENDIAN bytes (network bytes).
	 * 
	 * @param n short
	 * 
	 * @return byte[]
	 */
	public static byte[] toHH(short n) {
		byte[] b = new byte[2];
		b[1] = (byte) (n & 0xff);
		b[0] = (byte) (n >> 8 & 0xff);
		return b;
	}

	/**
	 * convert float to LITTLE_ENDIAN bytes.
	 * 
	 * @param f the f
	 * 
	 * @return the byte[]
	 */
	public static byte[] toLH(float f) {
		return toLH(Float.floatToRawIntBits(f));
	}

	/**
	 * convert int to LITTLE_ENDIAN bytes.
	 * 
	 * @param n int
	 * 
	 * @return byte[]
	 */
	public static byte[] toLH(int n) {
		byte[] b = new byte[4];
		b[0] = (byte) (n & 0xff);
		b[1] = (byte) (n >> 8 & 0xff);
		b[2] = (byte) (n >> 16 & 0xff);
		b[3] = (byte) (n >> 24 & 0xff);
		return b;
	}

	/**
	 * convert shor to LITTLE_ENDIAN bytes.
	 * 
	 * @param n short
	 * 
	 * @return byte[]
	 */
	public static byte[] toLH(short n) {
		byte[] b = new byte[2];
		b[0] = (byte) (n & 0xff);
		b[1] = (byte) (n >> 8 & 0xff);
		return b;
	}
}
