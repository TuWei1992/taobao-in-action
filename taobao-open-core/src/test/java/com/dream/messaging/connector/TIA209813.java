package com.dream.messaging.connector;

/**
 * @author Frank
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 * 
 * <!--
渠道标记   X(1)    0=柜面、1=电话银行、2=网银、3=ATM、4=POS、5=多媒体终端、6=手机银行、7=家易通、9=财管平台（低柜）、L=第三方系统、Z=客服整合平台、W=IVR、V=卡中心兑换系统、S=卡中心收费系统、P=策略收单系统
证件类型    X(1)    0-贷记卡号，1-清算账号ID
证件号 X(28)   左对齐、右补空格
币种  X(3)    CNY/USD；当证件类型为0时，若该字段为空则查询人民币和美元的积分余额    -->
 */
public class TIA209813  {
    
    private TIA tia;
    
    /**
     * @return Returns the chanelFlag.
     */
    public String getChanelFlag() {
        return chanelFlag;
    }
    /**
     * @param chanelFlag The chanelFlag to set.
     */
    public void setChanelFlag(String chanelFlag) {
        this.chanelFlag = chanelFlag;
    }
    /**
     * @return Returns the idType.
     */
    public String getIdType() {
        return idType;
    }
    /**
     * @param idType The idType to set.
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }
    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return Returns the currencyType.
     */
    public String getCurrencyType() {
        return currencyType;
    }
    /**
     * @param currencyType The currencyType to set.
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }
    /**
     * @param tia The tia to set.
     */
    public void setTia(TIA tia) {
        this.tia = tia;
    }
    /**
     * @return Returns the tia.
     */
    public TIA getTia() {
        return tia;
    }
    private String chanelFlag;
    private String idType;
    private String id;
    private String currencyType;
}
