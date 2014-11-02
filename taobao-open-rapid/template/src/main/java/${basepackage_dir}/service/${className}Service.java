<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<#include "/java_imports.include">
@Service
@Transactional
public class ${className}Service extends BaseService<${className},${className},${className}Criteria>{

	//================================********Generated code start here********================================
	
	@Autowired
	private ${className}Dao ${classNameLower}Dao;
	
	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写
	 * @param dao
	 */
	public void set${className}Dao(${className}Dao dao) {
		this.${classNameLower}Dao = dao;
	}
	
	@Override
	protected EntityDao getEntityDao() {
		return this.${classNameLower}Dao;
	}
	
	/**
	 * 翻页查询
	 * @param query
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page findPage(${className}Query query) {
		//return ${classNameLower}Dao.findPage(query);
		return null;
	}
	
<#list table.columns as column>
	<#if column.unique && !column.pk>
	
	/**
	 * 按指定的${column.columnName}查询
	 * @param v
	 * @return
	 */
	@Transactional(readOnly=true)
	public ${className} getBy${column.columnName}(${column.javaType} v) {
		return ${classNameLower}Dao.getBy${column.columnName}(v);
	}	
	
	</#if>
</#list>

	//================================********Generated code end here********================================
	
	//================================********Customized code start here********==============================
	
	
	
	//================================********Customized code end here********================================
}
