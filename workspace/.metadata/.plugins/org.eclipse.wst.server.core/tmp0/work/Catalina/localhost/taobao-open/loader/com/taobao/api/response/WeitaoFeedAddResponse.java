package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.weitao.feed.add response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WeitaoFeedAddResponse extends TaobaoResponse {

	private static final long serialVersionUID = 7643151837833114535L;

	/** 
	 * 发布feed成功后返回的feedId
	 */
	@ApiField("feed_id")
	private Long feedId;

	/** 
	 * 是否成功
	 */
	@ApiField("result")
	private Boolean result;

	public void setFeedId(Long feedId) {
		this.feedId = feedId;
	}
	public Long getFeedId( ) {
		return this.feedId;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	public Boolean getResult( ) {
		return this.result;
	}

}
