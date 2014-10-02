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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.ibator.api.IbatorPluginAdapter;
import org.apache.ibatis.ibator.api.IntrospectedTable;
import org.apache.ibatis.ibator.api.dom.java.FullyQualifiedJavaType;
import org.apache.ibatis.ibator.internal.util.StringUtility;
import org.apache.ibatis.ibator.internal.util.messages.Messages;

/**
 * 
 * @author Frank
 *
 */
public class RenameTablePrefixPlugin extends IbatorPluginAdapter {
    private String searchString;
    private String replaceString = "";

    /**
     * 
     */
    public RenameTablePrefixPlugin() {
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.ibator.api.IbatorPlugin#validate(java.util.List)
     */
    public boolean validate(List<String> warnings) {
        
        searchString = properties.getProperty("searchString");
        
        replaceString = properties.getProperty("replaceString") == null ? replaceString:properties.getProperty("replaceString");
        
        boolean valid = StringUtility.stringHasValue(searchString);
        
        if (valid) {
        	} else {
            if (!StringUtility.stringHasValue(searchString)) {
                warnings.add(Messages.getString("Warning.27"));
            }
        }
        
        return valid;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
//    	  calculateDAOImplementationPackage();
//        calculateDAOInterfacePackage();
//        calculateDAOImplementationType();
    	renameJavaType(introspectedTable,IntrospectedTable.ATTR_DAO_IMPLEMENTATION_TYPE);
//        calculateDAOInterfaceType();
    	renameJavaType(introspectedTable,IntrospectedTable.ATTR_DAO_INTERFACE_TYPE);
//        calculateJavaModelPackage();
//        calculatePrimaryKeyType();
    	renameJavaType(introspectedTable,IntrospectedTable.ATTR_PRIMARY_KEY_TYPE);
//        calculateBaseRecordType();
    	renameJavaType(introspectedTable,IntrospectedTable.ATTR_BASE_RECORD_TYPE);
//        calculateRecordWithBLOBsType();
    	renameJavaType(introspectedTable,IntrospectedTable.ATTR_RECORD_WITH_BLOBS_TYPE);
//        calculateExampleType();
    	renameJavaType(introspectedTable,IntrospectedTable.ATTR_EXAMPLE_TYPE);
//        calculateSqlMapPackage();
//        calculateSqlMapFileName();
    	renameSqlMap(introspectedTable,IntrospectedTable.ATTR_SQL_MAP_FILE_NAME);
    	  
    	  
    }
    
    
    private void renameJavaType(IntrospectedTable introspectedTable,String attribute){
		FullyQualifiedJavaType oldType = (FullyQualifiedJavaType)introspectedTable.getAttribute(attribute);
        String typeName = oldType.getFullyQualifiedName();
        Pattern pattern = Pattern.compile(searchString);
        Matcher matcher = pattern.matcher(typeName);
        typeName = matcher.replaceAll(replaceString);
        introspectedTable.setAttribute(attribute,new FullyQualifiedJavaType(typeName));
    }
    
    private void renameSqlMap(IntrospectedTable introspectedTable,String attribute){
		String oldType = (String)introspectedTable.getAttribute(attribute);
        String typeName = oldType;
        Pattern pattern = Pattern.compile(searchString);
        Matcher matcher = pattern.matcher(typeName);
        typeName = matcher.replaceAll(replaceString);
        introspectedTable.setAttribute(attribute,typeName);
    }
}
