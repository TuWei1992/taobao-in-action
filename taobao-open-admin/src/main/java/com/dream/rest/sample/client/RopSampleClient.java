/**
 *  
 * 
 */
package com.dream.rest.sample.client;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dream.rest.client.ClientRequest;
import com.dream.rest.client.CompositeResponse;
import com.dream.rest.client.DefaultRopClient;
import com.dream.rest.request.TelephoneConverter;
import com.dream.rest.sample.request.CreateUserRequest;
import com.dream.rest.sample.request.LogonRequest;
import com.dream.rest.sample.response.LogonResponse;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class RopSampleClient {

    public static final String SERVER_URL = "http://localhost:8080/taobao-open-admin/router";

    private DefaultRopClient ropClient ;

    /**
     * 创建客户端对象
     * @param appKey
     * @param secret
     */
    public RopSampleClient(String appKey,String secret) {
        ropClient = new DefaultRopClient(SERVER_URL, appKey, secret);
        ropClient.setFormatParamName("messageFormat");
        ropClient.addRopConvertor(new TelephoneConverter());
    }

    /**
     * 登录系统，返回SessionId
     *
     * @return
     */
    public String logon(String userName, String password) {
        LogonRequest ropRequest = new LogonRequest();
        ropRequest.setUserName("tomson");
        ropRequest.setPassword("123456");
        CompositeResponse response = ropClient.buildClientRequest().get(ropRequest, LogonResponse.class, "user.logon", "1.0");
        String sessionId = ((LogonResponse) response.getSuccessResponse()).getSessionId();
        ropClient.setSessionId(sessionId);
        return sessionId;
    }
    
    /**
     * 注销系统
     * @param sessionId
     */
    public void logout(String sessionId) {
    	ropClient.setSessionId(sessionId);
        ropClient.buildClientRequest().get(LogonResponse.class, "user.logout", "1.0");
    }
    
    /**
     * 增加新用户
     * @param sessionId
     */
    public void add(String sessionId){
    	 CreateUserRequest request = new CreateUserRequest();
    	 request.setUserName("zhangfu");
    	 request.setPassword("pass1234");
         CompositeResponse response = ropClient.buildClientRequest().get(request, LogonResponse.class, "user.add", "1.0");
    }
    
    /**
     * 增加新用户
     * @param sessionId
     */
    public void add1(String sessionId){
    	 CreateUserRequest request = new CreateUserRequest();
    	 request.setUserName("zhangfu");
    	 request.setPassword("pass1234");
         CompositeResponse response = ropClient.buildClientRequest().get(request, LogonResponse.class, "user.add", "1.0");
    }
    
    
    /**
     * 增加新用户
     * @param sessionId
     */
    public void add2(String sessionId){
    	 CreateUserRequest request = new CreateUserRequest();
    	 request.setUserName("zhangfu");
    	 request.setPassword("pass1234");
         CompositeResponse response = ropClient.buildClientRequest().get(request, LogonResponse.class, "user.add", "1.0");
    }
    
    
    /**
     * 增加新用户
     * @param sessionId
     */
    public void add3(String sessionId){
    	 CreateUserRequest request = new CreateUserRequest();
    	 request.setUserName("zhangfu");
    	 request.setPassword("pass1234");
         CompositeResponse response = ropClient.buildClientRequest().get(request, LogonResponse.class, "user.add", "1.0");
    }
    
    /**
     * 增加新用户
     * @param sessionId
     */
    public void add4(String sessionId){
    	 CreateUserRequest request = new CreateUserRequest();
    	 request.setUserName("zhangfu");
    	 request.setPassword("pass1234");
         CompositeResponse response = ropClient.buildClientRequest().get(request, LogonResponse.class, "user.add", "1.0");
    }
    
    /**
     * 增加新用户
     * @param sessionId
     */
    public void add5(String sessionId){
    	 CreateUserRequest request = new CreateUserRequest();
    	 request.setUserName("zhangfu");
    	 request.setPassword("pass1234");
    	 request.setDate(new Date());
    	 request.setSalary(5000L);
         CompositeResponse response = ropClient.buildClientRequest().get(request, LogonResponse.class, "user.add", "5.0");
    }
    
    /**
     * 查询用户信息
     * @param sessionId
     */
    public void query(String sessionId){
   }

    public ClientRequest buildClientRequest(){
        return ropClient.buildClientRequest();
    }
    
    public static void main(String[] args){
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("sampleClientRopApplicationContext.xml");
    	RopSampleClient c = context.getBean("ropSampleClient",RopSampleClient.class);
    	String sessionId = c.logon("zhangsan", "mm");
    	c.add5(sessionId);
//    	c.logout(sessionId);
    }
    
}

