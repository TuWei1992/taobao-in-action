package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.ShopCityResult;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.waimai.shopbycity.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WaimaiShopbycityGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 3474111373943469689L;

	/** 
	 * 以city维度分的店铺list
	 */
	@ApiField("result")
	private ShopCityResult result;

	public void setResult(ShopCityResult result) {
		this.result = result;
	}
	public ShopCityResult getResult( ) {
		return this.result;
	}

}
