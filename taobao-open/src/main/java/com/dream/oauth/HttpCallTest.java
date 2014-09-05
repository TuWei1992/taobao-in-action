package com.dream.oauth;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.response.UserGetResponse;


public class HttpCallTest {

    /**
     * @param args
     * @throws ApiException 
     */
    public static void main(String[] args) throws ApiException {
        String appKey="test";
        String appSecret="test";
        String serverUrl = "http://gw.api.tbsandbox.com/router/rest";
        /**创建client**/
        DefaultTaobaoClient client = new DefaultTaobaoClient(serverUrl , appKey , appSecret, "json");
        UserGetRequest req = new UserGetRequest();
        /**设置API业务入参**/
        req.setFields("nick,email");
        req.setNick("sandbox_c_1");
        UserGetResponse resp = client.execute(req );
        /**正常请求，获取用户信息，由于email是需要用户授权才能获取，因此返回的信息中不包含emaill信息**/
        System.out.println(resp.getBody());
        
        /**传入用户授权的sessionkey， 可获取用户 的email**/
        resp = client.execute(req, "6101813112fbded1142381ece45b633a381c53976144a932074082786");
        System.out.println(resp.getBody());
        
        /**传入不存在的nick ，对错误进行处理****/
        req.setNick("sandbox_nouser");
        resp = client.execute(req);
        
        if(resp.isSuccess()) {
            System.out.println(resp.getBody());
        } else {
            /**如果subCode 以isp开头，可重试，否则是由于业务错误，请不要重试。***/
            if(resp.getSubCode() != null && resp.getSubCode().startsWith("isp"))
                resp = client.execute(req);
            else 
                System.out.println(resp.getBody());
        }
    }
}