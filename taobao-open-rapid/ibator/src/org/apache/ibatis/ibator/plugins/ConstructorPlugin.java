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
import org.apache.ibatis.ibator.api.dom.java.JavaVisibility;
import org.apache.ibatis.ibator.api.dom.java.Method;
import org.apache.ibatis.ibator.api.dom.java.Parameter;
import org.apache.ibatis.ibator.api.dom.java.TopLevelClass;

/**
 * 当主键只为一个列时，生成带主键的构造函数及默认的构造函数
 * @author Frank
 *
 */
public class ConstructorPlugin extends IbatorPluginAdapter {

    public ConstructorPlugin() {
    }

    /**
     * This plugin is always valid - no properties are required
     */
    public boolean validate(List<String> warnings) {
        return true;
    }
    
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
        if(columns.size()>1){
        	return true;
        }
        FullyQualifiedJavaType keyJavaType = introspectedTable.getPrimaryKeyType();
        generateConstructor(topLevelClass,introspectedTable);
        generateConstructor(topLevelClass, columns.get(0), keyJavaType,introspectedTable);
        return true;
    }

    /**
     * 
     * @param topLevelClass
     * @param introspectedColumns
     * @param introspectedTable
     */
    protected void generateConstructor(TopLevelClass topLevelClass,IntrospectedColumn introspectedColumn, FullyQualifiedJavaType keyJavaType, IntrospectedTable introspectedTable) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setConstructor(true);
        method.setName(introspectedTable.getBaseRecordType().getShortName());
        String fieldNameString = introspectedColumn.getJavaProperty();
        method.addParameter(new Parameter(keyJavaType,fieldNameString)); //$NON-NLS-1$
        ibatorContext.getCommentGenerator().addGeneralMethodComment(method,introspectedTable.getFullyQualifiedTable());
        method.addBodyLine("this."+fieldNameString+" = "+fieldNameString); //$NON-NLS-1$
        method.addBodyLine("}"); //$NON-NLS-1$
        topLevelClass.addMethod(method);
    }
    
    protected void generateConstructor(TopLevelClass topLevelClass,IntrospectedTable introspectedTable) {
    	Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setConstructor(true);
        method.setName(introspectedTable.getBaseRecordType().getShortName());
        ibatorContext.getCommentGenerator().addGeneralMethodComment(method,introspectedTable.getFullyQualifiedTable());
        method.addBodyLine("}"); //$NON-NLS-1$
        topLevelClass.addMethod(method);
	}

}
