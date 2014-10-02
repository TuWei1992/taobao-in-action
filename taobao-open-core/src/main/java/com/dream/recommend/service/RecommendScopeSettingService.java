/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.recommend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.rapid.base.BaseService;
import com.dream.rapid.base.EntityDao;
import com.dream.rapid.page.Page;
import com.dream.recommend.dao.RecommendScopeSettingDao;
import com.dream.recommend.model.RecommendScopeSetting;
import com.dream.recommend.model.RecommendScopeSettingCriteria;
import com.dream.recommend.vo.query.RecommendScopeSettingQuery;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class RecommendScopeSettingService extends BaseService<RecommendScopeSetting,RecommendScopeSetting,RecommendScopeSettingCriteria>{

	//================================********Generated code start here********================================
	
	@Autowired
	private RecommendScopeSettingDao recommendScopeSettingDao;
	
	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写
	 * @param dao
	 */
	public void setRecommendScopeSettingDao(RecommendScopeSettingDao dao) {
		this.recommendScopeSettingDao = dao;
	}
	
	@Override
	protected EntityDao getEntityDao() {
		return this.recommendScopeSettingDao;
	}
	
	/**
	 * 翻页查询
	 * @param query
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page<RecommendScopeSetting> findPage(RecommendScopeSettingQuery query) {
		return recommendScopeSettingDao.findPage(query);
	}

	//================================********Generated code end here********================================
	
	//================================********Customized code start here********==============================
	
	
	
	//================================********Customized code end here********================================
	
}
