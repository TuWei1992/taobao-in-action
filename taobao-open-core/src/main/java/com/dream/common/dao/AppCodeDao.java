package com.dream.common.dao;

import org.springframework.stereotype.Repository;

import com.dream.common.model.AppCode;
import com.dream.common.model.AppCodeCriteria;
import com.dream.common.model.AppCodeKey;
import com.dream.rapid.base.BaseIbatisDao;

@Repository
public class AppCodeDao extends BaseIbatisDao<AppCode, AppCodeKey, AppCodeCriteria> {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table TOP_APP_CODE
     *
     *  Tue Oct 14 22:07:26 CST 2014
     */
    public AppCodeDao() {
        super();
    }

    protected void prepareObjectForSaveOrUpdate(AppCode record, AppCode first) {
        ;
    }

    protected String getIbatisSqlMapNamespace() {
        return "TOP_APP_CODE";
    }

    protected AppCodeCriteria getCriteriaParam(AppCode record, AppCodeCriteria criteria) {
        return new UpdateByCriteriaParms(record,criteria);
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table TOP_APP_CODE
     *
     *  Tue Oct 14 22:07:26 CST 2014
     */
    private static class UpdateByCriteriaParms extends AppCodeCriteria {
        private Object record;

        public UpdateByCriteriaParms(Object record, AppCodeCriteria criteria) {
            super(criteria);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}