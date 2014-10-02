package com.dream.rapid.lang.enums;

import junit.framework.TestCase;
import com.dream.rapid.util.fortest_enum.SomeTypeEnum;

public class EnumBaseUtilsTest extends TestCase {
	
	public void test() {
		SomeTypeEnum[] values = SomeTypeEnum.class.getEnumConstants();
		assertEquals(values[0],SomeTypeEnum.K1);
		assertEquals(values[1],SomeTypeEnum.K2);
	}
}
