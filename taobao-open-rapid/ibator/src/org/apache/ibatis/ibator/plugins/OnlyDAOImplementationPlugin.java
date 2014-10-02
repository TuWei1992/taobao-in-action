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
import org.apache.ibatis.ibator.api.IntrospectedTable;
import org.apache.ibatis.ibator.api.dom.java.FullyQualifiedJavaType;
import org.apache.ibatis.ibator.api.dom.java.Interface;
import org.apache.ibatis.ibator.api.dom.java.JavaVisibility;
import org.apache.ibatis.ibator.api.dom.java.Method;
import org.apache.ibatis.ibator.api.dom.java.Parameter;
import org.apache.ibatis.ibator.api.dom.java.TopLevelClass;
import org.apache.ibatis.ibator.internal.util.StringUtility;

/**
 * 
 * @author Frank
 *
 */
public class OnlyDAOImplementationPlugin extends IbatorPluginAdapter {
	
	private String annotationString;
	
	public boolean validate(List<String> warnings) {
		annotationString = properties.getProperty("annotationString");
        boolean valid = StringUtility.stringHasValue(annotationString);
        return valid;
	}
	
	public boolean daoImplementationGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    	FullyQualifiedJavaType superType =  topLevelClass.getSuperClass();
    	FullyQualifiedJavaType record = (FullyQualifiedJavaType) introspectedTable.getAttribute(IntrospectedTable.ATTR_BASE_RECORD_TYPE);
    	superType.addTypeArgument(record);
    	FullyQualifiedJavaType pk = (FullyQualifiedJavaType) introspectedTable.getAttribute(IntrospectedTable.ATTR_PRIMARY_KEY_TYPE);
    	superType.addTypeArgument(pk);
    	FullyQualifiedJavaType criteria = (FullyQualifiedJavaType) introspectedTable.getAttribute(IntrospectedTable.ATTR_EXAMPLE_TYPE);
    	superType.addTypeArgument(criteria);
    	topLevelClass.addAnnotation(annotationString);
    	
    	Method prepare = new Method();
    	prepare.setConstructor(false);
    	prepare.setVisibility(JavaVisibility.PROTECTED);
    	prepare.setName("prepareObjectForSaveOrUpdate");
    	prepare.addParameter(new Parameter(record,"record")); //$NON-NLS-1$
    	prepare.addBodyLine(";"); //$NON-NLS-1$
        topLevelClass.addMethod(prepare);
        
        Method namespace = new Method();
        namespace.setConstructor(false);
        namespace.setVisibility(JavaVisibility.PROTECTED);
        namespace.setName("getIbatisSqlMapNamespace");
        namespace.addBodyLine("return \""+introspectedTable.getFullyQualifiedTable().getSqlMapNamespace()+"\";"); //$NON-NLS-1$
        namespace.setReturnType(FullyQualifiedJavaType.getStringInstance());
        topLevelClass.addMethod(namespace);
        
        Method param = new Method();
        param.setConstructor(false);
        param.setVisibility(JavaVisibility.PROTECTED);
        param.setName("getCriteriaParam");
        param.addParameter(new Parameter(record,"record")); //$NON-NLS-1$
        param.addParameter(new Parameter(criteria,"criteria")); //$NON-NLS-1$
        param.addBodyLine("return new UpdateByCriteriaParms(record,criteria);"); //$NON-NLS-1$
        param.setReturnType(criteria);
        topLevelClass.addMethod(param);
    	
        return true;
    }
	
	public boolean daoInterfaceGenerated(Interface interfaze, IntrospectedTable introspectedTable){
		return false;
	}
	
	
	
	
	
	
	public boolean daoCountByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoCountByExampleMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoDeleteByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoDeleteByExampleMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoDeleteByPrimaryKeyMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }


    public boolean daoInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoInsertMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }


    public boolean daoSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoSelectByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoSelectByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoSelectByPrimaryKeyMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoUpdateByExampleSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoUpdateByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoUpdateByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoUpdateByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoUpdateByPrimaryKeySelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    public boolean daoUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }
	

    public boolean daoInsertSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
    	return false;
    }

    public boolean daoInsertSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    	return false;
    }
	
	
}
