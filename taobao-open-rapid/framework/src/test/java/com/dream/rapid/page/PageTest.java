package com.dream.rapid.page;


import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author badqiu
 */
public class PageTest extends TestCase {
	protected   final Logger logger = LoggerFactory.getLogger(getClass());
	public void test() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map m = BeanUtils.describe(new Page(1,2,100));
		logger.debug(m+"");
		logger.debug(m.keySet()+"");
	}
}
