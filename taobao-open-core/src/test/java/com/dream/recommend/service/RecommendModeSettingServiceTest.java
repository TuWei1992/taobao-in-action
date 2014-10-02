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


public class RecommendModeSettingServiceTest extends BaseServiceTestCase{

	private RecommendModeSettingService service;
	
	@Autowired
	public void setRecommendModeSettingService(RecommendModeSettingService service) {
		this.service = service;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/RecommendModeSetting.xml",
                            "classpath:testdata/RecommendModeSetting_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		RecommendModeSetting obj = newRecommendModeSetting();
		service.save(obj);
		service.getEntityDao().flush();
		
		service.update(obj);
		service.getEntityDao().flush();
		
		assertNotNull(obj.getShopId());
		
//		service.removeById(obj.getShopId());
		service.getEntityDao().flush();
	
	}
	
	public static RecommendModeSetting newRecommendModeSetting() {
		RecommendModeSetting obj = new RecommendModeSetting();
		
	  	obj.setIsSaledFirst(new java.lang.String("1"));
	  	obj.setSaledNum(new java.lang.Integer("1"));
	  	obj.setIsStorageFiltered(new java.lang.String("1"));
	  	obj.setStorageNum(new java.lang.String("1"));
	  	obj.setIsOffshelvesFiltered(new java.lang.String("1"));
	  	obj.setOffshelvesTime(new java.lang.Integer("1"));
	  	obj.setIsOnshelvesFiltered(new java.lang.String("1"));
	  	obj.setLastModifiedBy(new java.lang.String("1"));
	  	obj.setLastModifiedTime(new java.util.Date(System.currentTimeMillis()));
		return obj;
	}
}
