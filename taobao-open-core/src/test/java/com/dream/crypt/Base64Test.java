package com.dream.crypt;

import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Base64Test {

	private Base64 b64 = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		b64 = new Base64();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEncode() throws UnsupportedEncodingException {
		String encoding = "UTF-8";
		String input = "张甫";
		String result = b64.encode(input, encoding);
		System.out.print(result);
	}

	@Test
	public void testDecode() throws UnsupportedEncodingException {
		String encoding = "UTF-8";
		String input = "5byg55Sr";
		String result = b64.decode(input, encoding);
		System.out.print(result);
	}

}
