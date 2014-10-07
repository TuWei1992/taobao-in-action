package com.dream.recommend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendStatusCriteria {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public RecommendStatusCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    protected RecommendStatusCriteria(RecommendStatusCriteria example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table TOP_RECOMMEND_STATUS
     *
     *  Sun Oct 05 20:41:58 CST 2014
     */
    public static class Criteria {
        protected List<String> criteriaWithoutValue;

        protected List<Map<String, Object>> criteriaWithSingleValue;

        protected List<Map<String, Object>> criteriaWithListValue;

        protected List<Map<String, Object>> criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList<String>();
            criteriaWithSingleValue = new ArrayList<Map<String, Object>>();
            criteriaWithListValue = new ArrayList<Map<String, Object>>();
            criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List<String> getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List<Map<String, Object>> getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List<Map<String, Object>> getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List<Map<String, Object>> getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List<? extends Object> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List<Object> list = new ArrayList<Object>();
            list.add(value1);
            list.add(value2);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andShopIdIsNull() {
            addCriterion("SHOP_ID is null");
            return this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("SHOP_ID is not null");
            return this;
        }

        public Criteria andShopIdEqualTo(Long value) {
            addCriterion("SHOP_ID =", value, "shopId");
            return this;
        }

        public Criteria andShopIdNotEqualTo(Long value) {
            addCriterion("SHOP_ID <>", value, "shopId");
            return this;
        }

        public Criteria andShopIdGreaterThan(Long value) {
            addCriterion("SHOP_ID >", value, "shopId");
            return this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SHOP_ID >=", value, "shopId");
            return this;
        }

        public Criteria andShopIdLessThan(Long value) {
            addCriterion("SHOP_ID <", value, "shopId");
            return this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Long value) {
            addCriterion("SHOP_ID <=", value, "shopId");
            return this;
        }

        public Criteria andShopIdIn(List<Long> values) {
            addCriterion("SHOP_ID in", values, "shopId");
            return this;
        }

        public Criteria andShopIdNotIn(List<Long> values) {
            addCriterion("SHOP_ID not in", values, "shopId");
            return this;
        }

        public Criteria andShopIdBetween(Long value1, Long value2) {
            addCriterion("SHOP_ID between", value1, value2, "shopId");
            return this;
        }

        public Criteria andShopIdNotBetween(Long value1, Long value2) {
            addCriterion("SHOP_ID not between", value1, value2, "shopId");
            return this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return this;
        }

        public Criteria andLastModifiedTimeIsNull() {
            addCriterion("LAST_MODIFIED_TIME is null");
            return this;
        }

        public Criteria andLastModifiedTimeIsNotNull() {
            addCriterion("LAST_MODIFIED_TIME is not null");
            return this;
        }

        public Criteria andLastModifiedTimeEqualTo(Date value) {
            addCriterion("LAST_MODIFIED_TIME =", value, "lastModifiedTime");
            return this;
        }

        public Criteria andLastModifiedTimeNotEqualTo(Date value) {
            addCriterion("LAST_MODIFIED_TIME <>", value, "lastModifiedTime");
            return this;
        }

        public Criteria andLastModifiedTimeGreaterThan(Date value) {
            addCriterion("LAST_MODIFIED_TIME >", value, "lastModifiedTime");
            return this;
        }

        public Criteria andLastModifiedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LAST_MODIFIED_TIME >=", value, "lastModifiedTime");
            return this;
        }

        public Criteria andLastModifiedTimeLessThan(Date value) {
            addCriterion("LAST_MODIFIED_TIME <", value, "lastModifiedTime");
            return this;
        }

        public Criteria andLastModifiedTimeLessThanOrEqualTo(Date value) {
            addCriterion("LAST_MODIFIED_TIME <=", value, "lastModifiedTime");
            return this;
        }

        public Criteria andLastModifiedTimeIn(List<Date> values) {
            addCriterion("LAST_MODIFIED_TIME in", values, "lastModifiedTime");
            return this;
        }

        public Criteria andLastModifiedTimeNotIn(List<Date> values) {
            addCriterion("LAST_MODIFIED_TIME not in", values, "lastModifiedTime");
            return this;
        }

        public Criteria andLastModifiedTimeBetween(Date value1, Date value2) {
            addCriterion("LAST_MODIFIED_TIME between", value1, value2, "lastModifiedTime");
            return this;
        }

        public Criteria andLastModifiedTimeNotBetween(Date value1, Date value2) {
            addCriterion("LAST_MODIFIED_TIME not between", value1, value2, "lastModifiedTime");
            return this;
        }

        public Criteria andLastModifiedByIsNull() {
            addCriterion("LAST_MODIFIED_BY is null");
            return this;
        }

        public Criteria andLastModifiedByIsNotNull() {
            addCriterion("LAST_MODIFIED_BY is not null");
            return this;
        }

        public Criteria andLastModifiedByEqualTo(Long value) {
            addCriterion("LAST_MODIFIED_BY =", value, "lastModifiedBy");
            return this;
        }

        public Criteria andLastModifiedByNotEqualTo(Long value) {
            addCriterion("LAST_MODIFIED_BY <>", value, "lastModifiedBy");
            return this;
        }

        public Criteria andLastModifiedByGreaterThan(Long value) {
            addCriterion("LAST_MODIFIED_BY >", value, "lastModifiedBy");
            return this;
        }

        public Criteria andLastModifiedByGreaterThanOrEqualTo(Long value) {
            addCriterion("LAST_MODIFIED_BY >=", value, "lastModifiedBy");
            return this;
        }

        public Criteria andLastModifiedByLessThan(Long value) {
            addCriterion("LAST_MODIFIED_BY <", value, "lastModifiedBy");
            return this;
        }

        public Criteria andLastModifiedByLessThanOrEqualTo(Long value) {
            addCriterion("LAST_MODIFIED_BY <=", value, "lastModifiedBy");
            return this;
        }

        public Criteria andLastModifiedByIn(List<Long> values) {
            addCriterion("LAST_MODIFIED_BY in", values, "lastModifiedBy");
            return this;
        }

        public Criteria andLastModifiedByNotIn(List<Long> values) {
            addCriterion("LAST_MODIFIED_BY not in", values, "lastModifiedBy");
            return this;
        }

        public Criteria andLastModifiedByBetween(Long value1, Long value2) {
            addCriterion("LAST_MODIFIED_BY between", value1, value2, "lastModifiedBy");
            return this;
        }

        public Criteria andLastModifiedByNotBetween(Long value1, Long value2) {
            addCriterion("LAST_MODIFIED_BY not between", value1, value2, "lastModifiedBy");
            return this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("UPDATED_TIME is null");
            return this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("UPDATED_TIME is not null");
            return this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("UPDATED_TIME =", value, "updatedTime");
            return this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("UPDATED_TIME <>", value, "updatedTime");
            return this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("UPDATED_TIME >", value, "updatedTime");
            return this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATED_TIME >=", value, "updatedTime");
            return this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("UPDATED_TIME <", value, "updatedTime");
            return this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATED_TIME <=", value, "updatedTime");
            return this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("UPDATED_TIME in", values, "updatedTime");
            return this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("UPDATED_TIME not in", values, "updatedTime");
            return this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATED_TIME between", value1, value2, "updatedTime");
            return this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATED_TIME not between", value1, value2, "updatedTime");
            return this;
        }

        public Criteria andUpdatedUserIdIsNull() {
            addCriterion("UPDATED_USER_ID is null");
            return this;
        }

        public Criteria andUpdatedUserIdIsNotNull() {
            addCriterion("UPDATED_USER_ID is not null");
            return this;
        }

        public Criteria andUpdatedUserIdEqualTo(Long value) {
            addCriterion("UPDATED_USER_ID =", value, "updatedUserId");
            return this;
        }

        public Criteria andUpdatedUserIdNotEqualTo(Long value) {
            addCriterion("UPDATED_USER_ID <>", value, "updatedUserId");
            return this;
        }

        public Criteria andUpdatedUserIdGreaterThan(Long value) {
            addCriterion("UPDATED_USER_ID >", value, "updatedUserId");
            return this;
        }

        public Criteria andUpdatedUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("UPDATED_USER_ID >=", value, "updatedUserId");
            return this;
        }

        public Criteria andUpdatedUserIdLessThan(Long value) {
            addCriterion("UPDATED_USER_ID <", value, "updatedUserId");
            return this;
        }

        public Criteria andUpdatedUserIdLessThanOrEqualTo(Long value) {
            addCriterion("UPDATED_USER_ID <=", value, "updatedUserId");
            return this;
        }

        public Criteria andUpdatedUserIdIn(List<Long> values) {
            addCriterion("UPDATED_USER_ID in", values, "updatedUserId");
            return this;
        }

        public Criteria andUpdatedUserIdNotIn(List<Long> values) {
            addCriterion("UPDATED_USER_ID not in", values, "updatedUserId");
            return this;
        }

        public Criteria andUpdatedUserIdBetween(Long value1, Long value2) {
            addCriterion("UPDATED_USER_ID between", value1, value2, "updatedUserId");
            return this;
        }

        public Criteria andUpdatedUserIdNotBetween(Long value1, Long value2) {
            addCriterion("UPDATED_USER_ID not between", value1, value2, "updatedUserId");
            return this;
        }

        public Criteria andIsRecommendedIsNull() {
            addCriterion("IS_RECOMMENDED is null");
            return this;
        }

        public Criteria andIsRecommendedIsNotNull() {
            addCriterion("IS_RECOMMENDED is not null");
            return this;
        }

        public Criteria andIsRecommendedEqualTo(String value) {
            addCriterion("IS_RECOMMENDED =", value, "isRecommended");
            return this;
        }

        public Criteria andIsRecommendedNotEqualTo(String value) {
            addCriterion("IS_RECOMMENDED <>", value, "isRecommended");
            return this;
        }

        public Criteria andIsRecommendedGreaterThan(String value) {
            addCriterion("IS_RECOMMENDED >", value, "isRecommended");
            return this;
        }

        public Criteria andIsRecommendedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_RECOMMENDED >=", value, "isRecommended");
            return this;
        }

        public Criteria andIsRecommendedLessThan(String value) {
            addCriterion("IS_RECOMMENDED <", value, "isRecommended");
            return this;
        }

        public Criteria andIsRecommendedLessThanOrEqualTo(String value) {
            addCriterion("IS_RECOMMENDED <=", value, "isRecommended");
            return this;
        }

        public Criteria andIsRecommendedLike(String value) {
            addCriterion("IS_RECOMMENDED like", value, "isRecommended");
            return this;
        }

        public Criteria andIsRecommendedNotLike(String value) {
            addCriterion("IS_RECOMMENDED not like", value, "isRecommended");
            return this;
        }

        public Criteria andIsRecommendedIn(List<String> values) {
            addCriterion("IS_RECOMMENDED in", values, "isRecommended");
            return this;
        }

        public Criteria andIsRecommendedNotIn(List<String> values) {
            addCriterion("IS_RECOMMENDED not in", values, "isRecommended");
            return this;
        }

        public Criteria andIsRecommendedBetween(String value1, String value2) {
            addCriterion("IS_RECOMMENDED between", value1, value2, "isRecommended");
            return this;
        }

        public Criteria andIsRecommendedNotBetween(String value1, String value2) {
            addCriterion("IS_RECOMMENDED not between", value1, value2, "isRecommended");
            return this;
        }
    }
}