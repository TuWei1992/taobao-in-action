/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.menu.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.dream.rapid.base.BaseQuery;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class MenuQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** menuNameCn */
	private java.lang.String menuNameCn;
	/** menuId */
	private java.lang.String menuId;
	/** lftValue */
	private java.lang.String lftValue;
	/** rgtValue */
	private java.lang.String rgtValue;
	/** orderNum */
	private java.lang.Integer orderNum;
	/** menuNameEn */
	private java.sql.Array menuNameEn;

	public java.lang.String getMenuNameCn() {
		return this.menuNameCn;
	}
	
	public void setMenuNameCn(java.lang.String value) {
		this.menuNameCn = value;
	}
	
	public java.lang.String getMenuId() {
		return this.menuId;
	}
	
	public void setMenuId(java.lang.String value) {
		this.menuId = value;
	}
	
	public java.lang.String getLftValue() {
		return this.lftValue;
	}
	
	public void setLftValue(java.lang.String value) {
		this.lftValue = value;
	}
	
	public java.lang.String getRgtValue() {
		return this.rgtValue;
	}
	
	public void setRgtValue(java.lang.String value) {
		this.rgtValue = value;
	}
	
	public java.lang.Integer getOrderNum() {
		return this.orderNum;
	}
	
	public void setOrderNum(java.lang.Integer value) {
		this.orderNum = value;
	}
	
	public java.sql.Array getMenuNameEn() {
		return this.menuNameEn;
	}
	
	public void setMenuNameEn(java.sql.Array value) {
		this.menuNameEn = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

