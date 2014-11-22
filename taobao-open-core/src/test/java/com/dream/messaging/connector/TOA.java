package com.dream.messaging.connector;

import com.dream.messaging.engine.TranCodeAware;

/**
 * @author Frank
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 */
public class TOA implements TranCodeAware {
    private String CICSHeader;
    private String outPutMessageType;
    private String appliationType;
    private String applicationType;
    private Integer infomationCode;
    private Integer position;
    private String terminalNo;
    private String terminalSN;
    private Integer followingTranCode;
    private Integer followingAppCode;
    private String tranFlag;
    private Integer tranDate;
    private Integer tranTime;
    private Integer accountingDate;
    private Integer tranLogNo;
    private String counterNo;
    private String seqNo;
    private Integer pageFlag;
    private Integer dataCompressedFlag;
    private Integer dataEncryptedFlag;
    private Integer tranOrgCode;
    private String authLog;
    private String billFlag;
    private Integer authLevel;
    private String manager1;
    private String manager2;
    private String unused;
    private Integer totalLength;
    /**
     * @return Returns the cICSHeader.
     */
    public String getCICSHeader() {
        return CICSHeader;
    }
    /**
     * @param cICSHeader The cICSHeader to set.
     */
    public void setCICSHeader(String cICSHeader) {
        CICSHeader = cICSHeader;
    }
    /**
     * @return Returns the outPutMessageType.
     */
    public String getOutPutMessageType() {
        return outPutMessageType;
    }
    /**
     * @param outPutMessageType The outPutMessageType to set.
     */
    public void setOutPutMessageType(String outPutMessageType) {
        this.outPutMessageType = outPutMessageType;
    }
    /**
     * @return Returns the appliationType.
     */
    public String getAppliationType() {
        return appliationType;
    }
    /**
     * @param appliationType The appliationType to set.
     */
    public void setAppliationType(String appliationType) {
        this.appliationType = appliationType;
    }
    /**
     * @return Returns the applicationType.
     */
    public String getApplicationType() {
        return applicationType;
    }
    /**
     * @param applicationType The applicationType to set.
     */
    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }
    /**
     * @return Returns the infomationCode.
     */
    public Integer getInfomationCode() {
        return infomationCode;
    }
    /**
     * @param infomationCode The infomationCode to set.
     */
    public void setInfomationCode(Integer infomationCode) {
        this.infomationCode = infomationCode;
    }
    /**
     * @return Returns the position.
     */
    public Integer getPosition() {
        return position;
    }
    /**
     * @param position The position to set.
     */
    public void setPosition(Integer position) {
        this.position = position;
    }
    /**
     * @return Returns the terminalNo.
     */
    public String getTerminalNo() {
        return terminalNo;
    }
    /**
     * @param terminalNo The terminalNo to set.
     */
    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }
    /**
     * @return Returns the terminalSN.
     */
    public String getTerminalSN() {
        return terminalSN;
    }
    /**
     * @param terminalSN The terminalSN to set.
     */
    public void setTerminalSN(String terminalSN) {
        this.terminalSN = terminalSN;
    }
    /**
     * @return Returns the followingTranCode.
     */
    public Integer getFollowingTranCode() {
        return followingTranCode;
    }
    /**
     * @param followingTranCode The followingTranCode to set.
     */
    public void setFollowingTranCode(Integer followingTranCode) {
        this.followingTranCode = followingTranCode;
    }
    /**
     * @return Returns the followingAppCode.
     */
    public Integer getFollowingAppCode() {
        return followingAppCode;
    }
    /**
     * @param followingAppCode The followingAppCode to set.
     */
    public void setFollowingAppCode(Integer followingAppCode) {
        this.followingAppCode = followingAppCode;
    }
    /**
     * @return Returns the tranFlag.
     */
    public String getTranFlag() {
        return tranFlag;
    }
    /**
     * @param tranFlag The tranFlag to set.
     */
    public void setTranFlag(String tranFlag) {
        this.tranFlag = tranFlag;
    }
    /**
     * @return Returns the tranDate.
     */
    public Integer getTranDate() {
        return tranDate;
    }
    /**
     * @param tranDate The tranDate to set.
     */
    public void setTranDate(Integer tranDate) {
        this.tranDate = tranDate;
    }
    /**
     * @return Returns the tranTime.
     */
    public Integer getTranTime() {
        return tranTime;
    }
    /**
     * @param tranTime The tranTime to set.
     */
    public void setTranTime(Integer tranTime) {
        this.tranTime = tranTime;
    }
    /**
     * @return Returns the accountingDate.
     */
    public Integer getAccountingDate() {
        return accountingDate;
    }
    /**
     * @param accountingDate The accountingDate to set.
     */
    public void setAccountingDate(Integer accountingDate) {
        this.accountingDate = accountingDate;
    }
    /**
     * @return Returns the tranLogNo.
     */
    public Integer getTranLogNo() {
        return tranLogNo;
    }
    /**
     * @param tranLogNo The tranLogNo to set.
     */
    public void setTranLogNo(Integer tranLogNo) {
        this.tranLogNo = tranLogNo;
    }
    /**
     * @return Returns the counterNo.
     */
    public String getCounterNo() {
        return counterNo;
    }
    /**
     * @param counterNo The counterNo to set.
     */
    public void setCounterNo(String counterNo) {
        this.counterNo = counterNo;
    }
    /**
     * @return Returns the seqNo.
     */
    public String getSeqNo() {
        return seqNo;
    }
    /**
     * @param seqNo The seqNo to set.
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
    /**
     * @return Returns the pageFlag.
     */
    public Integer getPageFlag() {
        return pageFlag;
    }
    /**
     * @param pageFlag The pageFlag to set.
     */
    public void setPageFlag(Integer pageFlag) {
        this.pageFlag = pageFlag;
    }
    /**
     * @return Returns the dataCompressedFlag.
     */
    public Integer getDataCompressedFlag() {
        return dataCompressedFlag;
    }
    /**
     * @param dataCompressedFlag The dataCompressedFlag to set.
     */
    public void setDataCompressedFlag(Integer dataCompressedFlag) {
        this.dataCompressedFlag = dataCompressedFlag;
    }
    /**
     * @return Returns the dataEncryptedFlag.
     */
    public Integer getDataEncryptedFlag() {
        return dataEncryptedFlag;
    }
    /**
     * @param dataEncryptedFlag The dataEncryptedFlag to set.
     */
    public void setDataEncryptedFlag(Integer dataEncryptedFlag) {
        this.dataEncryptedFlag = dataEncryptedFlag;
    }
    /**
     * @return Returns the tranOrgCode.
     */
    public Integer getTranOrgCode() {
        return tranOrgCode;
    }
    /**
     * @param tranOrgCode The tranOrgCode to set.
     */
    public void setTranOrgCode(Integer tranOrgCode) {
        this.tranOrgCode = tranOrgCode;
    }
    /**
     * @return Returns the authLog.
     */
    public String getAuthLog() {
        return authLog;
    }
    /**
     * @param authLog The authLog to set.
     */
    public void setAuthLog(String authLog) {
        this.authLog = authLog;
    }
    /**
     * @return Returns the billFlag.
     */
    public String getBillFlag() {
        return billFlag;
    }
    /**
     * @param billFlag The billFlag to set.
     */
    public void setBillFlag(String billFlag) {
        this.billFlag = billFlag;
    }
    /**
     * @return Returns the authLevel.
     */
    public Integer getAuthLevel() {
        return authLevel;
    }
    /**
     * @param authLevel The authLevel to set.
     */
    public void setAuthLevel(Integer authLevel) {
        this.authLevel = authLevel;
    }
    /**
     * @return Returns the manager1.
     */
    public String getManager1() {
        return manager1;
    }
    /**
     * @param manager1 The manager1 to set.
     */
    public void setManager1(String manager1) {
        this.manager1 = manager1;
    }
    /**
     * @return Returns the manager2.
     */
    public String getManager2() {
        return manager2;
    }
    /**
     * @param manager2 The manager2 to set.
     */
    public void setManager2(String manager2) {
        this.manager2 = manager2;
    }
    /**
     * @return Returns the unused.
     */
    public String getUnused() {
        return unused;
    }
    /**
     * @param unused The unused to set.
     */
    public void setUnused(String unused) {
        this.unused = unused;
    }
    /**
     * @return Returns the totalLength.
     */
    public Integer getTotalLength() {
        return totalLength;
    }
    /**
     * @param totalLength The totalLength to set.
     */
    public void setTotalLength(Integer totalLength) {
        this.totalLength = totalLength;
    }
	@Override
	public Integer getTranCode() {
		// TODO Auto-generated method stub
		return null;
	}
}
