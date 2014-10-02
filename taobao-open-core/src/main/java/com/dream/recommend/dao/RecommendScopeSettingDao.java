/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.recommend.dao;

import org.springframework.stereotype.Repository;

import com.dream.rapid.base.BaseIbatisDao;
import com.dream.rapid.page.Page;
import com.dream.recommend.model.RecommendScopeSetting;
import com.dream.recommend.model.RecommendScopeSettingCriteria;
import com.dream.recommend.vo.query.RecommendScopeSettingQuery;
/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


@Repository
public class RecommendScopeSettingDao extends BaseIbatisDao<RecommendScopeSetting,java.lang.Long,RecommendScopeSettingCriteria>{

	//================================********Generated code start here********================================
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "TOP_RECOMMEND_SCOPE_SETTING";
	}
	
	
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	public Page<RecommendScopeSetting> findPage(RecommendScopeSettingQuery query) {
		return pageQuery("RecommendScopeSetting.findPage",query);
	}



	@Override
	protected void prepareObjectForSaveOrUpdate(RecommendScopeSetting source,RecommendScopeSetting record) {
		// TODO Auto-generated method stub
		
	}
	
	

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table TOP_RECOMMEND_SCOPE_SETTING
     *
     *  Mon Sep 29 17:58:40 CST 2014
     */
    private static class UpdateByCriteriaParms extends RecommendScopeSettingCriteria {
        private Object record;

        public UpdateByCriteriaParms(Object record, RecommendScopeSettingCriteria criteria) {
            super(criteria);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

	@Override
	protected RecommendScopeSettingCriteria getCriteriaParam(
			RecommendScopeSetting record, RecommendScopeSettingCriteria criteria) {
		return new UpdateByCriteriaParms(record,criteria);
	}

	//================================********Generated code end here********================================
	
	//================================********Customized code start here********==============================
	
	
	
	//================================********Customized code end here********================================

}
