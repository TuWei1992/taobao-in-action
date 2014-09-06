package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.wlb.order.schedule.rule.add response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WlbOrderScheduleRuleAddResponse extends TaobaoResponse {

	private static final long serialVersionUID = 8877949551159422878L;

	/** 
	 * 新增成功的订单调度规则id
	 */
	@ApiField("schedule_rule_id")
	private Long scheduleRuleId;

	public void setScheduleRuleId(Long scheduleRuleId) {
		this.scheduleRuleId = scheduleRuleId;
	}
	public Long getScheduleRuleId( ) {
		return this.scheduleRuleId;
	}

}
