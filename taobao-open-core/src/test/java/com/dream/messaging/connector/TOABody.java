package com.dream.messaging.connector;

/**
 * @author Frank
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 */
public class TOABody {
//    输出数据实际总长度   9(4)    整个数据区长度，包括本字段4字节在内
//    五位格式码   X(5)    SCD04
//    错误码所属应用符    X(2)    
//    错误信息码   9(4)    
//    CURS-POS    9(4)    0001
//    错误信息    X(56)  
//    <property name="totalMessageLength" class="int" length="4" status="M" />
//    <property name="formatCode" class="string" length="5" status="M" />
//    <property name="errorAppCode" class="string" length="2" status="M" />
//    <property name="errorCode" class="int" length="4" status="M" />
//    <property name="cursPos" class="int" length="4" status="M" />
//    <property name="errorMessage" class="string" length="56" />
    private TOA toa;
    private Integer totalMessageLength;
    private String formatCode;
    private String errorAppCode;
    private Integer errorCode;
    /**
     * @return Returns the toa.
     */
    public TOA getToa() {
        return toa;
    }
    /**
     * @param toa The toa to set.
     */
    public void setToa(TOA toa) {
        this.toa = toa;
    }
    /**
     * @return Returns the totalMessageLength.
     */
    public Integer getTotalMessageLength() {
        return totalMessageLength;
    }
    /**
     * @param totalMessageLength The totalMessageLength to set.
     */
    public void setTotalMessageLength(Integer totalMessageLength) {
        this.totalMessageLength = totalMessageLength;
    }
    /**
     * @return Returns the formatCode.
     */
    public String getFormatCode() {
        return formatCode;
    }
    /**
     * @param formatCode The formatCode to set.
     */
    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }
    /**
     * @return Returns the errorAppCode.
     */
    public String getErrorAppCode() {
        return errorAppCode;
    }
    /**
     * @param errorAppCode The errorAppCode to set.
     */
    public void setErrorAppCode(String errorAppCode) {
        this.errorAppCode = errorAppCode;
    }
    /**
     * @return Returns the errorCode.
     */
    public Integer getErrorCode() {
        return errorCode;
    }
    /**
     * @param errorCode The errorCode to set.
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
    /**
     * @return Returns the cursPos.
     */
    public Integer getCursPos() {
        return cursPos;
    }
    /**
     * @param cursPos The cursPos to set.
     */
    public void setCursPos(Integer cursPos) {
        this.cursPos = cursPos;
    }
    /**
     * @return Returns the errorMessage.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    /**
     * @param errorMessage The errorMessage to set.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    private Integer cursPos;
    private String errorMessage;

}
