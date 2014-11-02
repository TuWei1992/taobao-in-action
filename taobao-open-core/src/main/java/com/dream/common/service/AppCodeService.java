/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.dream.rapid.base.*;
import com.dream.rapid.util.*;
import com.dream.rapid.page.*;

import com.dream.common.model.*;
import com.dream.common.dao.*;
import com.dream.common.service.*;
import com.dream.common.vo.query.*;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class AppCodeService extends BaseService<AppCode,AppCodeKey,AppCodeCriteria>{

	//================================********Generated code start here********================================
	
	@Autowired
	private AppCodeDao appCodeDao;
	
	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写
	 * @param dao
	 */
	public void setAppCodeDao(AppCodeDao dao) {
		this.appCodeDao = dao;
	}
	
	@Override
	protected EntityDao getEntityDao() {
		return this.appCodeDao;
	}
	
	/**
	 * 翻页查询
	 * @param query
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page findPage(AppCodeQuery query) {
		//return appCodeDao.findPage(query);
		return null;
	}
	

	//================================********Generated code end here********================================
	
	//================================********Customized code start here********==============================
	
	
	
	//================================********Customized code end here********================================
}
