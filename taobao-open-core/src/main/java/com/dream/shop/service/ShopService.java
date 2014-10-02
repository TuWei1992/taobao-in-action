/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.rapid.base.BaseService;
import com.dream.rapid.base.EntityDao;
import com.dream.rapid.page.Page;
import com.dream.shop.dao.ShopDao;
import com.dream.shop.model.Shop;
import com.dream.shop.model.ShopCriteria;
import com.dream.shop.vo.query.ShopQuery;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class ShopService extends BaseService<Shop,Shop,ShopCriteria>{

	//================================********Generated code start here********================================
	
	@Autowired
	private ShopDao shopDao;
	
	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写
	 * @param dao
	 */
	public void setShopDao(ShopDao dao) {
		this.shopDao = dao;
	}
	
	@Override
	protected EntityDao getEntityDao() {
		return this.shopDao;
	}
	
	/**
	 * 翻页查询
	 * @param query
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page findPage(ShopQuery query) {
		return shopDao.findPage(query);
	}
	
	
	/**
	 * 按指定的Nick查询
	 * @param v
	 * @return
	 */
	@Transactional(readOnly=true)
	public Shop getByNick(java.lang.String v) {
		ShopCriteria criteria = new ShopCriteria();
		criteria.createCriteria().andNickEqualTo(v);
		List<Shop> result  =   this.shopDao.selectByCriteria(criteria);
		if(result == null || result.size()!=1){
			return null;
		}else{
			return result.get(0);
		}
	}	
	

	//================================********Generated code end here********================================
	
	//================================********Customized code start here********==============================
	
	
	
	//================================********Customized code end here********================================
}
