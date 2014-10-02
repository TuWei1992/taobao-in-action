/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.dream.item.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.dream.rapid.base.BaseQuery;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class ItemQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** numIid */
	private java.lang.Long numIid;
	/** cid */
	private java.lang.Integer cid;
	/** sellerCids */
	private java.lang.String sellerCids;
	/** props */
	private java.lang.String props;
	/** picUrl */
	private java.lang.String picUrl;
	/** num */
	private java.lang.String num;
	/** listTime */
	private java.util.Date listTimeBegin;
	private java.util.Date listTimeEnd;
	/** delistTime */
	private java.util.Date delistTimeBegin;
	private java.util.Date delistTimeEnd;
	/** price */
	private Long price;
	/** hasDiscount */
	private java.lang.String hasDiscount;
	/** hasInvoice */
	private java.lang.String hasInvoice;
	/** hasWarranty */
	private java.lang.String hasWarranty;
	/** hasShowcase */
	private java.lang.String hasShowcase;
	/** modified */
	private java.util.Date modifiedBegin;
	private java.util.Date modifiedEnd;
	/** approveStatus */
	private java.lang.String approveStatus;
	/** postageId */
	private java.lang.Integer postageId;
	/** isVirtual */
	private java.lang.String isVirtual;
	/** isTaobao */
	private java.lang.String isTaobao;
	/** isEx */
	private java.lang.String isEx;
	/** title */
	private java.lang.String title;
	/** nick */
	private java.lang.String nick;
	/** type */
	private java.lang.String type;
	/** validThru */
	private java.lang.String validThru;
	/** outerId */
	private java.lang.String outerId;
	/** onsaleStatus */
	private java.lang.String onsaleStatus;
	/** soldQuantity */
	private java.lang.Integer soldQuantity;
	/** id */
	private java.lang.Integer id;

	public java.lang.Long getNumIid() {
		return this.numIid;
	}
	
	public void setNumIid(java.lang.Long value) {
		this.numIid = value;
	}
	
	public java.lang.Integer getCid() {
		return this.cid;
	}
	
	public void setCid(java.lang.Integer value) {
		this.cid = value;
	}
	
	public java.lang.String getSellerCids() {
		return this.sellerCids;
	}
	
	public void setSellerCids(java.lang.String value) {
		this.sellerCids = value;
	}
	
	public java.lang.String getProps() {
		return this.props;
	}
	
	public void setProps(java.lang.String value) {
		this.props = value;
	}
	
	public java.lang.String getPicUrl() {
		return this.picUrl;
	}
	
	public void setPicUrl(java.lang.String value) {
		this.picUrl = value;
	}
	
	public java.lang.String getNum() {
		return this.num;
	}
	
	public void setNum(java.lang.String value) {
		this.num = value;
	}
	
	public java.util.Date getListTimeBegin() {
		return this.listTimeBegin;
	}
	
	public void setListTimeBegin(java.util.Date value) {
		this.listTimeBegin = value;
	}	
	
	public java.util.Date getListTimeEnd() {
		return this.listTimeEnd;
	}
	
	public void setListTimeEnd(java.util.Date value) {
		this.listTimeEnd = value;
	}
	
	public java.util.Date getDelistTimeBegin() {
		return this.delistTimeBegin;
	}
	
	public void setDelistTimeBegin(java.util.Date value) {
		this.delistTimeBegin = value;
	}	
	
	public java.util.Date getDelistTimeEnd() {
		return this.delistTimeEnd;
	}
	
	public void setDelistTimeEnd(java.util.Date value) {
		this.delistTimeEnd = value;
	}
	
	public Long getPrice() {
		return this.price;
	}
	
	public void setPrice(Long value) {
		this.price = value;
	}
	
	public java.lang.String getHasDiscount() {
		return this.hasDiscount;
	}
	
	public void setHasDiscount(java.lang.String value) {
		this.hasDiscount = value;
	}
	
	public java.lang.String getHasInvoice() {
		return this.hasInvoice;
	}
	
	public void setHasInvoice(java.lang.String value) {
		this.hasInvoice = value;
	}
	
	public java.lang.String getHasWarranty() {
		return this.hasWarranty;
	}
	
	public void setHasWarranty(java.lang.String value) {
		this.hasWarranty = value;
	}
	
	public java.lang.String getHasShowcase() {
		return this.hasShowcase;
	}
	
	public void setHasShowcase(java.lang.String value) {
		this.hasShowcase = value;
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
	
	public java.lang.String getApproveStatus() {
		return this.approveStatus;
	}
	
	public void setApproveStatus(java.lang.String value) {
		this.approveStatus = value;
	}
	
	public java.lang.Integer getPostageId() {
		return this.postageId;
	}
	
	public void setPostageId(java.lang.Integer value) {
		this.postageId = value;
	}
	
	public java.lang.String getIsVirtual() {
		return this.isVirtual;
	}
	
	public void setIsVirtual(java.lang.String value) {
		this.isVirtual = value;
	}
	
	public java.lang.String getIsTaobao() {
		return this.isTaobao;
	}
	
	public void setIsTaobao(java.lang.String value) {
		this.isTaobao = value;
	}
	
	public java.lang.String getIsEx() {
		return this.isEx;
	}
	
	public void setIsEx(java.lang.String value) {
		this.isEx = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getNick() {
		return this.nick;
	}
	
	public void setNick(java.lang.String value) {
		this.nick = value;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	public java.lang.String getValidThru() {
		return this.validThru;
	}
	
	public void setValidThru(java.lang.String value) {
		this.validThru = value;
	}
	
	public java.lang.String getOuterId() {
		return this.outerId;
	}
	
	public void setOuterId(java.lang.String value) {
		this.outerId = value;
	}
	
	public java.lang.String getOnsaleStatus() {
		return this.onsaleStatus;
	}
	
	public void setOnsaleStatus(java.lang.String value) {
		this.onsaleStatus = value;
	}
	
	public java.lang.Integer getSoldQuantity() {
		return this.soldQuantity;
	}
	
	public void setSoldQuantity(java.lang.Integer value) {
		this.soldQuantity = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

