package com.dream.auth.model;

import java.io.Serializable;
import java.util.Date;

public class Auth implements Serializable {
	
	
	public Auth(){
		
	}
	
	public Auth(Long userId){
		this.userId = userId;
	}
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_AUTH.USER_ID
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    private Long userId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_AUTH.IS_LOCK
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    private String isLock;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_AUTH.IS_ENABLE
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    private String isEnable;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_AUTH.TAOBAO_USER_NICK
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    private String taobaoUserNick;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_AUTH.TAOBAO_USER_ID
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    private String taobaoUserId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_AUTH.SUB_TAOBAO_USER_ID
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    private String subTaobaoUserId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_AUTH.SUB_TAOBAO_USER_NICK
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    private String subTaobaoUserNick;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_AUTH.REFRESHED_TIME
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    private Date refreshedTime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table TOP_AUTH
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_AUTH.USER_ID
     *
     * @return the value of TOP_AUTH.USER_ID
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_AUTH.USER_ID
     *
     * @param userId the value for TOP_AUTH.USER_ID
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public void setUserId(Long userId) {
        this.userId = userId == null ? null : userId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_AUTH.IS_LOCK
     *
     * @return the value of TOP_AUTH.IS_LOCK
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public String getIsLock() {
        return isLock;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_AUTH.IS_LOCK
     *
     * @param isLock the value for TOP_AUTH.IS_LOCK
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public void setIsLock(String isLock) {
        this.isLock = isLock == null ? null : isLock.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_AUTH.IS_ENABLE
     *
     * @return the value of TOP_AUTH.IS_ENABLE
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public String getIsEnable() {
        return isEnable;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_AUTH.IS_ENABLE
     *
     * @param isEnable the value for TOP_AUTH.IS_ENABLE
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable == null ? null : isEnable.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_AUTH.TAOBAO_USER_NICK
     *
     * @return the value of TOP_AUTH.TAOBAO_USER_NICK
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public String getTaobaoUserNick() {
        return taobaoUserNick;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_AUTH.TAOBAO_USER_NICK
     *
     * @param taobaoUserNick the value for TOP_AUTH.TAOBAO_USER_NICK
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public void setTaobaoUserNick(String taobaoUserNick) {
        this.taobaoUserNick = taobaoUserNick == null ? null : taobaoUserNick.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_AUTH.TAOBAO_USER_ID
     *
     * @return the value of TOP_AUTH.TAOBAO_USER_ID
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public String getTaobaoUserId() {
        return taobaoUserId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_AUTH.TAOBAO_USER_ID
     *
     * @param taobaoUserId the value for TOP_AUTH.TAOBAO_USER_ID
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public void setTaobaoUserId(String taobaoUserId) {
        this.taobaoUserId = taobaoUserId == null ? null : taobaoUserId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_AUTH.SUB_TAOBAO_USER_ID
     *
     * @return the value of TOP_AUTH.SUB_TAOBAO_USER_ID
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public String getSubTaobaoUserId() {
        return subTaobaoUserId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_AUTH.SUB_TAOBAO_USER_ID
     *
     * @param subTaobaoUserId the value for TOP_AUTH.SUB_TAOBAO_USER_ID
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public void setSubTaobaoUserId(String subTaobaoUserId) {
        this.subTaobaoUserId = subTaobaoUserId == null ? null : subTaobaoUserId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_AUTH.SUB_TAOBAO_USER_NICK
     *
     * @return the value of TOP_AUTH.SUB_TAOBAO_USER_NICK
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public String getSubTaobaoUserNick() {
        return subTaobaoUserNick;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_AUTH.SUB_TAOBAO_USER_NICK
     *
     * @param subTaobaoUserNick the value for TOP_AUTH.SUB_TAOBAO_USER_NICK
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public void setSubTaobaoUserNick(String subTaobaoUserNick) {
        this.subTaobaoUserNick = subTaobaoUserNick == null ? null : subTaobaoUserNick.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_AUTH.REFRESHED_TIME
     *
     * @return the value of TOP_AUTH.REFRESHED_TIME
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public Date getRefreshedTime() {
        return refreshedTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_AUTH.REFRESHED_TIME
     *
     * @param refreshedTime the value for TOP_AUTH.REFRESHED_TIME
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public void setRefreshedTime(Date refreshedTime) {
        this.refreshedTime = refreshedTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_AUTH
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof Auth)) {
            return false;
        }
        Auth other = (Auth) that;
        return this.getUserId() == null ? other == null : this.getUserId().equals(other.getUserId())
            && this.getIsLock() == null ? other == null : this.getIsLock().equals(other.getIsLock())
            && this.getIsEnable() == null ? other == null : this.getIsEnable().equals(other.getIsEnable())
            && this.getTaobaoUserNick() == null ? other == null : this.getTaobaoUserNick().equals(other.getTaobaoUserNick())
            && this.getTaobaoUserId() == null ? other == null : this.getTaobaoUserId().equals(other.getTaobaoUserId())
            && this.getSubTaobaoUserId() == null ? other == null : this.getSubTaobaoUserId().equals(other.getSubTaobaoUserId())
            && this.getSubTaobaoUserNick() == null ? other == null : this.getSubTaobaoUserNick().equals(other.getSubTaobaoUserNick())
            && this.getRefreshedTime() == null ? other == null : this.getRefreshedTime().equals(other.getRefreshedTime());
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_AUTH
     *
     *  Mon Sep 29 17:29:04 CST 2014
     */
    public int hashCode() {
        int hash = 23;
        if (getUserId() != null) {
            hash *= getUserId().hashCode();
        }
        if (getIsLock() != null) {
            hash *= getIsLock().hashCode();
        }
        if (getIsEnable() != null) {
            hash *= getIsEnable().hashCode();
        }
        if (getTaobaoUserNick() != null) {
            hash *= getTaobaoUserNick().hashCode();
        }
        if (getTaobaoUserId() != null) {
            hash *= getTaobaoUserId().hashCode();
        }
        if (getSubTaobaoUserId() != null) {
            hash *= getSubTaobaoUserId().hashCode();
        }
        if (getSubTaobaoUserNick() != null) {
            hash *= getSubTaobaoUserNick().hashCode();
        }
        if (getRefreshedTime() != null) {
            hash *= getRefreshedTime().hashCode();
        }
        return hash;
    }
}