/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.auth.dao;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dream.auth.vo.query.AuthQuery;
import com.dream.rapid.base.BaseDaoTestCase;
import com.dream.rapid.page.Page;
import com.dream.rapid.test.context.TestMethodContext;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class AuthDaoTest extends BaseDaoTestCase{
	
	private AuthDao dao;
	
	@Autowired
	public void setAuthDao(AuthDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/Auth.xml",
		                    "classpath:testdata/Auth_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		AuthQuery query = newAuthQuery();
//		Page page = dao.findPage(query);
//		
//		assertEquals(pageNumber,page.getThisPageNumber());
//		assertEquals(pageSize,page.getPageSize());
//		List resultList = (List)page.getResult();
//		assertNotNull(resultList);
		
	}
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static AuthQuery newAuthQuery() {
		AuthQuery query = new AuthQuery();
		query.setPageNumber(pageNumber);
		query.setPageSize(pageSize);
		query.setSortColumns(null);
		
	  	query.setIsLock(new String("1"));
	  	query.setIsEnable(new String("1"));
	  	query.setTaobaoUserNick(new String("1"));
	  	query.setTaobaoUserId(new String("1"));
	  	query.setSubTaobaoUserId(new String("1"));
	  	query.setSubTaobaoUserNick(new String("1"));
		query.setRefreshedTimeBegin(new Date(System.currentTimeMillis()));
		query.setRefreshedTimeEnd(new Date(System.currentTimeMillis()));
		return query;
	}
	
}
