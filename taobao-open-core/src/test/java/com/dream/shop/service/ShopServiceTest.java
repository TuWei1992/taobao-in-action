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
import com.dream.shop.model.Shop;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class ShopServiceTest extends BaseServiceTestCase{

	private ShopService service;
	
	@Autowired
	public void setShopService(ShopService service) {
		this.service = service;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/Shop.xml",
                            "classpath:testdata/Shop_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		Shop obj = newShop();
		service.save(obj);
		service.getEntityDao().flush();
		
		service.update(obj);
		service.getEntityDao().flush();
		
		assertNotNull(obj.getSid());
		
//		service.removeById(obj.getSid());
		service.getEntityDao().flush();
	
	}
	
	public static Shop newShop() {
		Shop obj = new Shop();
		
	  	obj.setCid(new java.lang.Long("1"));
	  	obj.setNick(new java.lang.String("1"));
	  	obj.setTitle(new java.lang.String("1"));
	  	obj.setDesc(new java.lang.String("1"));
	  	obj.setBulletin(new java.lang.String("1"));
	  	obj.setPicPath(new java.lang.String("1"));
	  	obj.setCreated(new java.util.Date(System.currentTimeMillis()));
	  	obj.setModified(new java.util.Date(System.currentTimeMillis()));
		return obj;
	}
}
