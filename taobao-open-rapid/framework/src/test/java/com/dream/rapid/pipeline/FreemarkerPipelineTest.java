package com.dream.rapid.pipeline;

import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.dream.rapid.freemarker.FreemarkerTemplateProcessor;
import com.dream.rapid.freemarker.directive.DirectiveUtils;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;


public class FreemarkerPipelineTest {
	protected   final Logger logger = LoggerFactory.getLogger(getClass());
	Configuration conf = new Configuration();
	FreemarkerPipeline pipeline = null;
	@Before
	public void setUp() throws FileNotFoundException, Exception {
		DirectiveUtils.exposeRapidMacros(conf);
		conf.setTemplateLoader(new FileTemplateLoader(ResourceUtils.getFile("classpath:fortest_freemarker/pipeline")));
		pipeline = new FreemarkerPipeline(conf);
	}
	@Test
	public void testFreemarker()  throws Exception  {
		
		StringWriter out = new StringWriter();
		HashMap model = new HashMap();
		model.put("name", "badqiu");
		pipeline.pipeline("first.flt|second.flt | three.flt", model, out);
		
		logger.debug(out.toString());
		String expected = "<html><head>override_by_first</head><body>override_by_second</body><three><second>first:badqiu</second></three></html>";
		Assert.assertEquals(expected,out.toString().replaceAll("\\s+", ""));
	}
	
	@Test
	public void testOtherMethod() {
		pipeline.pipeline("first.flt|second.flt | three.flt", new Object(), new StringWriter());
	}
	
}
