package com.dream.messaging.connector;

import java.io.Serializable;

public class TDpCorpStmtInqRs implements Serializable{

	private static final long serialVersionUID = 1L;
	private String version="1.0";
	private String statusCode;
	private String serverStatusCode;
	private String rqUID;
	private String numTranCode;
	
	private String recNo;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getServerStatusCode() {
		return serverStatusCode;
	}

	public void setServerStatusCode(String serverStatusCode) {
		this.serverStatusCode = serverStatusCode;
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

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}
}
