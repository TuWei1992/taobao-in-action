package com.dream.rapid.base;


import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.dream.rapid.test.context.TestMethodContextExecutionListener;

/**
 * 本基类主要为子类指定好要装载的spring配置文件
 * 及在运行测试前通过dbunit插入测试数据在数据库中,运行完测试删除测试数据 
 *
 * @author Frank
 * 请设置好要装载的spring配置文件,一般开发数据库与测试数据库分开
 * 所以你要装载的资源文件应改为"classpath:/spring/*-test-resource.xml"
 */
@TestExecutionListeners(listeners = TestMethodContextExecutionListener.class) // TestMethodContextExecutionListener用于在@Before时可以得到测试方法名称
public class BaseTestCase extends AbstractJUnit4SpringContextTests  {
	
}
