package com.dream.crypt;

import org.junit.After;
import org.junit.Before;

public class DESTest {
	
	private DES des = null;
	
	@Before
	public void setUp() throws Exception {
		des = new DES();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		des = null;
	}
	
	public void encrypt() throws Exception{
		byte[] plain = "abc".getBytes();
		String password = "def";
		byte[] result = des.encrypt(plain, password);
	}
	
	public void decrypt() throws Exception{
		byte[] crypted = null;
		String password = null;
		byte[] result = des.decrypt(crypted, password);
	}
	
	

}
