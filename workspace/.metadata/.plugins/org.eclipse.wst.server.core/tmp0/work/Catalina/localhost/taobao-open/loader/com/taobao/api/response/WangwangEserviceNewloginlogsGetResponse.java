package com.taobao.api.response;

import java.util.List;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import com.taobao.api.domain.UserLoginlog;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.wangwang.eservice.newloginlogs.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WangwangEserviceNewloginlogsGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 1125247496527833483L;

	/** 
	 * 用户登录日志
	 */
	@ApiListField("userloginlogs")
	@ApiField("user_loginlog")
	private List<UserLoginlog> userloginlogs;

	public void setUserloginlogs(List<UserLoginlog> userloginlogs) {
		this.userloginlogs = userloginlogs;
	}
	public List<UserLoginlog> getUserloginlogs( ) {
		return this.userloginlogs;
	}

}
