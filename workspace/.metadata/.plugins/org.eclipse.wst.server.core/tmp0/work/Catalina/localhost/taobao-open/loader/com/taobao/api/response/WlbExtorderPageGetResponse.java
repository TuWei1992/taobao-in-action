package com.taobao.api.response;

import java.util.List;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import com.taobao.api.domain.WlbExtOrder;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.wlb.extorder.page.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WlbExtorderPageGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 5711596563358722343L;

	/** 
	 * 分页查询返回结果
	 */
	@ApiListField("ext_order_list")
	@ApiField("wlb_ext_order")
	private List<WlbExtOrder> extOrderList;

	/** 
	 * 总条数
	 */
	@ApiField("total_count")
	private Long totalCount;

	public void setExtOrderList(List<WlbExtOrder> extOrderList) {
		this.extOrderList = extOrderList;
	}
	public List<WlbExtOrder> getExtOrderList( ) {
		return this.extOrderList;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getTotalCount( ) {
		return this.totalCount;
	}

}
