/*
 *  Copyright 2008 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.ibatis.ibator.plugins;

import java.util.List;

import org.apache.ibatis.ibator.api.IbatorPluginAdapter;
import org.apache.ibatis.ibator.api.IntrospectedColumn;
import org.apache.ibatis.ibator.api.IntrospectedTable;
import org.apache.ibatis.ibator.api.dom.java.FullyQualifiedJavaType;
import org.apache.ibatis.ibator.internal.rules.IbatorRules;

/**
 * 
 * @author Frank
 *
 */
public class ResetPrimariyKeyTypePlugin extends IbatorPluginAdapter {

    /**
     * 
     */
    public ResetPrimariyKeyTypePlugin() {
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.ibator.api.IbatorPlugin#validate(java.util.List)
     */
    public boolean validate(List<String> warnings) {
    	return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
    	IbatorRules rules = introspectedTable.getRules();
    	//如果不生成Primary Key的类，此时主键应该只为一个列名，因此需要重置主键的类型
    	if(!rules.generatePrimaryKeyClass()){
    	  List<IntrospectedColumn> columns =  introspectedTable.getPrimaryKeyColumns();
       	  if(columns.size()==1){
       		  IntrospectedColumn introspectedColumn = columns.get(0);
       		  FullyQualifiedJavaType fqjt = introspectedColumn.getFullyQualifiedJavaType();
       		  introspectedTable.setAttribute(IntrospectedTable.ATTR_PRIMARY_KEY_TYPE,fqjt);
       	  }
    	}
    }
    
    
    
}
