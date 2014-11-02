/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.shop.dao;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dream.rapid.base.BaseDaoTestCase;
import com.dream.rapid.page.Page;
import com.dream.rapid.test.context.TestMethodContext;
import com.dream.recommend.dao.RecommendSpecifiedDao;
import com.dream.recommend.vo.query.RecommendSpecifiedQuery;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class RecommendSpecifiedDaoTest extends BaseDaoTestCase{
	
	private RecommendSpecifiedDao dao;
	
	@Autowired
	public void setRecommendSpecifiedDao(RecommendSpecifiedDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/RecommendSpecified.xml",
		                    "classpath:testdata/RecommendSpecified_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		RecommendSpecifiedQuery query = newRecommendSpecifiedQuery();
//		Page page = dao.findPage(query);
		
//		assertEquals(pageNumber,page.getThisPageNumber());
//		assertEquals(pageSize,page.getPageSize());
//		List resultList = (List)page.getResult();
//		assertNotNull(resultList);
		
	}
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static RecommendSpecifiedQuery newRecommendSpecifiedQuery() {
		RecommendSpecifiedQuery query = new RecommendSpecifiedQuery();
		query.setPageNumber(pageNumber);
		query.setPageSize(pageSize);
		query.setSortColumns(null);
		
//	  	query.setShopId(new Long("1"));
//	  	query.setType(new String("1"));
//	  	query.setItemId(new Long("1"));
//	  	query.setLastModifiedBy(new Long("1"));
//		query.setLastModifiedTimeBegin(new Date(System.currentTimeMillis()));
//		query.setLastModifiedTimeEnd(new Date(System.currentTimeMillis()));
//	  	query.setItemName(new String("1"));
		return query;
	}
	
}
