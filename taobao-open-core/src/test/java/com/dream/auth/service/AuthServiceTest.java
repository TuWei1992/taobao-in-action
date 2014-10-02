/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;

import com.dream.rapid.test.context.TestMethodContext;
import static junit.framework.Assert.*;

import java.util.*;

import com.dream.rapid.base.*;
import com.dream.rapid.util.*;
import com.dream.rapid.page.*;

import com.dream.auth.model.*;
import com.dream.auth.dao.*;
import com.dream.auth.service.*;
import com.dream.auth.vo.query.*;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */


public class AuthServiceTest extends BaseServiceTestCase{

	private AuthService service;
	
	@Autowired
	public void setAuthService(AuthService service) {
		this.service = service;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/Auth.xml",
                            "classpath:testdata/Auth_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		Auth obj = newAuth();
		service.save(obj);
		service.getEntityDao().flush();
		
		service.update(obj);
		service.getEntityDao().flush();
		
		assertNotNull(obj.getUserId());
		
		service.removeById(null);
		service.getEntityDao().flush();
	
	}
	
	public static Auth newAuth() {
		Auth obj = new Auth();
		
	  	obj.setIsLock(new java.lang.String("1"));
	  	obj.setIsEnable(new java.lang.String("1"));
	  	obj.setTaobaoUserNick(new java.lang.String("1"));
	  	obj.setTaobaoUserId(new java.lang.String("1"));
	  	obj.setSubTaobaoUserId(new java.lang.String("1"));
	  	obj.setSubTaobaoUserNick(new java.lang.String("1"));
	  	obj.setRefreshedTime(new java.util.Date(System.currentTimeMillis()));
		return obj;
	}
}
