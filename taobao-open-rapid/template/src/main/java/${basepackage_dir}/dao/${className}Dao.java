<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao;

<#include "/java_imports.include">

import org.springframework.stereotype.Repository;


@Repository
public class ${className}Dao extends BaseIbatisDao<${className},${table.idColumn.javaType}>{

	//================================********Generated code start here********================================
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "${className}";
	}
	
	public void saveOrUpdate(${className} entity) {
		if(entity.get${table.idColumn.columnName}() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	public Page findPage(${className}Query query) {
		return pageQuery(getIbatisSqlMapNamespace()+".findPage",query);
	}
	
	<#list table.columns as column>
	<#if column.unique && !column.pk>
	
	/**
	 * 按指定的列${column.columnName}查询
	 * @param v
	 * @return
	 */
	public ${className} getBy${column.columnName}(${column.javaType} v) {
		return (${className})getSqlMapClientTemplate().queryForObject(getIbatisSqlMapNamespace()+".getBy${column.columnName}",v);
	}	
	
	</#if>
	</#list>

	//================================********Generated code end here********================================
	
	//================================********Customized code start here********==============================
	
	
	
	//================================********Customized code end here********================================

	
	
}
