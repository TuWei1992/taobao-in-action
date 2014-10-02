package com.dream.rapid.util;

import java.util.List;

import junit.framework.TestCase;

public class ScanClassUtilsTest extends TestCase {
	
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
		System.out.println(clazzes);
		
		clazzes = ScanClassUtils.scanPackages("com.dream.rapid.**.*");
		assertFalse(clazzes.isEmpty());
		assertTrue(contains(clazzes, "com.dream.rapid.util"));
		assertTrue(contains(clazzes, "com.dream.rapid.base"));
		System.out.println(clazzes);
		
		clazzes = ScanClassUtils.scanPackages("com.dream.rapid.**.*,cn.org.*_framework");
		assertFalse(clazzes.isEmpty());
		assertTrue(contains(clazzes, "com.dream.rapid"));
		System.out.println(clazzes);
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
