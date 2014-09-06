package com.taobao.api.response;

import java.util.List;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import com.taobao.api.domain.WaybillApplyUpdateInfo;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.wlb.waybill.update response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WlbWaybillUpdateResponse extends TaobaoResponse {

	private static final long serialVersionUID = 3221777158893417611L;

	/** 
	 * 更新返回
	 */
	@ApiListField("results")
	@ApiField("waybill_apply_update_info")
	private List<WaybillApplyUpdateInfo> results;

	public void setResults(List<WaybillApplyUpdateInfo> results) {
		this.results = results;
	}
	public List<WaybillApplyUpdateInfo> getResults( ) {
		return this.results;
	}

}
