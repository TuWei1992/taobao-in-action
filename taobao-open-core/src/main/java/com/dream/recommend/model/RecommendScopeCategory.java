package com.dream.recommend.model;

import java.io.Serializable;
import java.util.Date;

public class RecommendScopeCategory implements Serializable {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_RECOMMEND_SCOPE_CATEGORY.ID
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    private String id;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_RECOMMEND_SCOPE_CATEGORY.SHOP_ID
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    private Long shopId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_RECOMMEND_SCOPE_CATEGORY.CAT_ID
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    private String catId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_RECOMMEND_SCOPE_CATEGORY.CAT_NAME
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    private String catName;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_RECOMMEND_SCOPE_CATEGORY.LAST_MODIFIED_TIME
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    private Date lastModifiedTime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column TOP_RECOMMEND_SCOPE_CATEGORY.LAST_MODIFIED_BY
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    private Long lastModifiedBy;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table TOP_RECOMMEND_SCOPE_CATEGORY
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_RECOMMEND_SCOPE_CATEGORY.ID
     *
     * @return the value of TOP_RECOMMEND_SCOPE_CATEGORY.ID
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_RECOMMEND_SCOPE_CATEGORY.ID
     *
     * @param id the value for TOP_RECOMMEND_SCOPE_CATEGORY.ID
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_RECOMMEND_SCOPE_CATEGORY.SHOP_ID
     *
     * @return the value of TOP_RECOMMEND_SCOPE_CATEGORY.SHOP_ID
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_RECOMMEND_SCOPE_CATEGORY.SHOP_ID
     *
     * @param shopId the value for TOP_RECOMMEND_SCOPE_CATEGORY.SHOP_ID
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_RECOMMEND_SCOPE_CATEGORY.CAT_ID
     *
     * @return the value of TOP_RECOMMEND_SCOPE_CATEGORY.CAT_ID
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public String getCatId() {
        return catId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_RECOMMEND_SCOPE_CATEGORY.CAT_ID
     *
     * @param catId the value for TOP_RECOMMEND_SCOPE_CATEGORY.CAT_ID
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public void setCatId(String catId) {
        this.catId = catId == null ? null : catId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_RECOMMEND_SCOPE_CATEGORY.CAT_NAME
     *
     * @return the value of TOP_RECOMMEND_SCOPE_CATEGORY.CAT_NAME
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public String getCatName() {
        return catName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_RECOMMEND_SCOPE_CATEGORY.CAT_NAME
     *
     * @param catName the value for TOP_RECOMMEND_SCOPE_CATEGORY.CAT_NAME
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_RECOMMEND_SCOPE_CATEGORY.LAST_MODIFIED_TIME
     *
     * @return the value of TOP_RECOMMEND_SCOPE_CATEGORY.LAST_MODIFIED_TIME
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_RECOMMEND_SCOPE_CATEGORY.LAST_MODIFIED_TIME
     *
     * @param lastModifiedTime the value for TOP_RECOMMEND_SCOPE_CATEGORY.LAST_MODIFIED_TIME
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column TOP_RECOMMEND_SCOPE_CATEGORY.LAST_MODIFIED_BY
     *
     * @return the value of TOP_RECOMMEND_SCOPE_CATEGORY.LAST_MODIFIED_BY
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column TOP_RECOMMEND_SCOPE_CATEGORY.LAST_MODIFIED_BY
     *
     * @param lastModifiedBy the value for TOP_RECOMMEND_SCOPE_CATEGORY.LAST_MODIFIED_BY
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_SCOPE_CATEGORY
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof RecommendScopeCategory)) {
            return false;
        }
        RecommendScopeCategory other = (RecommendScopeCategory) that;
        return this.getId() == null ? other == null : this.getId().equals(other.getId())
            && this.getShopId() == null ? other == null : this.getShopId().equals(other.getShopId())
            && this.getCatId() == null ? other == null : this.getCatId().equals(other.getCatId())
            && this.getCatName() == null ? other == null : this.getCatName().equals(other.getCatName())
            && this.getLastModifiedTime() == null ? other == null : this.getLastModifiedTime().equals(other.getLastModifiedTime())
            && this.getLastModifiedBy() == null ? other == null : this.getLastModifiedBy().equals(other.getLastModifiedBy());
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_SCOPE_CATEGORY
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    @Override
    public int hashCode() {
        int hash = 23;
        if (getId() != null) {
            hash *= getId().hashCode();
        }
        if (getShopId() != null) {
            hash *= getShopId().hashCode();
        }
        if (getCatId() != null) {
            hash *= getCatId().hashCode();
        }
        if (getCatName() != null) {
            hash *= getCatName().hashCode();
        }
        if (getLastModifiedTime() != null) {
            hash *= getLastModifiedTime().hashCode();
        }
        if (getLastModifiedBy() != null) {
            hash *= getLastModifiedBy().hashCode();
        }
        return hash;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_SCOPE_CATEGORY
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public RecommendScopeCategory() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_SCOPE_CATEGORY
     *
     *  Sun Oct 05 21:06:00 CST 2014
     */
    public RecommendScopeCategory(String id) {
        this.id = id;
    }
}