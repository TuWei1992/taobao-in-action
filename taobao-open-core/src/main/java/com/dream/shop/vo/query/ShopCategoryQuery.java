/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.shop.vo.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

import java.util.*;

import com.dream.rapid.base.*;
import com.dream.rapid.util.*;
import com.dream.rapid.page.*;

import com.dream.shop.model.*;
import com.dream.shop.dao.*;
import com.dream.shop.service.*;
import com.dream.shop.vo.query.*;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class ShopCategoryQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** cid */
	private java.lang.Long cid;
	/** parentCid */
	private java.lang.Long parentCid;
	/** name */
	private java.lang.String name;
	/** picUrl */
	private java.lang.String picUrl;
	/** sortOrder */
	private java.lang.Integer sortOrder;
	/** type */
	private java.lang.String type;
	/** shopId */
	private java.lang.Long shopId;
	/** createTime */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** refreshTime */
	private java.util.Date refreshTimeBegin;
	private java.util.Date refreshTimeEnd;

	public java.lang.Long getCid() {
		return this.cid;
	}
	
	public void setCid(java.lang.Long value) {
		this.cid = value;
	}
	
	public java.lang.Long getParentCid() {
		return this.parentCid;
	}
	
	public void setParentCid(java.lang.Long value) {
		this.parentCid = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getPicUrl() {
		return this.picUrl;
	}
	
	public void setPicUrl(java.lang.String value) {
		this.picUrl = value;
	}
	
	public java.lang.Integer getSortOrder() {
		return this.sortOrder;
	}
	
	public void setSortOrder(java.lang.Integer value) {
		this.sortOrder = value;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	public java.lang.Long getShopId() {
		return this.shopId;
	}
	
	public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
	}	
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(java.util.Date value) {
		this.createTimeEnd = value;
	}
	
	public java.util.Date getRefreshTimeBegin() {
		return this.refreshTimeBegin;
	}
	
	public void setRefreshTimeBegin(java.util.Date value) {
		this.refreshTimeBegin = value;
	}	
	
	public java.util.Date getRefreshTimeEnd() {
		return this.refreshTimeEnd;
	}
	
	public void setRefreshTimeEnd(java.util.Date value) {
		this.refreshTimeEnd = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

