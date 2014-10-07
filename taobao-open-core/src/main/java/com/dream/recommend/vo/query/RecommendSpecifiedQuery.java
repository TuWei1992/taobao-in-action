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


public class RecommendSpecifiedQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.String id;
	/** shopId */
	private java.lang.String shopId;
	/** type */
	private java.lang.String type;
	/** itemId */
	private java.lang.String itemId;
	/** itemName */
	private java.lang.String itemName;
	/** lastModifiedBy */
	private java.lang.Long lastModifiedBy;
	/** lastModifiedTime */
	private java.util.Date lastModifiedTimeBegin;
	private java.util.Date lastModifiedTimeEnd;

	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getShopId() {
		return this.shopId;
	}
	
	public void setShopId(java.lang.String value) {
		this.shopId = value;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	public java.lang.String getItemId() {
		return this.itemId;
	}
	
	public void setItemId(java.lang.String value) {
		this.itemId = value;
	}
	
	public java.lang.String getItemName() {
		return this.itemName;
	}
	
	public void setItemName(java.lang.String value) {
		this.itemName = value;
	}
	
	public java.lang.Long getLastModifiedBy() {
		return this.lastModifiedBy;
	}
	
	public void setLastModifiedBy(java.lang.Long value) {
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

