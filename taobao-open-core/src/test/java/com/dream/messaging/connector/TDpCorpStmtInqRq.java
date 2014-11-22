package com.dream.messaging.connector;

import java.io.Serializable;

public class TDpCorpStmtInqRq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * CommonRqHdr
	 */
	private String version="1.0";
	private String rqUID;
	private String numTranCode;
	private String clearDate;
	private String tranDate;
	private String tranTime;
	private String directSendFlag;
	private String channelId;
	
	private String mediumType;
	private String acctNo;
	private String currency;
	private String fcyType;
	private String pwd;
	private String beginDt;
	private String endDt;
	private String mode;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getRqUID() {
		return rqUID;
	}
	public void setRqUID(String rqUID) {
		this.rqUID = rqUID;
	}
	public String getNumTranCode() {
		return numTranCode;
	}
	public void setNumTranCode(String numTranCode) {
		this.numTranCode = numTranCode;
	}
	public String getClearDate() {
		return clearDate;
	}
	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}
	public String getTranDate() {
		return tranDate;
	}
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}
	public String getTranTime() {
		return tranTime;
	}
	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}
	public String getDirectSendFlag() {
		return directSendFlag;
	}
	public void setDirectSendFlag(String directSendFlag) {
		this.directSendFlag = directSendFlag;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getMediumType() {
		return mediumType;
	}
	public void setMediumType(String mediumType) {
		this.mediumType = mediumType;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getFcyType() {
		return fcyType;
	}
	public void setFcyType(String fcyType) {
		this.fcyType = fcyType;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getBeginDt() {
		return beginDt;
	}
	public void setBeginDt(String beginDt) {
		this.beginDt = beginDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
}
