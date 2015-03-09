package com.dream.rapid.test.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;


public class Junit3TestMethodContextTest extends TestCase {
	protected   final Logger logger = LoggerFactory.getLogger(getClass());
    public void test_alibaba() {
        logger.debug("xxxx:"+getName());
        assertEquals("test_alibaba", getName());
    }

    public void test_foo() {
        logger.debug("xxxx:"+getName());
        assertEquals("test_foo", getName());
    }
    
}
