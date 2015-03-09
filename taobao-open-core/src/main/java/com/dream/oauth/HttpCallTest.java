package com.dream.oauth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.dream.shop.model.ShopCategory;
import com.taobao.api.ApiException;


public class HttpCallTest {

    /**
     * @param args
     * @throws ApiException 
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonGenerationException 
     */
    public static void main(String[] args) throws ApiException, JsonGenerationException, JsonMappingException, IOException {
//        String appKey="test";
//        String appSecret="test";
//        String serverUrl = "http://gw.api.tbsandbox.com/router/rest";
//        /**创建client**/
//        DefaultTaobaoClient client = new DefaultTaobaoClient(serverUrl , appKey , appSecret, "json");
//        UserGetRequest req = new UserGetRequest();
//        /**设置API业务入参**/
//        req.setFields("nick,email");
//        req.setNick("sandbox_c_1");
//        UserGetResponse resp = client.execute(req );
//        /**正常请求，获取用户信息，由于email是需要用户授权才能获取，因此返回的信息中不包含emaill信息**/
//        logger.debug(resp.getBody());
//        
//        /**传入用户授权的sessionkey， 可获取用户 的email**/
//        resp = client.execute(req, "6101813112fbded1142381ece45b633a381c53976144a932074082786");
//        logger.debug(resp.getBody());
//        
//        /**传入不存在的nick ，对错误进行处理****/
//        req.setNick("sandbox_nouser");
//        resp = client.execute(req);
//        
//        if(resp.isSuccess()) {
//            logger.debug(resp.getBody());
//        } else {
//            /**如果subCode 以isp开头，可重试，否则是由于业务错误，请不要重试。***/
//            if(resp.getSubCode() != null && resp.getSubCode().startsWith("isp"))
//                resp = client.execute(req);
//            else 
//                logger.debug(resp.getBody());
//        }
    	List<ShopCategory> list = new ArrayList<ShopCategory>();
    	ShopCategory cat = new ShopCategory();
    	cat.setCid(1L);
    	cat.setParentCid(0L);
    	cat.setName("Level1");
    	
    	list.add(cat);
    	
    	ShopCategory cat01 = new ShopCategory();
    	cat01.setCid(11L);
    	cat01.setParentCid(1L);
    	cat01.setName("Level1");
    	list.add(cat01);
    	
    	ShopCategory cat1 = new ShopCategory();
    	cat1.setCid(2L);
    	cat1.setParentCid(0L);
    	cat1.setName("Level1");
    	list.add(cat1);
    	
    	ShopCategory cat2 = new ShopCategory();
    	cat2.setCid(3L);
    	cat2.setParentCid(0L);
    	cat2.setName("Level1");
    	list.add(cat2);
    	ObjectMapper objectMapper =  new ObjectMapper();
    	String s = objectMapper.writeValueAsString(list);
//    	logger.debug(s);
    	
    }
}