package com.dream.rapid.freemarker.loader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.rapid.freemarker.FreemarkerTemplateProcessor;
import com.dream.rapid.test.hsql.HSQLMemDataSourceUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class DataSourceTemplateLoaderTest {
	DataSourceTemplateLoader loader = new DataSourceTemplateLoader();
	DataSource ds = null;
	Map model = new HashMap();
	Configuration conf = new Configuration();
	@Before
	public void setUp() throws Exception {
		ds = HSQLMemDataSourceUtils.getDataSource(DataSourceTemplateLoaderTest.class,"UTF-8");
		
		loader.setDataSource(ds);
		loader.setTableName("template");
		loader.setTemplateNameColumn("template_name");
		loader.setTemplateContentColumn("template_content");
		loader.setTimestampColumn("last_modified");
		loader.afterPropertiesSet();
		
		conf.setDefaultEncoding("UTF-8");
		conf.setTemplateLoader(loader);
		
		model.put("username", "qiu");
		model.put("sex", "男");
	}
	
	protected   final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void test_get_template() throws Exception, TemplateException {
		testProcessTemplate();
		
		Thread.sleep(1000 * 5);
		
		logger.debug("**************************");
		testProcessTemplate();
	}
	
	private void testProcessTemplate() throws IOException {
		Template t = conf.getTemplate("/test/template.ftl");
		String out = FreemarkerTemplateProcessor.processTemplateIntoString(t, model);
		logger.debug(out);
		Assert.assertEquals("用户名: qiu 性别:男",out);
	}
	
}
