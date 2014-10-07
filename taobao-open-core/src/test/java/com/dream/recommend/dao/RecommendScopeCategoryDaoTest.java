/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.recommend.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;

import com.dream.rapid.test.context.TestMethodContext;

import static junit.framework.Assert.*;

import java.util.*;

import com.dream.rapid.base.*;
import com.dream.rapid.util.*;
import com.dream.rapid.page.*;

import com.dream.recommend.model.*;
import com.dream.recommend.dao.*;
import com.dream.recommend.service.*;
import com.dream.recommend.vo.query.*;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class RecommendScopeCategoryDaoTest extends BaseDaoTestCase{
	
	private RecommendScopeCategoryDao dao;
	
	@Autowired
	public void setRecommendScopeCategoryDao(RecommendScopeCategoryDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/RecommendScopeCategory.xml",
		                    "classpath:testdata/RecommendScopeCategory_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		RecommendScopeCategoryQuery query = newRecommendScopeCategoryQuery();
//		Page page = dao.findPage(query);
//		
//		assertEquals(pageNumber,page.getThisPageNumber());
//		assertEquals(pageSize,page.getPageSize());
//		List resultList = (List)page.getResult();
//		assertNotNull(resultList);
		
	}
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static RecommendScopeCategoryQuery newRecommendScopeCategoryQuery() {
		RecommendScopeCategoryQuery query = new RecommendScopeCategoryQuery();
		query.setPageNumber(pageNumber);
		query.setPageSize(pageSize);
		query.setSortColumns(null);
		
	  	query.setShopId(new String("1"));
	  	query.setCatId(new String("1"));
	  	query.setCatName(new String("1"));
		query.setLastModifiedTimeBegin(new Date(System.currentTimeMillis()));
		query.setLastModifiedTimeEnd(new Date(System.currentTimeMillis()));
	  	query.setLastModifiedBy(new Long("1"));
		return query;
	}
	
}
