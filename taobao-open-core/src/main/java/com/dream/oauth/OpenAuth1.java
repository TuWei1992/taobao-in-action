package com.dream.oauth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.taobao.api.ApiException;
import com.taobao.api.internal.util.WebUtils;
public class OpenAuth1 {
    /**
     * @param args
     * @throws ApiException 
     * @throws IOException 
     */
    public static void main(String[] args) throws ApiException, IOException {
    	
    	WebUtils.setIgnoreSSLCheck(true);
        /** 
         *  访问  https://oauth.tbsandbox.com/authorize?response_type=code&client_id=1021035674&redirect_uri=http://mini.tbsandbox.com&state=1212&scope=item&view=web
         * 取到code ，
         */
        //
        Map params = new HashMap();
        
        params.put("response_type", "code");
        params.put("client_id", "1021035674");
        params.put("redirect_uri", "http://mini.tbsandbox.com");
        params.put("state", "1212");
        params.put("scope", "item");
        params.put("view", "web");
        params.put("code", "v4OJpGaL6sZJtPAokVDanR0v162");
        params.put("client_secret", "sandbox68cb8f35e1d06f8154c1551de");
        params.put("grant_type", "authorization_code");     
        
        /**
         *  获取token 的值  */
          //System.out.print(WebUtils.doPost("https://oauth.tbsandbox.com/token", params, 0, 0));
          /**  {
            "w2_expires_in": 1800,
            "taobao_user_id": "2074082786",
            "taobao_user_nick": "sandbox_c_1",
            "w1_expires_in": 31536001,
            "re_expires_in": 15552000,
            "r2_expires_in": 259200,
            "hra_expires_in": "1800",
            "expires_in": 31536001,
            "token_type": "Bearer",
            "refresh_token": "6200019ed08a5596aca6c6ccf8dfhbcd15582381d437a452074082786",
             "access_token": "620101925972b8f5b3c3bfb9bcdfhb722b64a660ce34e732074082786",
            "r1_expires_in": 31536001
        }
*/
        
        /**
         * 通过取到的token ， 访问top
         */
        Map paramss = new HashMap();
        String serverUrl = "https://gw.api.tbsandbox.com/router/rest";
        
        paramss.put("method", "taobao.user.get");
        paramss.put("v", "2.0");
        paramss.put("fields", "user_id,uid,nick,sex");
        paramss.put("access_token", "620101925972b8f5b3c3bfb9bcdfhb722b64a660ce34e732074082786");
        paramss.put("format", "json");
        System.out.print(WebUtils.doPost(serverUrl, paramss , 0 , 0));
        /**
         * {"user_get_response":{"user":{"nick":"sandbox_c_1","uid":"04551356471221876972373904922375","user_id":2074082786}}}
         * {"user_get_response":{"user":{"nick":"sandbox_c_1","sex":"","uid":"04551356471221876972373904922375","user_id":2074082786}}}
         */
        }
}
