/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.menu.service;

import static junit.framework.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dream.menu.model.Menu;
import com.dream.rapid.base.BaseServiceTestCase;
import com.dream.rapid.test.context.TestMethodContext;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class MenuServiceTest extends BaseServiceTestCase{

	private MenuService service;
	
	@Autowired
	public void setMenuService(MenuService service) {
		this.service = service;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/Menu.xml",
                            "classpath:testdata/Menu_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		Menu obj = newMenu();
//		service.save(obj);
//		service.getEntityDao().flush();
		
		service.update(obj);
		service.getEntityDao().flush();
		
		assertNotNull(obj.getMenuId());
		
		service.removeById(obj.getMenuId());
		service.getEntityDao().flush();
	
	}
	
	public static Menu newMenu() {
		Menu obj = new Menu();
		obj.setMenuId(new java.lang.String("1"));
	  	obj.setMenuNameCn(new java.lang.String("1"));
	  	obj.setLftValue(new java.lang.String("1"));
	  	obj.setRgtValue(new java.lang.String("1"));
	  	obj.setOrderNum(new java.lang.Integer("1"));
	  	obj.setMenuNameEn(new java.lang.String("1"));
		return obj;
	}
}
