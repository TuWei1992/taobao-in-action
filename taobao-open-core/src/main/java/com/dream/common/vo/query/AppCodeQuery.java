/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.common.vo.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

import java.util.*;

import com.dream.rapid.base.*;
import com.dream.rapid.util.*;
import com.dream.rapid.page.*;

import com.dream.common.model.*;
import com.dream.common.dao.*;
import com.dream.common.service.*;
import com.dream.common.vo.query.*;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class AppCodeQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** groupId */
	private java.lang.String groupId;
	/** sequence */
	private java.lang.Integer sequence;
	/** codeNameCn */
	private java.lang.String codeNameCn;
	/** codeNameEn */
	private java.lang.String codeNameEn;
	/** codeValue */
	private java.lang.String codeValue;
	/** codeDesc */
	private java.lang.String codeDesc;

	public java.lang.String getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(java.lang.String value) {
		this.groupId = value;
	}
	
	public java.lang.Integer getSequence() {
		return this.sequence;
	}
	
	public void setSequence(java.lang.Integer value) {
		this.sequence = value;
	}
	
	public java.lang.String getCodeNameCn() {
		return this.codeNameCn;
	}
	
	public void setCodeNameCn(java.lang.String value) {
		this.codeNameCn = value;
	}
	
	public java.lang.String getCodeNameEn() {
		return this.codeNameEn;
	}
	
	public void setCodeNameEn(java.lang.String value) {
		this.codeNameEn = value;
	}
	
	public java.lang.String getCodeValue() {
		return this.codeValue;
	}
	
	public void setCodeValue(java.lang.String value) {
		this.codeValue = value;
	}
	
	public java.lang.String getCodeDesc() {
		return this.codeDesc;
	}
	
	public void setCodeDesc(java.lang.String value) {
		this.codeDesc = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

