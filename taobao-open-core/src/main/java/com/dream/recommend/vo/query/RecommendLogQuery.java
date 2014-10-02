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


public class RecommendLogQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.String id;
	/** shopId */
	private java.lang.String shopId;
	/** itemId */
	private java.lang.String itemId;
	/** itemName */
	private java.lang.String itemName;
	/** lastModifiedTime */
	private java.lang.String lastModifiedTime;

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
	
	public java.lang.String getLastModifiedTime() {
		return this.lastModifiedTime;
	}
	
	public void setLastModifiedTime(java.lang.String value) {
		this.lastModifiedTime = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

