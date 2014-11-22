package com.dream.messaging.connector;

/**
 * @author Frank
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 * 
 *<property name="chanelFlag" class="string" length="1" status="M" />
        <property name="idType" class="string" length="1" status="M" />
        <property name="fromId" class="string" length="28" status="M" />
        <property name="fromCurrencyType" class="string" length="3" status="M" default="CNY" />
        <property name="toId" class="string" length="28" status="M" />
        <property name="toCurrencyType" class="string" length="3" status="M" default="CNY" />
        <property name="mergeAmount" class="Integer" length="8"/>
        <property name="mergeReason" class="string" length="80" status="M"/>
 */
public class TIA209841 {
    private TIA tia;
    private String chanelFlag;
    private String idType;
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
     * @return Returns the fromId.
     */
    public String getFromId() {
        return fromId;
    }
    /**
     * @param fromId The fromId to set.
     */
    public void setFromId(String fromId) {
        this.fromId = fromId;
    }
    /**
     * @return Returns the fromCurrencyType.
     */
    public String getFromCurrencyType() {
        return fromCurrencyType;
    }
    /**
     * @param fromCurrencyType The fromCurrencyType to set.
     */
    public void setFromCurrencyType(String fromCurrencyType) {
        this.fromCurrencyType = fromCurrencyType;
    }
    /**
     * @return Returns the toId.
     */
    public String getToId() {
        return toId;
    }
    /**
     * @param toId The toId to set.
     */
    public void setToId(String toId) {
        this.toId = toId;
    }
    /**
     * @return Returns the toCurrencyType.
     */
    public String getToCurrencyType() {
        return toCurrencyType;
    }
    /**
     * @param toCurrencyType The toCurrencyType to set.
     */
    public void setToCurrencyType(String toCurrencyType) {
        this.toCurrencyType = toCurrencyType;
    }
    /**
     * @return Returns the mergeAmount.
     */
    public Integer getMergeAmount() {
        return mergeAmount;
    }
    /**
     * @param mergeAmount The mergeAmount to set.
     */
    public void setMergeAmount(Integer mergeAmount) {
        this.mergeAmount = mergeAmount;
    }
    /**
     * @return Returns the mergeReason.
     */
    public String getMergeReason() {
        return mergeReason;
    }
    /**
     * @param mergeReason The mergeReason to set.
     */
    public void setMergeReason(String mergeReason) {
        this.mergeReason = mergeReason;
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
    private String fromId;
    private String fromCurrencyType;
    private String toId;
    private String toCurrencyType;
    private Integer mergeAmount;
    private String mergeReason;
    
}
