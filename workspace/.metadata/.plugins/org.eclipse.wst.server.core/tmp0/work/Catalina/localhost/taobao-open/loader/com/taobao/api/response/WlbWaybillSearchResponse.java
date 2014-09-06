package com.taobao.api.response;

import java.util.List;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import com.taobao.api.domain.WaybillApplySubscriptionInfo;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.wlb.waybill.search response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WlbWaybillSearchResponse extends TaobaoResponse {

	private static final long serialVersionUID = 4779175347455233648L;

	/** 
	 * 查询相关数据(cp_type 1是直营，2是加盟
)
	 */
	@ApiListField("results")
	@ApiField("waybill_apply_subscription_info")
	private List<WaybillApplySubscriptionInfo> results;

	public void setResults(List<WaybillApplySubscriptionInfo> results) {
		this.results = results;
	}
	public List<WaybillApplySubscriptionInfo> getResults( ) {
		return this.results;
	}

}
