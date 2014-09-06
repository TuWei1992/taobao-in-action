package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.FeedDetail;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.weitao.feed.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WeitaoFeedGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 7331849982119563661L;

	/** 
	 * feed的详细信息
	 */
	@ApiField("feed_detail")
	private FeedDetail feedDetail;

	/** 
	 * 接口执行是否成功
	 */
	@ApiField("result")
	private Boolean result;

	public void setFeedDetail(FeedDetail feedDetail) {
		this.feedDetail = feedDetail;
	}
	public FeedDetail getFeedDetail( ) {
		return this.feedDetail;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	public Boolean getResult( ) {
		return this.result;
	}

}
