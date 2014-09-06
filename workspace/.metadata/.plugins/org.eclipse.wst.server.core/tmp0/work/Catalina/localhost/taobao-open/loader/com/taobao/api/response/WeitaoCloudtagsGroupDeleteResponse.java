package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.weitao.cloudtags.group.delete response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WeitaoCloudtagsGroupDeleteResponse extends TaobaoResponse {

	private static final long serialVersionUID = 3228896884213627466L;

	/** 
	 * 业务是否执行成功，result仅是系统调用是否成功
	 */
	@ApiField("is_success")
	private Boolean isSuccess;

	/** 
	 * 是否成功
	 */
	@ApiField("result")
	private Boolean result;

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Boolean getIsSuccess( ) {
		return this.isSuccess;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	public Boolean getResult( ) {
		return this.result;
	}

}
