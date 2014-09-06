package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.weike.performance.put response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WeikePerformancePutResponse extends TaobaoResponse {

	private static final long serialVersionUID = 2697737457192551511L;

	/** 
	 * 返回结果
	 */
	@ApiField("result")
	private Boolean result;

	public void setResult(Boolean result) {
		this.result = result;
	}
	public Boolean getResult( ) {
		return this.result;
	}

}
