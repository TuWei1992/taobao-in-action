package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.SubscInfoWrapper;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.weike.subscinfo.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WeikeSubscinfoGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 1131335943133757745L;

	/** 
	 * 返回结果
	 */
	@ApiField("result")
	private SubscInfoWrapper result;

	public void setResult(SubscInfoWrapper result) {
		this.result = result;
	}
	public SubscInfoWrapper getResult( ) {
		return this.result;
	}

}
