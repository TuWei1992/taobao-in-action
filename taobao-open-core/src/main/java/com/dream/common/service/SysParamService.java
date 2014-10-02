/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.common.dao.SysParamDao;
import com.dream.common.model.SysParam;
import com.dream.common.model.SysParamCriteria;
import com.dream.common.vo.query.SysParamQuery;
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
public class SysParamService extends BaseService<SysParam,SysParam,SysParamCriteria>{
	@Autowired
	private SysParamDao sysParamDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setSysParamDao(SysParamDao dao) {
		this.sysParamDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.sysParamDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(SysParamQuery query) {
		return sysParamDao.findPage(query);
	}
	
	/**
	 * 根据Key获取参数值
	 * @param key
	 * @return
	 */
	@Transactional(readOnly=true)
	public SysParam getByKey(String key) {
		if(key == null){
			throw new IllegalArgumentException("Can not get sys param with null.");
		}
		SysParam param = sysParamDao.getByKey(key);
		if(param == null){
			throw new IllegalArgumentException("***Can not get sys param with key:"+key); 
		}
		return param;
	}
	
}
