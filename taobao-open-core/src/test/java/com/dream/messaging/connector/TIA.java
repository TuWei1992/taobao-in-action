package com.dream.messaging.connector;

import com.dream.messaging.engine.TranCodeAware;

/**
 * @author Frank
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 */
public class TIA  implements TranCodeAware {
    /**
     * @return Returns the cICSTranCode.
     */
    public String getCICSTranCode() {
        return CICSTranCode;
    }
    /**
     * @param cICSTranCode The cICSTranCode to set.
     */
    public void setCICSTranCode(String cICSTranCode) {
        CICSTranCode = cICSTranCode;
    }
    /**
     * @return Returns the appCode.
     */
    public Integer getAppCode() {
        return appCode;
    }
    /**
     * @param appCode The appCode to set.
     */
    public void setAppCode(Integer appCode) {
        this.appCode = appCode;
    }
    /**
     * @return Returns the tranCode.
     */
    public Integer getTranCode() {
        return tranCode;
    }
    /**
     * @param tranCode The tranCode to set.
     */
    public void setTranCode(Integer tranCode) {
        this.tranCode = tranCode;
    }
    /**
     * @return Returns the frontEndTranCode.
     */
    public Integer getFrontEndTranCode() {
        return frontEndTranCode;
    }
    /**
     * @param frontEndTranCode The frontEndTranCode to set.
     */
    public void setFrontEndTranCode(Integer frontEndTranCode) {
        this.frontEndTranCode = frontEndTranCode;
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
     * @return Returns the tranSource.
     */
    public String getTranSource() {
        return tranSource;
    }
    /**
     * @param tranSource The tranSource to set.
     */
    public void setTranSource(String tranSource) {
        this.tranSource = tranSource;
    }
    /**
     * @return Returns the frontEndSN.
     */
    public String getFrontEndSN() {
        return frontEndSN;
    }
    /**
     * @param frontEndSN The frontEndSN to set.
     */
    public void setFrontEndSN(String frontEndSN) {
        this.frontEndSN = frontEndSN;
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
     * @return Returns the requestType.
     */
    public String getRequestType() {
        return requestType;
    }
    /**
     * @param requestType The requestType to set.
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
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
     * @return Returns the managerPwd1.
     */
    public String getManagerPwd1() {
        return managerPwd1;
    }
    /**
     * @param managerPwd1 The managerPwd1 to set.
     */
    public void setManagerPwd1(String managerPwd1) {
        this.managerPwd1 = managerPwd1;
    }
    /**
     * @return Returns the managerPwd2.
     */
    public String getManagerPwd2() {
        return managerPwd2;
    }
    /**
     * @param managerPwd2 The managerPwd2 to set.
     */
    public void setManagerPwd2(String managerPwd2) {
        this.managerPwd2 = managerPwd2;
    }
    /**
     * @return Returns the managerPwdInput1.
     */
    public String getManagerPwdInput1() {
        return managerPwdInput1;
    }
    /**
     * @param managerPwdInput1 The managerPwdInput1 to set.
     */
    public void setManagerPwdInput1(String managerPwdInput1) {
        this.managerPwdInput1 = managerPwdInput1;
    }
    /**
     * @return Returns the managerPwdInput2.
     */
    public String getManagerPwdInput2() {
        return managerPwdInput2;
    }
    /**
     * @param managerPwdInput2 The managerPwdInput2 to set.
     */
    public void setManagerPwdInput2(String managerPwdInput2) {
        this.managerPwdInput2 = managerPwdInput2;
    }
    /**
     * @return Returns the authCode1.
     */
    public String getAuthCode1() {
        return authCode1;
    }
    /**
     * @param authCode1 The authCode1 to set.
     */
    public void setAuthCode1(String authCode1) {
        this.authCode1 = authCode1;
    }
    /**
     * @return Returns the authCode2.
     */
    public String getAuthCode2() {
        return authCode2;
    }
    /**
     * @param authCode2 The authCode2 to set.
     */
    public void setAuthCode2(String authCode2) {
        this.authCode2 = authCode2;
    }
    /**
     * @return Returns the authCode3.
     */
    public String getAuthCode3() {
        return authCode3;
    }
    /**
     * @param authCode3 The authCode3 to set.
     */
    public void setAuthCode3(String authCode3) {
        this.authCode3 = authCode3;
    }
    /**
     * @return Returns the authCode4.
     */
    public String getAuthCode4() {
        return authCode4;
    }
    /**
     * @param authCode4 The authCode4 to set.
     */
    public void setAuthCode4(String authCode4) {
        this.authCode4 = authCode4;
    }
    /**
     * @return Returns the authCode5.
     */
    public String getAuthCode5() {
        return authCode5;
    }
    /**
     * @param authCode5 The authCode5 to set.
     */
    public void setAuthCode5(String authCode5) {
        this.authCode5 = authCode5;
    }
    /**
     * @return Returns the authCode6.
     */
    public String getAuthCode6() {
        return authCode6;
    }
    /**
     * @param authCode6 The authCode6 to set.
     */
    public void setAuthCode6(String authCode6) {
        this.authCode6 = authCode6;
    }
    /**
     * @return Returns the authCode7.
     */
    public String getAuthCode7() {
        return authCode7;
    }
    /**
     * @param authCode7 The authCode7 to set.
     */
    public void setAuthCode7(String authCode7) {
        this.authCode7 = authCode7;
    }
    /**
     * @return Returns the authCode8.
     */
    public String getAuthCode8() {
        return authCode8;
    }
    /**
     * @param authCode8 The authCode8 to set.
     */
    public void setAuthCode8(String authCode8) {
        this.authCode8 = authCode8;
    }
    /**
     * @return Returns the authCode9.
     */
    public String getAuthCode9() {
        return authCode9;
    }
    /**
     * @param authCode9 The authCode9 to set.
     */
    public void setAuthCode9(String authCode9) {
        this.authCode9 = authCode9;
    }
    /**
     * @return Returns the authCode10.
     */
    public String getAuthCode10() {
        return authCode10;
    }
    /**
     * @param authCode10 The authCode10 to set.
     */
    public void setAuthCode10(String authCode10) {
        this.authCode10 = authCode10;
    }
    /**
     * @return Returns the authLogFlag.
     */
    public String getAuthLogFlag() {
        return authLogFlag;
    }
    /**
     * @param authLogFlag The authLogFlag to set.
     */
    public void setAuthLogFlag(String authLogFlag) {
        this.authLogFlag = authLogFlag;
    }
    /**
     * @return Returns the lastTranLogNo.
     */
    public String getLastTranLogNo() {
        return lastTranLogNo;
    }
    /**
     * @param lastTranLogNo The lastTranLogNo to set.
     */
    public void setLastTranLogNo(String lastTranLogNo) {
        this.lastTranLogNo = lastTranLogNo;
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
     * @return Returns the counterLevel.
     */
    public Integer getCounterLevel() {
        return counterLevel;
    }
    /**
     * @param counterLevel The counterLevel to set.
     */
    public void setCounterLevel(Integer counterLevel) {
        this.counterLevel = counterLevel;
    }
    /**
     * @return Returns the terminalVersion.
     */
    public Integer getTerminalVersion() {
        return terminalVersion;
    }
    /**
     * @param terminalVersion The terminalVersion to set.
     */
    public void setTerminalVersion(Integer terminalVersion) {
        this.terminalVersion = terminalVersion;
    }
    /**
     * @return Returns the realCounter.
     */
    public String getRealCounter() {
        return realCounter;
    }
    /**
     * @param realCounter The realCounter to set.
     */
    public void setRealCounter(String realCounter) {
        this.realCounter = realCounter;
    }
    /**
     * @return Returns the tIAFilter.
     */
    public String getTIAFilter() {
        return TIAFilter;
    }
    /**
     * @param tIAFilter The tIAFilter to set.
     */
    public void setTIAFilter(String tIAFilter) {
        TIAFilter = tIAFilter;
    }
    private String CICSTranCode;
    private Integer appCode;
    private Integer tranCode;
    private Integer frontEndTranCode;
    private String terminalSN;
    private String tranSource;
    private String frontEndSN;
    private String counterNo;
    private String requestType;
    private Integer authLevel;
    private String manager1;
    private String manager2;
    private String managerPwd1;
    private String managerPwd2;
    private String managerPwdInput1;
    private String managerPwdInput2;
    private String authCode1;
    private String authCode2;
    private String authCode3;
    private String authCode4;
    private String authCode5;
    private String authCode6;
    private String authCode7;
    private String authCode8;
    private String authCode9;
    private String authCode10;
    private String authLogFlag;
    private String lastTranLogNo;
    private Integer dataCompressedFlag;
    private Integer dataEncryptedFlag;
    private Integer tranOrgCode;
    private Integer counterLevel;
    private Integer terminalVersion;
    private String realCounter;
    private String TIAFilter;
    
}
