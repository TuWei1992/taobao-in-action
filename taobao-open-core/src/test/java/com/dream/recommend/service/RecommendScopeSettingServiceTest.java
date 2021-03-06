/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.recommend.service;

import static junit.framework.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dream.rapid.base.BaseServiceTestCase;
import com.dream.rapid.test.context.TestMethodContext;
import com.dream.recommend.model.RecommendScopeSetting;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class RecommendScopeSettingServiceTest extends BaseServiceTestCase{

	private RecommendScopeSettingService service;
	
	@Autowired
	public void setRecommendScopeSettingService(RecommendScopeSettingService service) {
		this.service = service;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/RecommendScopeSetting.xml",
                            "classpath:testdata/RecommendScopeSetting_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		RecommendScopeSetting obj = newRecommendScopeSetting();
		service.save(obj);
		service.getEntityDao().flush();
		
		service.update(obj);
		service.getEntityDao().flush();
		
		assertNotNull(obj.getShopId());
		
//		service.removeById(obj.getShopId());
		service.getEntityDao().flush();
	
	}
	
	public static RecommendScopeSetting newRecommendScopeSetting() {
		RecommendScopeSetting obj = new RecommendScopeSetting();
		obj.setShopId(1234L);
	  	obj.setScopeType(new java.lang.String("1"));
	  	obj.setIsEnabled(new java.lang.String("1"));
//	  	obj.setLastModifiedBy(new java.lang.String("1"));
	  	obj.setLastModifiedTime(new java.util.Date(System.currentTimeMillis()));
		return obj;
	}
}
