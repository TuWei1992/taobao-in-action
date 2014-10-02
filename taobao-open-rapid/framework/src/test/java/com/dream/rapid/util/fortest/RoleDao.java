/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package com.dream.rapid.util.fortest;

import org.springframework.stereotype.Component;

import com.dream.rapid.page.Page;
import com.dream.rapid.page.PageRequest;

@Component
public class RoleDao {

	public Class getEntityClass() {
		return Role.class;
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		//XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
//		String sql = "from Role as a where 1=1 "
//				+ "/~ and a.roleName = '[roleName]' ~/"
//				+ "/~ and a.resourceId = '[resourceId]' ~/"
//				+ "/~ order by [sortingColumn] [sortingDirection] ~/";
//		return pageQuery(sql,pageRequest);
		return null;
	}
	

}
