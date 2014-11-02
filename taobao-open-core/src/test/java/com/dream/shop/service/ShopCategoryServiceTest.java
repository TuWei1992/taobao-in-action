/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.shop.service;

import static junit.framework.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dream.rapid.base.BaseServiceTestCase;
import com.dream.rapid.test.context.TestMethodContext;
import com.dream.shop.model.ShopCategory;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class ShopCategoryServiceTest extends BaseServiceTestCase{

	private ShopCategoryService service;
	
	@Autowired
	public void setShopCategoryService(ShopCategoryService service) {
		this.service = service;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/ShopCategory.xml",
                            "classpath:testdata/ShopCategory_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		ShopCategory obj = newShopCategory();
		service.save(obj);
		service.getEntityDao().flush();
		
		service.update(obj);
		service.getEntityDao().flush();
		
		assertNotNull(obj.getCid());
		
//		service.removeById(obj.getCid());
		service.getEntityDao().flush();
	
	}
	
	public static ShopCategory newShopCategory() {
		ShopCategory obj = new ShopCategory();
		
	  	obj.setParentCid(new java.lang.Long("1"));
	  	obj.setName(new java.lang.String("1"));
	  	obj.setPicUrl(new java.lang.String("1"));
	  	obj.setSortOrder(new java.lang.Integer("1"));
	  	obj.setType(new java.lang.String("1"));
	  	obj.setShopId(new java.lang.Long("1"));
	  	obj.setCreateTime(new java.util.Date(System.currentTimeMillis()));
	  	obj.setRefreshTime(new java.util.Date(System.currentTimeMillis()));
		return obj;
	}
}
