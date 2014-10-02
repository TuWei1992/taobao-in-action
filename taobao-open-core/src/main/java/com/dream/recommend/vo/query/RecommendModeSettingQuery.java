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


public class RecommendModeSettingQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** shopId */
	private java.lang.Long shopId;
	/** isSaledFirst */
	private java.lang.String isSaledFirst;
	/** saledNum */
	private java.lang.Integer saledNum;
	/** isStorageFiltered */
	private java.lang.String isStorageFiltered;
	/** storageNum */
	private java.lang.String storageNum;
	/** isOffshelvesFiltered */
	private java.lang.String isOffshelvesFiltered;
	/** offshelvesTime */
	private java.lang.Integer offshelvesTime;
	/** isOnshelvesFiltered */
	private java.lang.String isOnshelvesFiltered;
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
	
	public java.lang.String getIsSaledFirst() {
		return this.isSaledFirst;
	}
	
	public void setIsSaledFirst(java.lang.String value) {
		this.isSaledFirst = value;
	}
	
	public java.lang.Integer getSaledNum() {
		return this.saledNum;
	}
	
	public void setSaledNum(java.lang.Integer value) {
		this.saledNum = value;
	}
	
	public java.lang.String getIsStorageFiltered() {
		return this.isStorageFiltered;
	}
	
	public void setIsStorageFiltered(java.lang.String value) {
		this.isStorageFiltered = value;
	}
	
	public java.lang.String getStorageNum() {
		return this.storageNum;
	}
	
	public void setStorageNum(java.lang.String value) {
		this.storageNum = value;
	}
	
	public java.lang.String getIsOffshelvesFiltered() {
		return this.isOffshelvesFiltered;
	}
	
	public void setIsOffshelvesFiltered(java.lang.String value) {
		this.isOffshelvesFiltered = value;
	}
	
	public java.lang.Integer getOffshelvesTime() {
		return this.offshelvesTime;
	}
	
	public void setOffshelvesTime(java.lang.Integer value) {
		this.offshelvesTime = value;
	}
	
	public java.lang.String getIsOnshelvesFiltered() {
		return this.isOnshelvesFiltered;
	}
	
	public void setIsOnshelvesFiltered(java.lang.String value) {
		this.isOnshelvesFiltered = value;
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

