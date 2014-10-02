/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.menu.dao;

import org.springframework.stereotype.Repository;

import com.dream.menu.model.Menu;
import com.dream.menu.model.MenuCriteria;
import com.dream.menu.vo.query.MenuQuery;
import com.dream.rapid.base.BaseIbatisDao;
import com.dream.rapid.page.Page;
/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


@Repository
public class MenuDao extends BaseIbatisDao<Menu,java.lang.String,MenuCriteria>{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "ITEM_MENU";
	}
	
	
	public Page findPage(MenuQuery query) {
		return pageQuery("Menu.findPage",query);
	}


	@Override
	protected void prepareObjectForSaveOrUpdate(Menu source,Menu dest) {
		// TODO Auto-generated method stub
		
	}
	
	


    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table TOP_MENU
     *
     *  Mon Sep 29 17:55:43 CST 2014
     */
    private static class UpdateByCriteriaParms extends MenuCriteria {
        private Object record;

        public UpdateByCriteriaParms(Object record, MenuCriteria criteria) {
            super(criteria);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }




	@Override
	protected MenuCriteria getCriteriaParam(Menu record, MenuCriteria criteria) {
		return new UpdateByCriteriaParms(record,criteria)  ;
	}
	

}
