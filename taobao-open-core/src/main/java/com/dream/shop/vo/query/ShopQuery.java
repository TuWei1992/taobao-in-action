/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.shop.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.dream.rapid.base.BaseQuery;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class ShopQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** sid */
	private java.lang.Long sid;
	/** cid */
	private java.lang.Long cid;
	/** nick */
	private java.lang.String nick;
	/** title */
	private java.lang.String title;
	/** desc */
	private java.lang.String desc;
	/** bulletin */
	private java.lang.String bulletin;
	/** picPath */
	private java.lang.String picPath;
	/** created */
	private java.util.Date createdBegin;
	private java.util.Date createdEnd;
	/** modified */
	private java.util.Date modifiedBegin;
	private java.util.Date modifiedEnd;

	public java.lang.Long getSid() {
		return this.sid;
	}
	
	public void setSid(java.lang.Long value) {
		this.sid = value;
	}
	
	public java.lang.Long getCid() {
		return this.cid;
	}
	
	public void setCid(java.lang.Long value) {
		this.cid = value;
	}
	
	public java.lang.String getNick() {
		return this.nick;
	}
	
	public void setNick(java.lang.String value) {
		this.nick = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getDesc() {
		return this.desc;
	}
	
	public void setDesc(java.lang.String value) {
		this.desc = value;
	}
	
	public java.lang.String getBulletin() {
		return this.bulletin;
	}
	
	public void setBulletin(java.lang.String value) {
		this.bulletin = value;
	}
	
	public java.lang.String getPicPath() {
		return this.picPath;
	}
	
	public void setPicPath(java.lang.String value) {
		this.picPath = value;
	}
	
	public java.util.Date getCreatedBegin() {
		return this.createdBegin;
	}
	
	public void setCreatedBegin(java.util.Date value) {
		this.createdBegin = value;
	}	
	
	public java.util.Date getCreatedEnd() {
		return this.createdEnd;
	}
	
	public void setCreatedEnd(java.util.Date value) {
		this.createdEnd = value;
	}
	
	public java.util.Date getModifiedBegin() {
		return this.modifiedBegin;
	}
	
	public void setModifiedBegin(java.util.Date value) {
		this.modifiedBegin = value;
	}	
	
	public java.util.Date getModifiedEnd() {
		return this.modifiedEnd;
	}
	
	public void setModifiedEnd(java.util.Date value) {
		this.modifiedEnd = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

