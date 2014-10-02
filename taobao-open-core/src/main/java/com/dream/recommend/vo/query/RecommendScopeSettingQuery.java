/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.recommend.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.dream.rapid.base.BaseQuery;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class RecommendScopeSettingQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** shopId */
	private java.lang.Long shopId;
	/** scopeType */
	private java.lang.String scopeType;
	/** isEnabled */
	private java.lang.String isEnabled;
	/** lastModifiedBy */
	private java.lang.String lastModifiedBy;
	/** lastModifiedTime */
	private java.util.Date lastModifiedTimeBegin;
	private java.util.Date lastModifiedTimeEnd;

	public java.lang.Long getShopId() {
		return this.shopId;
	}
	
	public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}
	
	public java.lang.String getScopeType() {
		return this.scopeType;
	}
	
	public void setScopeType(java.lang.String value) {
		this.scopeType = value;
	}
	
	public java.lang.String getIsEnabled() {
		return this.isEnabled;
	}
	
	public void setIsEnabled(java.lang.String value) {
		this.isEnabled = value;
	}
	
	public java.lang.String getLastModifiedBy() {
		return this.lastModifiedBy;
	}
	
	public void setLastModifiedBy(java.lang.String value) {
		this.lastModifiedBy = value;
	}
	
	public java.util.Date getLastModifiedTimeBegin() {
		return this.lastModifiedTimeBegin;
	}
	
	public void setLastModifiedTimeBegin(java.util.Date value) {
		this.lastModifiedTimeBegin = value;
	}	
	
	public java.util.Date getLastModifiedTimeEnd() {
		return this.lastModifiedTimeEnd;
	}
	
	public void setLastModifiedTimeEnd(java.util.Date value) {
		this.lastModifiedTimeEnd = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

