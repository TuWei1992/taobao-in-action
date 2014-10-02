package com.dream.rapid.struts;

import com.dream.rapid.struts.DynamicModuleConfig;
import junit.framework.TestCase;

public class DynamicModuleConfigTest extends TestCase {

	DynamicModuleConfig config = new DynamicModuleConfig();
		
	public void testIsValidClassName() {
		assertTrue(config.isValidClassName(DynamicModuleConfigTest.class.getName()));
		assertFalse(config.isValidClassName("xxxxxxxxxx.ErrorClassName"));
	}
}
