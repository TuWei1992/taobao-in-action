/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.auth.dao;

import org.springframework.stereotype.Repository;

import com.dream.auth.model.Auth;
import com.dream.auth.model.AuthCriteria;
import com.dream.auth.vo.query.AuthQuery;
import com.dream.rapid.base.BaseIbatisDao;
import com.dream.rapid.page.Page;
/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


@Repository
public class AuthDao extends BaseIbatisDao<Auth,java.lang.String,AuthCriteria>{

	//================================********Generated code start here********================================
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "TOP_AUTH";
	}
	
	
	@Override
	protected void prepareObjectForSaveOrUpdate(Auth source,Auth dest) {
		dest.setUserId(source.getUserId());
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	public Page findPage(AuthQuery query) {
		return pageQuery(getIbatisSqlMapNamespace()+".findPage",query);
	}

	    /**
	     * This class was generated by Apache iBATIS ibator.
	     * This class corresponds to the database table TOP_AUTH
	     *
	     *  Mon Sep 29 17:29:04 CST 2014
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

	    @Override
		protected AuthCriteria getCriteriaParam(Auth record,
				AuthCriteria criteria) {
			return new UpdateByCriteriaParms(record,criteria);
		}
	

	//================================********Generated code end here********================================
	
	//================================********Customized code start here********==============================
	
	
	//================================********Customized code end here********================================
	
}