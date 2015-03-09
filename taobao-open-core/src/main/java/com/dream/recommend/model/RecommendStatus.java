package com.dream.recommend.model;

import java.io.Serializable;
import java.util.Date;

public class RecommendStatus implements Serializable {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_RECOMMEND_STATUS.SHOP_ID
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    private Long shopId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_RECOMMEND_STATUS.STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    private String status;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_RECOMMEND_STATUS.LAST_MODIFIED_TIME
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    private Date lastModifiedTime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_RECOMMEND_STATUS.LAST_MODIFIED_BY
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    private Long lastModifiedBy;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_RECOMMEND_STATUS.UPDATED_TIME
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    private Date updatedTime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_RECOMMEND_STATUS.UPDATED_USER_ID
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    private Long updatedUserId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_RECOMMEND_STATUS.IS_RECOMMENDED
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    private String isRecommended;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_RECOMMEND_STATUS.SHOP_ID
     *
     * @return the value of TOP_RECOMMEND_STATUS.SHOP_ID
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_RECOMMEND_STATUS.SHOP_ID
     *
     * @param shopId the value for TOP_RECOMMEND_STATUS.SHOP_ID
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_RECOMMEND_STATUS.STATUS
     *
     * @return the value of TOP_RECOMMEND_STATUS.STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_RECOMMEND_STATUS.STATUS
     *
     * @param status the value for TOP_RECOMMEND_STATUS.STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_RECOMMEND_STATUS.LAST_MODIFIED_TIME
     *
     * @return the value of TOP_RECOMMEND_STATUS.LAST_MODIFIED_TIME
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_RECOMMEND_STATUS.LAST_MODIFIED_TIME
     *
     * @param lastModifiedTime the value for TOP_RECOMMEND_STATUS.LAST_MODIFIED_TIME
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_RECOMMEND_STATUS.LAST_MODIFIED_BY
     *
     * @return the value of TOP_RECOMMEND_STATUS.LAST_MODIFIED_BY
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_RECOMMEND_STATUS.LAST_MODIFIED_BY
     *
     * @param lastModifiedBy the value for TOP_RECOMMEND_STATUS.LAST_MODIFIED_BY
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_RECOMMEND_STATUS.UPDATED_TIME
     *
     * @return the value of TOP_RECOMMEND_STATUS.UPDATED_TIME
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_RECOMMEND_STATUS.UPDATED_TIME
     *
     * @param updatedTime the value for TOP_RECOMMEND_STATUS.UPDATED_TIME
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_RECOMMEND_STATUS.UPDATED_USER_ID
     *
     * @return the value of TOP_RECOMMEND_STATUS.UPDATED_USER_ID
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public Long getUpdatedUserId() {
        return updatedUserId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_RECOMMEND_STATUS.UPDATED_USER_ID
     *
     * @param updatedUserId the value for TOP_RECOMMEND_STATUS.UPDATED_USER_ID
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public void setUpdatedUserId(Long updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_RECOMMEND_STATUS.IS_RECOMMENDED
     *
     * @return the value of TOP_RECOMMEND_STATUS.IS_RECOMMENDED
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public String getIsRecommended() {
        return isRecommended;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_RECOMMEND_STATUS.IS_RECOMMENDED
     *
     * @param isRecommended the value for TOP_RECOMMEND_STATUS.IS_RECOMMENDED
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public void setIsRecommended(String isRecommended) {
        this.isRecommended = isRecommended == null ? null : isRecommended.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof RecommendStatus)) {
            return false;
        }
        RecommendStatus other = (RecommendStatus) that;
        return this.getShopId() == null ? other == null : this.getShopId().equals(other.getShopId())
            && this.getStatus() == null ? other == null : this.getStatus().equals(other.getStatus())
            && this.getLastModifiedTime() == null ? other == null : this.getLastModifiedTime().equals(other.getLastModifiedTime())
            && this.getLastModifiedBy() == null ? other == null : this.getLastModifiedBy().equals(other.getLastModifiedBy())
            && this.getUpdatedTime() == null ? other == null : this.getUpdatedTime().equals(other.getUpdatedTime())
            && this.getUpdatedUserId() == null ? other == null : this.getUpdatedUserId().equals(other.getUpdatedUserId())
            && this.getIsRecommended() == null ? other == null : this.getIsRecommended().equals(other.getIsRecommended());
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    @Override
    public int hashCode() {
        int hash = 23;
        if (getShopId() != null) {
            hash *= getShopId().hashCode();
        }
        if (getStatus() != null) {
            hash *= getStatus().hashCode();
        }
        if (getLastModifiedTime() != null) {
            hash *= getLastModifiedTime().hashCode();
        }
        if (getLastModifiedBy() != null) {
            hash *= getLastModifiedBy().hashCode();
        }
        if (getUpdatedTime() != null) {
            hash *= getUpdatedTime().hashCode();
        }
        if (getUpdatedUserId() != null) {
            hash *= getUpdatedUserId().hashCode();
        }
        if (getIsRecommended() != null) {
            hash *= getIsRecommended().hashCode();
        }
        return hash;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public RecommendStatus() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public RecommendStatus(Long shopId) {
        this.shopId = shopId;
    }
}