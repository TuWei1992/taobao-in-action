/**
 * 
 */
package com.dream.oauth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.taobao.api.internal.util.WebUtils;

/**
 * @author Frank
 * 
 */
public class OpenAuth {
	public static void main(String[] args) {
		// String url="https://oauth.taobao.com/token";
		String url = "https://oauth.tbsandbox.com/token";
		WebUtils.setIgnoreSSLCheck(true);
		Map<String, String> props = new HashMap<String, String>();
		props.put("grant_type", "authorization_code");
		/* 测试时，需把test参数换成自己应用对应的值 */
		props.put("code",
				"61011016b17be5783500d7d17747676adced2f659c3e1632074082786");
		props.put("client_id", "1021697746");
		props.put("client_secret", "sandbox18b7c5cc19a602a635ebb8bcd");
		props.put("redirect_uri", "http://www.dreamlabs.com:8080/taobao-open");
		props.put("view", "web");
		String s = "";
		try {
			s = WebUtils.doPost(url, props, 30000, 30000);
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
