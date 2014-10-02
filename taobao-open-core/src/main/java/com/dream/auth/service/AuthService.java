/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.auth.dao.AuthDao;
import com.dream.auth.model.Auth;
import com.dream.auth.model.AuthCriteria;
import com.dream.auth.vo.query.AuthQuery;
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
public class AuthService extends BaseService<Auth,Auth,AuthCriteria>{

	//================================********Generated code start here********================================
	
	@Autowired
	private AuthDao authDao;
	
	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写
	 * @param dao
	 */
	public void setAuthDao(AuthDao dao) {
		this.authDao = dao;
	}
	
	@Override
	protected EntityDao getEntityDao() {
		return this.authDao;
	}
	
	/**
	 * 翻页查询
	 * @param query
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page findPage(AuthQuery query) {
		return authDao.findPage(query);
	}
	

	//================================********Generated code end here********================================
	
	//================================********Customized code start here********==============================
	@Transactional(readOnly=true)
	public Auth getByTaobaoUserId(String taobaoUserId){
		AuthCriteria criteria = new AuthCriteria();
		criteria.createCriteria().andTaobaoUserIdEqualTo(taobaoUserId);
		List<Auth> result = this.authDao.selectByCriteria(criteria);
		if(result == null || result.size()!=1){
			return null;
		}else{
			return result.get(0);
		}
	}
	
	//================================********Customized code end here********================================
}
