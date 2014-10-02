package com.dream.oauth;

import org.codehaus.jackson.annotate.JsonProperty;

public class OAuth {
	
	@JsonProperty("w2_expires_in")
	public String getW2ExpiresIn() {
		return w2_expires_in;
	}

	public void setW2ExpiresIn(String w2_expires_in) {
		this.w2_expires_in = w2_expires_in;
	}
	
	@JsonProperty("taobao_user_id")
	public String getTaobaoUserId() {
		return taobao_user_id;
	}

	public void setTaobaoUserId(String taobao_user_id) {
		this.taobao_user_id = taobao_user_id;
	}
	
	@JsonProperty("taobao_user_nick")
	public String getTaobaoUserNick() {
		return taobao_user_nick;
	}

	public void setTaobaoUserNick(String taobao_user_nick) {
		this.taobao_user_nick = taobao_user_nick;
	}
	
	@JsonProperty("w1_expires_in")
	public int getW1ExpiresIn() {
		return w1_expires_in;
	}

	public void setW1ExpiresIn(int w1_expires_in) {
		this.w1_expires_in = w1_expires_in;
	}
	
	@JsonProperty("re_expires_in")
	public int getReExpiresIn() {
		return re_expires_in;
	}

	public void setReExpiresIn(int re_expires_in) {
		this.re_expires_in = re_expires_in;
	}
	
	@JsonProperty("r2_expires_in")
	public int getR2ExpiresIn() {
		return r2_expires_in;
	}

	public void setR2ExpiresIn(int r2_expires_in) {
		this.r2_expires_in = r2_expires_in;
	}
	
	@JsonProperty("expires_in")
	public int getExpiresIn() {
		return expires_in;
	}

	public void setExpiresIn(int expires_in) {
		this.expires_in = expires_in;
	}
	
	@JsonProperty("token_type")
	public String getTokenType() {
		return token_type;
	}

	public void setTokenType(String token_type) {
		this.token_type = token_type;
	}
	
	@JsonProperty("refresh_token")
	public String getRefreshToken() {
		return refresh_token;
	}

	public void setRefreshToken(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	
	@JsonProperty("access_token")
	public String getAccessToken() {
		return access_token;
	}

	public void setAccessToken(String access_token) {
		this.access_token = access_token;
	}

	@JsonProperty("r1_expires_in")
	public int getR1ExpiresIn() {
		return r1_expires_in;
	}

	public void setR1ExpiresIn(int r1_expires_in) {
		this.r1_expires_in = r1_expires_in;
	}

	private String w2_expires_in;// : 1800,
	private String taobao_user_id;// "2074082786",
	private String taobao_user_nick;// : "sandbox_c_1",//
	private int w1_expires_in;// ": 12960000,
	private int re_expires_in;// ": 15552000,
	private int r2_expires_in;// : 259200,
	private int expires_in;// 12960000,
	private String token_type;// ": "Bearer",
	private String refresh_token;// :
									// "6202021b06eb95ddf9a2b6c901dbfhjb09849c856e86cb62074082786",
	private String access_token;// :6202b214990ef709425ab7188966fhj2f72a1c928ca1aa12074082786",
	private int r1_expires_in;// :12960000

}
