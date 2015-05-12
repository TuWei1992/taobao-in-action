/**
 *  
 * 
 */
package com.dream.rest.sample.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dream.rest.client.ClientRequest;
import com.dream.rest.client.CompositeResponse;
import com.dream.rest.client.DefaultRopClient;
import com.dream.rest.request.TelephoneConverter;
import com.dream.rest.sample.request.Address;
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

    public static final String SERVER_URL = "http://localhost:8080/taobao-open-admin/router?appKey={appKey}&method={method}&v={v}&messageFormat={messageFormat}&locale={locale}&sign={sign}";

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
        ropRequest.setUserName(userName);
        ropRequest.setPassword(password);
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
     * 增加新用户，简单对象
     * @param sessionId
     */
    public void add4(String sessionId){
    	 CreateUserRequest request = new CreateUserRequest();
    	 request.setUserName("zhangfu");
    	 request.setPassword("pass1234");
         CompositeResponse response = ropClient.buildClientRequest().get(request, LogonResponse.class, "user.add", "1.0");
    }
    
    /**
     * 增加新用户，有复合对象Address的情况
     * @param sessionId
     */
    public void add5(String sessionId){
    	 CreateUserRequest request = new CreateUserRequest();
    	 request.setUserName("zhangfu");
    	 request.setPassword("pass1234");
    	 request.setDate(new Date());
    	 request.setSalary(5000L);
         CompositeResponse response = ropClient.buildClientRequest().post(request, LogonResponse.class, "user.add", "5.0");
    }
    
    /**
     * 增加新用户,请求报文有列表Addresses的情况
     * @param sessionId
     */
    public void add6(String sessionId){
    	 CreateUserRequest request = new CreateUserRequest();
    	 request.setUserName("zhangfu");
    	 request.setPassword("pass1234");
    	 request.setDate(new Date());
    	 request.setSalary(5000L);
    	 
    	 List<Address> addreses = new ArrayList<Address>();
    	 
    	 Address address1 = new Address();
    	 address1.setZoneCode("001");
    	 address1.setDoorCode("002");
    	 addreses.add(address1);
    	 
    	 Address address2 = new Address();
    	 address2.setZoneCode("002");
    	 address2.setDoorCode("003");
    	 addreses.add(address2);
    	 
    	 request.setAddresses(addreses);
    	 
         CompositeResponse response = ropClient.buildClientRequest().post(LogonResponse.class, "user.add", "6.0");;
         
    }
    
    /**
     * 增加新用户,请求报文有列表Addresses的情况
     * @param sessionId
     */
    public void add7(String ropRequestString){
         CompositeResponse response = ropClient.buildClientRequest().post(ropRequestString, LogonResponse.class, "user.add", "6.0");;
         
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
    
}

