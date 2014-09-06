package com.taobao.api.response;

import java.util.Date;
import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.wlb.extorder.cancle response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WlbExtorderCancleResponse extends TaobaoResponse {

	private static final long serialVersionUID = 2632196326952273813L;

	/** 
	 * 错误编码列表
	 */
	@ApiField("error_code_list")
	private String errorCodeList;

	/** 
	 * 修改时间，只有在取消成功的情况下，才有效
	 */
	@ApiField("modify_time")
	private Date modifyTime;

	public void setErrorCodeList(String errorCodeList) {
		this.errorCodeList = errorCodeList;
	}
	public String getErrorCodeList( ) {
		return this.errorCodeList;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Date getModifyTime( ) {
		return this.modifyTime;
	}

}
