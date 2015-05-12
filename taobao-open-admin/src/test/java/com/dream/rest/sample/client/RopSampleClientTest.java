/**
 *  
 * 
 */
package com.dream.rest.sample.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.web.util.UriUtils;

import com.dream.rest.config.SystemParameterNames;
import com.taobao.api.internal.util.WebUtils;

/**
 * <pre>
 * 功能说明：对ROP Client的测试类
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
@ContextConfiguration(locations={"classpath:sampleClientRopApplicationContext.xml"})
public class RopSampleClientTest extends  AbstractJUnit4SpringContextTests {
	
	private RopSampleClient ropSampleClient;

    /**
     * 创建客户端对象
     * @param appKey
     * @param secret
     */
	@Before
    public void init() {
		ropSampleClient =  applicationContext.getBean("ropSampleClient",RopSampleClient.class);
    }

    /**
     * 登录系统，返回SessionId
     *
     * @return
     */
    @Test
    public void logonTest() {
    	String userName = "张三12345";
    	String password = "密码12345";
    	ropSampleClient.logon(userName, password);
    }
    
    
    @Test
    public void logon1Test() {
    	String userName = "zhang12345";
    	String password = "mm12345";
    	ropSampleClient.logon(userName, password);
    }
    
    @Test
    public void logon2Test() throws IOException {
    	String userName = "zhang12345";
    	String password = "mm12345";
    	ropSampleClient.logon(userName, password);
    }
    
    /**
     * 注销系统
     * @param sessionId
     */
    @Test
    public void logoutTest() {
    	String sessionId = "123456";
    	ropSampleClient.logout(sessionId);
    }
    
    /**
     * 增加新用户
     * @param sessionId
     */
    @Test
    public void addTest(){
    	String sessionId = "123456";
    	ropSampleClient.add(sessionId);
    }
    
    /**
     * 增加新用户
     * @param sessionId
     */
    @Test
    public void add1Test(){
    	String sessionId = "123456";
    	ropSampleClient.add1(sessionId);
    }
    
    
    /**
     * 增加新用户
     * @param sessionId
     */
    @Test
    public void add2Test(){
    	String sessionId = "123456";
    	ropSampleClient.add1(sessionId);
    }
    
    
    /**
     * 增加新用户
     * @param sessionId
     */
    @Test
    public void add3Test(){
    	String sessionId = "123456";
    	ropSampleClient.add3(sessionId);
    }
    
    /**
     * 增加新用户，简单对象
     * @param sessionId
     */
    @Test
    public void add4Test(){
    	String sessionId = "123456";
    	ropSampleClient.add4(sessionId);
    }
    
    /**
     * 增加新用户，有复合对象Address的情况
     * @param sessionId
     */
    @Test
    public void add5Test(){
    	String sessionId = "123456";
    	ropSampleClient.add5(sessionId);
    }
    
    /**
     * 增加新用户,请求报文有列表Addresses的情况
     * @param sessionId
     */
    @Test
    public void add6Test(){
    	String sessionId = "123456";
    	ropSampleClient.add6(sessionId);
    }
    
    /**
     * Request String
     * @param sessionId
     */
    @Test
    public void add7Test(){
    	String request = "<xml><root>test</root></xml>";
    	ropSampleClient.add7(request);
    }
    
    
    /**
     * 查询用户信息
     * @param sessionId
     */
    @Test
    public void queryTest(){
    	String sessionId = "123456";
    	ropSampleClient.query(sessionId);
   }
    
    @Test
    public void urlTest() throws Exception{
    	String queryParam = "t=张三&p=李四";
    	String encoding = "UTF-8";
    	String encoded = UriUtils.encodeQueryParam(queryParam, encoding);
    	System.out.println(encoded);
    	String decoded = UriUtils.decode(encoded, encoding);
    	System.out.println(decoded);
   }
    
    
}

