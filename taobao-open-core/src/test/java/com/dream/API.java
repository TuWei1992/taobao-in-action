/**
 * 
 */
package com.dream;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;

import com.dream.auth.model.Auth;
import com.dream.oauth.OAuth;
import com.taobao.api.ApiException;
import com.taobao.api.Constants;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.response.UserSellerGetResponse;

/**
 * @author Frank
 *
 */
public class API {
	 protected static String url = "http://gw.api.tbsandbox.com/router/rest";//沙箱环境调用地址
     //正式环境需要设置为:http://gw.api.taobao.com/router/rest
     protected static String appkey = "1021697746";//1021697746
     protected static String appSecret = "sandbox18b7c5cc19a602a635ebb8bcd";//sandbox18b7c5cc19a602a635ebb8bcd
     protected static String sessionkey = "61011016b17be5783500d7d17747676adced2f659c3e1632074082786"; //如 沙箱测试帐号sandbox_c_1授权后得到的sessionkey
     public static void testUserGet() {
         TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret,Constants.FORMAT_XML);//实例化TopClient类
         UserSellerGetRequest req = new UserSellerGetRequest();//实例化具体API对应的Request类
         req.setFields("nick,user_id,type");
         UserSellerGetResponse response;
         try {
             response = client.execute(req,sessionkey); //执行API请求并打印结果
             System.out.println("result:"+response.getBody());
          
         } catch (ApiException e) {
         // deal error
         }
     }
     public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
//    	 API.testUserGet();
    	 String s = "{\"w2_expires_in\": 0,  \"taobao_user_id\": \"263685215\",  \"taobao_user_nick\": \"%E5%95%86%E5%AE%B6%E6%B5%8B%E8%AF%95%E5%B8%90%E5%8F%B752\",  \"w1_expires_in\": 1800,  \"re_expires_in\": 0,  \"r2_expires_in\": 0,  \"expires_in\": 86400,  \"token_type\": \"Bearer\",  \"refresh_token\": \"6200e1909ca29b04685c49d67f5ZZ3675347c0c6d5abccd263685215\",  \"access_token\": \"6200819d9366af1383023a19907ZZf9048e4c14fd56333b263685215\",  \"r1_expires_in\": 1800}";
    	 ObjectMapper objectMapper = new ObjectMapper();
    	 OAuth auth = objectMapper.readValue(s, OAuth.class);
    	 System.out.print(auth);
    	 Auth a = new Auth();
    	 BeanUtils.copyProperties(auth,a);
    	 System.out.print(a);
     }
}
