/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.recommend.service;

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


public class RecommendSpecifiedServiceTest extends BaseServiceTestCase{

	private RecommendSpecifiedService service;
	
	@Autowired
	public void setRecommendSpecifiedService(RecommendSpecifiedService service) {
		this.service = service;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/RecommendSpecified.xml",
                            "classpath:testdata/RecommendSpecified_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		RecommendSpecified obj = newRecommendSpecified();
		service.save(obj);
		service.getEntityDao().flush();
		
		service.update(obj);
		service.getEntityDao().flush();
		
		assertNotNull(obj.getId());
		
		service.removeById(obj.getId());
		service.getEntityDao().flush();
	
	}
	
	public static RecommendSpecified newRecommendSpecified() {
		RecommendSpecified obj = new RecommendSpecified();
		
	  	obj.setShopId(new java.lang.String("1"));
	  	obj.setType(new java.lang.String("1"));
	  	obj.setItemId(new java.lang.String("1"));
	  	obj.setItemName(new java.lang.String("1"));
	  	obj.setLastModifiedBy(new java.lang.Long("1"));
	  	obj.setLastModifiedTime(new java.util.Date(System.currentTimeMillis()));
		return obj;
	}
}
