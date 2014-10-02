/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.dream.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.item.dao.ItemDao;
import com.dream.item.model.Item;
import com.dream.item.model.ItemCriteria;
import com.dream.item.vo.query.ItemQuery;
import com.dream.rapid.base.BaseService;
import com.dream.rapid.base.EntityDao;
import com.dream.rapid.page.Page;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class ItemService extends BaseService<Item,Item,ItemCriteria>{
	@Autowired
	private ItemDao topItemDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setTopItemDao(ItemDao dao) {
		this.topItemDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.topItemDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(ItemQuery query) {
		return topItemDao.findPage(query);
	}
	

	
}
