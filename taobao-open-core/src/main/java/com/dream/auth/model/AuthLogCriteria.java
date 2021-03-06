package com.dream.auth.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthLogCriteria {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table TOP_AUTH_LOG
     *
     *  Mon Sep 29 15:27:39 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table TOP_AUTH_LOG
     *
     *  Mon Sep 29 15:27:39 CST 2014
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_AUTH_LOG
     *
     *  Mon Sep 29 15:27:39 CST 2014
     */
    public AuthLogCriteria() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_AUTH_LOG
     *
     *  Mon Sep 29 15:27:39 CST 2014
     */
    protected AuthLogCriteria(AuthLogCriteria example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_AUTH_LOG
     *
     *  Mon Sep 29 15:27:39 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_AUTH_LOG
     *
     *  Mon Sep 29 15:27:39 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_AUTH_LOG
     *
     *  Mon Sep 29 15:27:39 CST 2014
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_AUTH_LOG
     *
     *  Mon Sep 29 15:27:39 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_AUTH_LOG
     *
     *  Mon Sep 29 15:27:39 CST 2014
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
     * This method corresponds to the database table TOP_AUTH_LOG
     *
     *  Mon Sep 29 15:27:39 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_AUTH_LOG
     *
     *  Mon Sep 29 15:27:39 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table TOP_AUTH_LOG
     *
     *  Mon Sep 29 15:27:39 CST 2014
     */
    public static class Criteria {
        protected List criteriaWithoutValue;

        protected List criteriaWithSingleValue;

        protected List criteriaWithListValue;

        protected List criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList();
            criteriaWithSingleValue = new ArrayList();
            criteriaWithListValue = new ArrayList();
            criteriaWithBetweenValue = new ArrayList();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List getCriteriaWithBetweenValue() {
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
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List list = new ArrayList();
            list.add(value1);
            list.add(value2);
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andLogIdIsNull() {
            addCriterion("LOG_ID is null");
            return this;
        }

        public Criteria andLogIdIsNotNull() {
            addCriterion("LOG_ID is not null");
            return this;
        }

        public Criteria andLogIdEqualTo(String value) {
            addCriterion("LOG_ID =", value, "logId");
            return this;
        }

        public Criteria andLogIdNotEqualTo(String value) {
            addCriterion("LOG_ID <>", value, "logId");
            return this;
        }

        public Criteria andLogIdGreaterThan(String value) {
            addCriterion("LOG_ID >", value, "logId");
            return this;
        }

        public Criteria andLogIdGreaterThanOrEqualTo(String value) {
            addCriterion("LOG_ID >=", value, "logId");
            return this;
        }

        public Criteria andLogIdLessThan(String value) {
            addCriterion("LOG_ID <", value, "logId");
            return this;
        }

        public Criteria andLogIdLessThanOrEqualTo(String value) {
            addCriterion("LOG_ID <=", value, "logId");
            return this;
        }

        public Criteria andLogIdLike(String value) {
            addCriterion("LOG_ID like", value, "logId");
            return this;
        }

        public Criteria andLogIdNotLike(String value) {
            addCriterion("LOG_ID not like", value, "logId");
            return this;
        }

        public Criteria andLogIdIn(List values) {
            addCriterion("LOG_ID in", values, "logId");
            return this;
        }

        public Criteria andLogIdNotIn(List values) {
            addCriterion("LOG_ID not in", values, "logId");
            return this;
        }

        public Criteria andLogIdBetween(String value1, String value2) {
            addCriterion("LOG_ID between", value1, value2, "logId");
            return this;
        }

        public Criteria andLogIdNotBetween(String value1, String value2) {
            addCriterion("LOG_ID not between", value1, value2, "logId");
            return this;
        }

        public Criteria andSessionIdIsNull() {
            addCriterion("SESSION_ID is null");
            return this;
        }

        public Criteria andSessionIdIsNotNull() {
            addCriterion("SESSION_ID is not null");
            return this;
        }

        public Criteria andSessionIdEqualTo(String value) {
            addCriterion("SESSION_ID =", value, "sessionId");
            return this;
        }

        public Criteria andSessionIdNotEqualTo(String value) {
            addCriterion("SESSION_ID <>", value, "sessionId");
            return this;
        }

        public Criteria andSessionIdGreaterThan(String value) {
            addCriterion("SESSION_ID >", value, "sessionId");
            return this;
        }

        public Criteria andSessionIdGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_ID >=", value, "sessionId");
            return this;
        }

        public Criteria andSessionIdLessThan(String value) {
            addCriterion("SESSION_ID <", value, "sessionId");
            return this;
        }

        public Criteria andSessionIdLessThanOrEqualTo(String value) {
            addCriterion("SESSION_ID <=", value, "sessionId");
            return this;
        }

        public Criteria andSessionIdLike(String value) {
            addCriterion("SESSION_ID like", value, "sessionId");
            return this;
        }

        public Criteria andSessionIdNotLike(String value) {
            addCriterion("SESSION_ID not like", value, "sessionId");
            return this;
        }

        public Criteria andSessionIdIn(List values) {
            addCriterion("SESSION_ID in", values, "sessionId");
            return this;
        }

        public Criteria andSessionIdNotIn(List values) {
            addCriterion("SESSION_ID not in", values, "sessionId");
            return this;
        }

        public Criteria andSessionIdBetween(String value1, String value2) {
            addCriterion("SESSION_ID between", value1, value2, "sessionId");
            return this;
        }

        public Criteria andSessionIdNotBetween(String value1, String value2) {
            addCriterion("SESSION_ID not between", value1, value2, "sessionId");
            return this;
        }

        public Criteria andClientIpIsNull() {
            addCriterion("CLIENT_IP is null");
            return this;
        }

        public Criteria andClientIpIsNotNull() {
            addCriterion("CLIENT_IP is not null");
            return this;
        }

        public Criteria andClientIpEqualTo(String value) {
            addCriterion("CLIENT_IP =", value, "clientIp");
            return this;
        }

        public Criteria andClientIpNotEqualTo(String value) {
            addCriterion("CLIENT_IP <>", value, "clientIp");
            return this;
        }

        public Criteria andClientIpGreaterThan(String value) {
            addCriterion("CLIENT_IP >", value, "clientIp");
            return this;
        }

        public Criteria andClientIpGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENT_IP >=", value, "clientIp");
            return this;
        }

        public Criteria andClientIpLessThan(String value) {
            addCriterion("CLIENT_IP <", value, "clientIp");
            return this;
        }

        public Criteria andClientIpLessThanOrEqualTo(String value) {
            addCriterion("CLIENT_IP <=", value, "clientIp");
            return this;
        }

        public Criteria andClientIpLike(String value) {
            addCriterion("CLIENT_IP like", value, "clientIp");
            return this;
        }

        public Criteria andClientIpNotLike(String value) {
            addCriterion("CLIENT_IP not like", value, "clientIp");
            return this;
        }

        public Criteria andClientIpIn(List values) {
            addCriterion("CLIENT_IP in", values, "clientIp");
            return this;
        }

        public Criteria andClientIpNotIn(List values) {
            addCriterion("CLIENT_IP not in", values, "clientIp");
            return this;
        }

        public Criteria andClientIpBetween(String value1, String value2) {
            addCriterion("CLIENT_IP between", value1, value2, "clientIp");
            return this;
        }

        public Criteria andClientIpNotBetween(String value1, String value2) {
            addCriterion("CLIENT_IP not between", value1, value2, "clientIp");
            return this;
        }

        public Criteria andClientOsIsNull() {
            addCriterion("CLIENT_OS is null");
            return this;
        }

        public Criteria andClientOsIsNotNull() {
            addCriterion("CLIENT_OS is not null");
            return this;
        }

        public Criteria andClientOsEqualTo(String value) {
            addCriterion("CLIENT_OS =", value, "clientOs");
            return this;
        }

        public Criteria andClientOsNotEqualTo(String value) {
            addCriterion("CLIENT_OS <>", value, "clientOs");
            return this;
        }

        public Criteria andClientOsGreaterThan(String value) {
            addCriterion("CLIENT_OS >", value, "clientOs");
            return this;
        }

        public Criteria andClientOsGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENT_OS >=", value, "clientOs");
            return this;
        }

        public Criteria andClientOsLessThan(String value) {
            addCriterion("CLIENT_OS <", value, "clientOs");
            return this;
        }

        public Criteria andClientOsLessThanOrEqualTo(String value) {
            addCriterion("CLIENT_OS <=", value, "clientOs");
            return this;
        }

        public Criteria andClientOsLike(String value) {
            addCriterion("CLIENT_OS like", value, "clientOs");
            return this;
        }

        public Criteria andClientOsNotLike(String value) {
            addCriterion("CLIENT_OS not like", value, "clientOs");
            return this;
        }

        public Criteria andClientOsIn(List values) {
            addCriterion("CLIENT_OS in", values, "clientOs");
            return this;
        }

        public Criteria andClientOsNotIn(List values) {
            addCriterion("CLIENT_OS not in", values, "clientOs");
            return this;
        }

        public Criteria andClientOsBetween(String value1, String value2) {
            addCriterion("CLIENT_OS between", value1, value2, "clientOs");
            return this;
        }

        public Criteria andClientOsNotBetween(String value1, String value2) {
            addCriterion("CLIENT_OS not between", value1, value2, "clientOs");
            return this;
        }

        public Criteria andClientBrowserIsNull() {
            addCriterion("CLIENT_BROWSER is null");
            return this;
        }

        public Criteria andClientBrowserIsNotNull() {
            addCriterion("CLIENT_BROWSER is not null");
            return this;
        }

        public Criteria andClientBrowserEqualTo(String value) {
            addCriterion("CLIENT_BROWSER =", value, "clientBrowser");
            return this;
        }

        public Criteria andClientBrowserNotEqualTo(String value) {
            addCriterion("CLIENT_BROWSER <>", value, "clientBrowser");
            return this;
        }

        public Criteria andClientBrowserGreaterThan(String value) {
            addCriterion("CLIENT_BROWSER >", value, "clientBrowser");
            return this;
        }

        public Criteria andClientBrowserGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENT_BROWSER >=", value, "clientBrowser");
            return this;
        }

        public Criteria andClientBrowserLessThan(String value) {
            addCriterion("CLIENT_BROWSER <", value, "clientBrowser");
            return this;
        }

        public Criteria andClientBrowserLessThanOrEqualTo(String value) {
            addCriterion("CLIENT_BROWSER <=", value, "clientBrowser");
            return this;
        }

        public Criteria andClientBrowserLike(String value) {
            addCriterion("CLIENT_BROWSER like", value, "clientBrowser");
            return this;
        }

        public Criteria andClientBrowserNotLike(String value) {
            addCriterion("CLIENT_BROWSER not like", value, "clientBrowser");
            return this;
        }

        public Criteria andClientBrowserIn(List values) {
            addCriterion("CLIENT_BROWSER in", values, "clientBrowser");
            return this;
        }

        public Criteria andClientBrowserNotIn(List values) {
            addCriterion("CLIENT_BROWSER not in", values, "clientBrowser");
            return this;
        }

        public Criteria andClientBrowserBetween(String value1, String value2) {
            addCriterion("CLIENT_BROWSER between", value1, value2, "clientBrowser");
            return this;
        }

        public Criteria andClientBrowserNotBetween(String value1, String value2) {
            addCriterion("CLIENT_BROWSER not between", value1, value2, "clientBrowser");
            return this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return this;
        }

        public Criteria andUserIdIn(List values) {
            addCriterion("USER_ID in", values, "userId");
            return this;
        }

        public Criteria andUserIdNotIn(List values) {
            addCriterion("USER_ID not in", values, "userId");
            return this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return this;
        }

        public Criteria andAuthTimeIsNull() {
            addCriterion("AUTH_TIME is null");
            return this;
        }

        public Criteria andAuthTimeIsNotNull() {
            addCriterion("AUTH_TIME is not null");
            return this;
        }

        public Criteria andAuthTimeEqualTo(Date value) {
            addCriterion("AUTH_TIME =", value, "authTime");
            return this;
        }

        public Criteria andAuthTimeNotEqualTo(Date value) {
            addCriterion("AUTH_TIME <>", value, "authTime");
            return this;
        }

        public Criteria andAuthTimeGreaterThan(Date value) {
            addCriterion("AUTH_TIME >", value, "authTime");
            return this;
        }

        public Criteria andAuthTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("AUTH_TIME >=", value, "authTime");
            return this;
        }

        public Criteria andAuthTimeLessThan(Date value) {
            addCriterion("AUTH_TIME <", value, "authTime");
            return this;
        }

        public Criteria andAuthTimeLessThanOrEqualTo(Date value) {
            addCriterion("AUTH_TIME <=", value, "authTime");
            return this;
        }

        public Criteria andAuthTimeIn(List values) {
            addCriterion("AUTH_TIME in", values, "authTime");
            return this;
        }

        public Criteria andAuthTimeNotIn(List values) {
            addCriterion("AUTH_TIME not in", values, "authTime");
            return this;
        }

        public Criteria andAuthTimeBetween(Date value1, Date value2) {
            addCriterion("AUTH_TIME between", value1, value2, "authTime");
            return this;
        }

        public Criteria andAuthTimeNotBetween(Date value1, Date value2) {
            addCriterion("AUTH_TIME not between", value1, value2, "authTime");
            return this;
        }
    }
}