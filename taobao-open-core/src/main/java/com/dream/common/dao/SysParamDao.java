/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dream.common.model.SysParam;
import com.dream.common.model.SysParamCriteria;
import com.dream.common.vo.query.SysParamQuery;
import com.dream.rapid.base.BaseIbatisDao;
import com.dream.rapid.page.Page;
/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


@Repository
public class SysParamDao extends BaseIbatisDao<SysParam,java.lang.String,SysParamCriteria>{
	
	public String getIbatisSqlMapNamespace() {
		return "TOP_SYS_PARAM";
	}
	
	public Page findPage(SysParamQuery query) {
		return pageQuery(getIbatisSqlMapNamespace()+".findPage",query);
	}
	
	public SysParam getByKey(String key){
		SysParamCriteria criteria = new SysParamCriteria();
		criteria.createCriteria().andKeyEqualTo(key);
		List<SysParam> result = this.selectByCriteria(criteria);
		if(result!=null){
			return result.get(0);
		}
		return null;
	}

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table TOP_SYS_PARAM
     *
     *  Mon Sep 29 17:41:14 CST 2014
     */
    private static class UpdateByCriteriaParms extends SysParamCriteria {
        private Object record;

        public UpdateByCriteriaParms(Object record, SysParamCriteria criteria) {
            super(criteria);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

	@Override
	protected void prepareObjectForSaveOrUpdate(SysParam source,SysParam dest) {
	}


	@Override
	protected SysParamCriteria getCriteriaParam(SysParam record,
			SysParamCriteria criteria) {
		return new UpdateByCriteriaParms(record,criteria);
	}
	

}
