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
import com.dream.recommend.dao.RecommendModeSettingDao;
import com.dream.recommend.model.RecommendModeSetting;
import com.dream.recommend.model.RecommendModeSettingCriteria;
import com.dream.recommend.vo.query.RecommendModeSettingQuery;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class RecommendModeSettingService extends BaseService<RecommendModeSetting,RecommendModeSetting,RecommendModeSettingCriteria>{

	//================================********Generated code start here********================================
	
	@Autowired
	private RecommendModeSettingDao recommendModeSettingDao;
	
	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写
	 * @param dao
	 */
	public void setRecommendModeSettingDao(RecommendModeSettingDao dao) {
		this.recommendModeSettingDao = dao;
	}
	
	@Override
	protected EntityDao getEntityDao() {
		return this.recommendModeSettingDao;
	}
	
	/**
	 * 翻页查询
	 * @param query
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page findPage(RecommendModeSettingQuery query) {
		return recommendModeSettingDao.findPage(query);
	}
	

	//================================********Generated code end here********================================
	
	//================================********Customized code start here********==============================
	
	
	
	//================================********Customized code end here********================================
}
