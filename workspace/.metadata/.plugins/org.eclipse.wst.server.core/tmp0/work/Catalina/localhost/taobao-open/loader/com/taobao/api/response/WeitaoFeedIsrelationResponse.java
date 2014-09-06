package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.weitao.feed.isrelation response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WeitaoFeedIsrelationResponse extends TaobaoResponse {

	private static final long serialVersionUID = 8179376293548689533L;

	/** 
	 * 是否关注
	 */
	@ApiField("result")
	private Long result;

	public void setResult(Long result) {
		this.result = result;
	}
	public Long getResult( ) {
		return this.result;
	}

}
