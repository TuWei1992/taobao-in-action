package com.dream.rapid.test.util;

import junit.framework.TestCase;



public class CurrentMethodUtilsTest extends TestCase{
    
    public void test_java_bb() {
        assertEquals("test_java_bb",CurrentMethodUtils.getCurrentMethodName());
        assertEquals("com.dream.rapid.test.util.CurrentMethodUtilsTest",CurrentMethodUtils.getCurrentClassName());
    }
    
}
