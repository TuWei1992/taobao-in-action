package com.dream.rapid.test.context;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class TestMethodContextTest {
    @Rule public TestName testName = new TestName();
    protected   final Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void test_alibaba() {
        logger.debug(getClass()+"");
        logger.debug("current test method:"+ testName.getMethodName());
        Assert.assertEquals("test_alibaba", testName.getMethodName());
    }

    @Test
    public void test_foo() {
        Assert.assertEquals("test_foo", testName.getMethodName());
    }
    
}
