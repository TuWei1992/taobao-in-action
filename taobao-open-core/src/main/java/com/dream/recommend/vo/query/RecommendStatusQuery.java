/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.recommend.vo.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

import java.util.*;

import com.dream.rapid.base.*;
import com.dream.rapid.util.*;
import com.dream.rapid.page.*;

import com.dream.recommend.model.*;
import com.dream.recommend.dao.*;
import com.dream.recommend.service.*;
import com.dream.recommend.vo.query.*;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class RecommendStatusQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** shopId */
	private java.lang.Long shopId;
	/** status */
	private java.lang.String status;
	/** lastModifiedTime */
	private java.util.Date lastModifiedTimeBegin;
	private java.util.Date lastModifiedTimeEnd;
	/** lastModifiedBy */
	private java.lang.Long lastModifiedBy;
	/** updatedTime */
	private java.util.Date updatedTimeBegin;
	private java.util.Date updatedTimeEnd;
	/** updatedUserId */
	private java.lang.Long updatedUserId;

	public java.lang.Long getShopId() {
		return this.shopId;
	}
	
	public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
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
	
	public java.lang.Long getLastModifiedBy() {
		return this.lastModifiedBy;
	}
	
	public void setLastModifiedBy(java.lang.Long value) {
		this.lastModifiedBy = value;
	}
	
	public java.util.Date getUpdatedTimeBegin() {
		return this.updatedTimeBegin;
	}
	
	public void setUpdatedTimeBegin(java.util.Date value) {
		this.updatedTimeBegin = value;
	}	
	
	public java.util.Date getUpdatedTimeEnd() {
		return this.updatedTimeEnd;
	}
	
	public void setUpdatedTimeEnd(java.util.Date value) {
		this.updatedTimeEnd = value;
	}
	
	public java.lang.Long getUpdatedUserId() {
		return this.updatedUserId;
	}
	
	public void setUpdatedUserId(java.lang.Long value) {
		this.updatedUserId = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

