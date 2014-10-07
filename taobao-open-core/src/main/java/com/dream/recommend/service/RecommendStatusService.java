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
import com.dream.recommend.dao.RecommendStatusDao;
import com.dream.recommend.model.RecommendStatus;
import com.dream.recommend.model.RecommendStatusCriteria;
import com.dream.recommend.vo.query.RecommendStatusQuery;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class RecommendStatusService extends BaseService<RecommendStatus,RecommendStatus,RecommendStatusCriteria>{

	//================================********Generated code start here********================================
	
	@Autowired
	private RecommendStatusDao recommendStatusDao;
	
	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写
	 * @param dao
	 */
	public void setRecommendStatusDao(RecommendStatusDao dao) {
		this.recommendStatusDao = dao;
	}
	
	@Override
	protected EntityDao getEntityDao() {
		return this.recommendStatusDao;
	}
	
	/**
	 * 翻页查询
	 * @param query
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page findPage(RecommendStatusQuery query) {
		return null;
	}
	

	//================================********Generated code end here********================================
	
	//================================********Customized code start here********==============================
	
	
	
	//================================********Customized code end here********================================
}
