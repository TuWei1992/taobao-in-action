package com.dream.auth.dao;

import com.dream.auth.model.Auth;
import com.dream.auth.model.AuthCriteria;
import com.dream.rapid.base.BaseIbatisDao;
import org.springframework.stereotype.Repository;

@Repository
public class AuthDao extends BaseIbatisDao<Auth, Auth, AuthCriteria> {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_AUTH
     *
     *  Sat Oct 04 22:12:31 CST 2014
     */
    public AuthDao() {
        super();
    }

    protected void prepareObjectForSaveOrUpdate(Auth record,Auth first) {
        ;
    }

    protected String getIbatisSqlMapNamespace() {
        return "TOP_AUTH";
    }

    protected AuthCriteria getCriteriaParam(Auth record, AuthCriteria criteria) {
        return new UpdateByCriteriaParms(record,criteria);
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table TOP_AUTH
     *
     *  Sat Oct 04 22:12:31 CST 2014
     */
    private static class UpdateByCriteriaParms extends AuthCriteria {
        private Object record;

        public UpdateByCriteriaParms(Object record, AuthCriteria criteria) {
            super(criteria);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}