package com.dream;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public  class TestTest {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@BeforeClass
	public static void setUpClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	
	
	@Test
	public  void test() throws Exception {
//		FileInputStream file = new FileInputStream("C:\\t.txt");
//		System.out.println(file);
		Random ran = new Random(Integer.MAX_VALUE);
		System.out.println(Math.abs(Integer.MIN_VALUE+1));
		
		BigDecimal cent1 = new BigDecimal(0.01);
		
		BigDecimal cent2 = BigDecimal.valueOf(0.01);
		 BigDecimal cent = new BigDecimal(0.01).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		 System.out.println(cent2);
		 System.out.println(cent1);
		 System.out.println(cent);
		 
		 java.text.NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
		 System.out.println(nf.format(1111123.45));
	}

}
