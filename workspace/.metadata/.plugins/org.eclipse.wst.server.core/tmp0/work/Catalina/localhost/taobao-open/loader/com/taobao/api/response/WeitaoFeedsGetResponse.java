package com.taobao.api.response;

import java.util.List;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import com.taobao.api.domain.SimpleFeed;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.weitao.feeds.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WeitaoFeedsGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 5246748619326743817L;

	/** 
	 * 返回的feed列表
	 */
	@ApiListField("feed_list")
	@ApiField("simple_feed")
	private List<SimpleFeed> feedList;

	/** 
	 * 接口执行是否成功
	 */
	@ApiField("result")
	private Boolean result;

	/** 
	 * feed的总记录数
	 */
	@ApiField("total_count")
	private Long totalCount;

	public void setFeedList(List<SimpleFeed> feedList) {
		this.feedList = feedList;
	}
	public List<SimpleFeed> getFeedList( ) {
		return this.feedList;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	public Boolean getResult( ) {
		return this.result;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getTotalCount( ) {
		return this.totalCount;
	}

}
