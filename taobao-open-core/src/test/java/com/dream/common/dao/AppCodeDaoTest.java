/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.common.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;

import com.dream.rapid.test.context.TestMethodContext;

import static junit.framework.Assert.*;

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


public class AppCodeDaoTest extends BaseDaoTestCase{
	
	private AppCodeDao dao;
	
	@Autowired
	public void setAppCodeDao(AppCodeDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/AppCode.xml",
		                    "classpath:testdata/AppCode_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		AppCodeQuery query = newAppCodeQuery();
//		Page page = dao.findPage(query);
//		
//		assertEquals(pageNumber,page.getThisPageNumber());
//		assertEquals(pageSize,page.getPageSize());
//		List resultList = (List)page.getResult();
//		assertNotNull(resultList);
//		
	}
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static AppCodeQuery newAppCodeQuery() {
		AppCodeQuery query = new AppCodeQuery();
		query.setPageNumber(pageNumber);
		query.setPageSize(pageSize);
		query.setSortColumns(null);
		
	  	query.setCodeNameCn(new String("1"));
	  	query.setCodeNameEn(new String("1"));
	  	query.setCodeValue(new String("1"));
	  	query.setCodeDesc(new String("1"));
		return query;
	}
	
}
