package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.waimai.shop.close response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WaimaiShopCloseResponse extends TaobaoResponse {

	private static final long serialVersionUID = 7832365869255162591L;

	/** 
	 * 成功：0,失败：1
	 */
	@ApiField("ret_code")
	private String retCode;

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetCode( ) {
		return this.retCode;
	}

}
