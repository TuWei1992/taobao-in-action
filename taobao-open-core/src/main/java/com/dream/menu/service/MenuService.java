/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.menu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.menu.dao.MenuDao;
import com.dream.menu.model.Menu;
import com.dream.menu.model.MenuCriteria;
import com.dream.menu.vo.query.MenuQuery;
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
public class MenuService extends BaseService<Menu,java.lang.String,MenuCriteria>{
	
	@Autowired
	private MenuDao menuDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setMenuDao(MenuDao dao) {
		this.menuDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.menuDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(MenuQuery query) {
		return menuDao.findPage(query);
	}
	
}
