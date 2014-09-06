package com.taobao.api.response;

import java.util.Date;
import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.wlb.extorder.create response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WlbExtorderCreateResponse extends TaobaoResponse {

	private static final long serialVersionUID = 8734822511414679162L;

	/** 
	 * 订单创建时间
	 */
	@ApiField("create_time")
	private Date createTime;

	/** 
	 * 物流宝外部订单创建成功后，返回物流宝的外部订单编号；如果订单创建失败，该字段为空。
	 */
	@ApiField("ext_order_code")
	private String extOrderCode;

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTime( ) {
		return this.createTime;
	}

	public void setExtOrderCode(String extOrderCode) {
		this.extOrderCode = extOrderCode;
	}
	public String getExtOrderCode( ) {
		return this.extOrderCode;
	}

}
