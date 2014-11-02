/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.rapid.base.BaseService;
import com.dream.rapid.base.EntityDao;
import com.dream.rapid.page.Page;
import com.dream.shop.dao.ShopCategoryDao;
import com.dream.shop.model.ShopCategory;
import com.dream.shop.model.ShopCategoryCriteria;
import com.dream.shop.vo.query.ShopCategoryQuery;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class ShopCategoryService extends BaseService<ShopCategory,ShopCategory,ShopCategoryCriteria>{

	//================================********Generated code start here********================================
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写
	 * @param dao
	 */
	public void setShopCategoryDao(ShopCategoryDao dao) {
		this.shopCategoryDao = dao;
	}
	
	@Override
	protected EntityDao getEntityDao() {
		return this.shopCategoryDao;
	}
	
	/**
	 * 翻页查询
	 * @param query
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page findPage(ShopCategoryQuery query) {
//		return shopCategoryDao.findPage(query);
		return null;
	}
	

	//================================********Generated code end here********================================
	
	//================================********Customized code start here********==============================
	
	
	
	//================================********Customized code end here********================================
}
