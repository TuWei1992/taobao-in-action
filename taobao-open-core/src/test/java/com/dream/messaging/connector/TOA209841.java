package com.dream.messaging.connector;

/**
 * @author Frank
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 * 
 *  <property name="totalMessageLength" class="Integer" length="4"
            status="M" />
        <property name="formatCode" class="string" length="5" status="M" />
        <property name="idType" class="string" length="1" status="M" />
        <property name="fromId" class="string" length="28" status="M" />
        <property name="fromCurrencyType" class="string" length="3"
            status="M" default="CNY" />
        <property name="fromCardBalance" class="Integer" length="8"
            status="M" />
        <property name="toId" class="string" length="28" status="M" />
        <property name="toCurrencyType" class="string" length="3"
            status="M" default="CNY" />
        <property name="toCardBalance" class="Integer" length="8" status="M" />
        <property name="mergedScore" class="Integer" length="8" status="M" />
 */
public class TOA209841 extends TOABody{
  
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
     * @return Returns the fromCardBalance.
     */
    public Integer getFromCardBalance() {
        return fromCardBalance;
    }
    /**
     * @param fromCardBalance The fromCardBalance to set.
     */
    public void setFromCardBalance(Integer fromCardBalance) {
        this.fromCardBalance = fromCardBalance;
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
     * @return Returns the toCardBalance.
     */
    public Integer getToCardBalance() {
        return toCardBalance;
    }
    /**
     * @param toCardBalance The toCardBalance to set.
     */
    public void setToCardBalance(Integer toCardBalance) {
        this.toCardBalance = toCardBalance;
    }
    /**
     * @return Returns the mergedScore.
     */
    public Integer getMergedScore() {
        return mergedScore;
    }
    /**
     * @param mergedScore The mergedScore to set.
     */
    public void setMergedScore(Integer mergedScore) {
        this.mergedScore = mergedScore;
    }

    private String idType;
    private String fromId;
    private String fromCurrencyType;
    private Integer fromCardBalance;
    private String toId;
    private String toCurrencyType;
    private Integer toCardBalance;
    private Integer mergedScore;

}
