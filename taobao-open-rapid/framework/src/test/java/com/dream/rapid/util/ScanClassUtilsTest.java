package com.dream.rapid.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class ScanClassUtilsTest extends TestCase {
	protected   final Logger logger = LoggerFactory.getLogger(getClass());
	public void testScanPackages() {
		List clazzes = null;
		try {
			clazzes = ScanClassUtils.scanPackages(null);
			fail();
		}catch(Exception e) {
			assertTrue(true);
		}
		
		clazzes = ScanClassUtils.scanPackages("com.dream.rapid.util");
		assertFalse(clazzes.isEmpty());
		assertTrue(contains(clazzes, "com.dream.rapid.util"));
//		logger.debug(clazzes);
		
		clazzes = ScanClassUtils.scanPackages("com.dream.rapid.**.*");
		assertFalse(clazzes.isEmpty());
		assertTrue(contains(clazzes, "com.dream.rapid.util"));
		assertTrue(contains(clazzes, "com.dream.rapid.base"));
//		logger.debug(clazzes);
		
		clazzes = ScanClassUtils.scanPackages("com.dream.rapid.**.*,cn.org.*_framework");
		assertFalse(clazzes.isEmpty());
		assertTrue(contains(clazzes, "com.dream.rapid"));
//		logger.debug(clazzes);
	}
	
	public boolean contains(List<String> clazzes,String containString) {
		for(String s : clazzes) {
			if(s.contains(containString)) {
				return true;
			}
		}
		return false;
	}
	
}
