package com.taobao.api.response;

import java.util.List;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import com.taobao.api.domain.WaybillApplyNewInfo;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.wlb.waybill.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WlbWaybillGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 6424496531136954745L;

	/** 
	 * 结果
	 */
	@ApiListField("results")
	@ApiField("waybill_apply_new_info")
	private List<WaybillApplyNewInfo> results;

	public void setResults(List<WaybillApplyNewInfo> results) {
		this.results = results;
	}
	public List<WaybillApplyNewInfo> getResults( ) {
		return this.results;
	}

}
