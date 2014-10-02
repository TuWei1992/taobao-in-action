/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.auth.vo.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

import java.util.*;

import com.dream.rapid.base.*;
import com.dream.rapid.util.*;
import com.dream.rapid.page.*;

import com.dream.auth.model.*;
import com.dream.auth.dao.*;
import com.dream.auth.service.*;
import com.dream.auth.vo.query.*;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class AuthQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

	/** userId */
	private java.lang.Long userId;
	/** isLock */
	private java.lang.String isLock;
	/** isEnable */
	private java.lang.String isEnable;
	/** taobaoUserNick */
	private java.lang.String taobaoUserNick;
	/** taobaoUserId */
	private java.lang.String taobaoUserId;
	/** subTaobaoUserId */
	private java.lang.String subTaobaoUserId;
	/** subTaobaoUserNick */
	private java.lang.String subTaobaoUserNick;
	/** refreshedTime */
	private java.util.Date refreshedTimeBegin;
	private java.util.Date refreshedTimeEnd;

	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	
	public java.lang.String getIsLock() {
		return this.isLock;
	}
	
	public void setIsLock(java.lang.String value) {
		this.isLock = value;
	}
	
	public java.lang.String getIsEnable() {
		return this.isEnable;
	}
	
	public void setIsEnable(java.lang.String value) {
		this.isEnable = value;
	}
	
	public java.lang.String getTaobaoUserNick() {
		return this.taobaoUserNick;
	}
	
	public void setTaobaoUserNick(java.lang.String value) {
		this.taobaoUserNick = value;
	}
	
	public java.lang.String getTaobaoUserId() {
		return this.taobaoUserId;
	}
	
	public void setTaobaoUserId(java.lang.String value) {
		this.taobaoUserId = value;
	}
	
	public java.lang.String getSubTaobaoUserId() {
		return this.subTaobaoUserId;
	}
	
	public void setSubTaobaoUserId(java.lang.String value) {
		this.subTaobaoUserId = value;
	}
	
	public java.lang.String getSubTaobaoUserNick() {
		return this.subTaobaoUserNick;
	}
	
	public void setSubTaobaoUserNick(java.lang.String value) {
		this.subTaobaoUserNick = value;
	}
	
	public java.util.Date getRefreshedTimeBegin() {
		return this.refreshedTimeBegin;
	}
	
	public void setRefreshedTimeBegin(java.util.Date value) {
		this.refreshedTimeBegin = value;
	}	
	
	public java.util.Date getRefreshedTimeEnd() {
		return this.refreshedTimeEnd;
	}
	
	public void setRefreshedTimeEnd(java.util.Date value) {
		this.refreshedTimeEnd = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

