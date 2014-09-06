package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.wireless.bunting.shop.shorturl.create response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WirelessBuntingShopShorturlCreateResponse extends TaobaoResponse {

	private static final long serialVersionUID = 8746771362752421926L;

	/** 
	 * 短链
	 */
	@ApiField("shorturl")
	private String shorturl;

	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}
	public String getShorturl( ) {
		return this.shorturl;
	}

}
