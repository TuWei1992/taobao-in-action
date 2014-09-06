package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.TakeoutShopPage;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.waimai.shop.list response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WaimaiShopListResponse extends TaobaoResponse {

	private static final long serialVersionUID = 8176534282441147972L;

	/** 
	 * 数据结果集
	 */
	@ApiField("result")
	private TakeoutShopPage result;

	public void setResult(TakeoutShopPage result) {
		this.result = result;
	}
	public TakeoutShopPage getResult( ) {
		return this.result;
	}

}
