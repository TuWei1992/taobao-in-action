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
package org.apache.ibatis.ibator.generator.ibatis2.dao.elements;

import org.apache.ibatis.ibator.api.FullyQualifiedTable;
import org.apache.ibatis.ibator.api.dom.java.Field;
import org.apache.ibatis.ibator.api.dom.java.FullyQualifiedJavaType;
import org.apache.ibatis.ibator.api.dom.java.InnerClass;
import org.apache.ibatis.ibator.api.dom.java.Interface;
import org.apache.ibatis.ibator.api.dom.java.JavaVisibility;
import org.apache.ibatis.ibator.api.dom.java.Method;
import org.apache.ibatis.ibator.api.dom.java.Parameter;
import org.apache.ibatis.ibator.api.dom.java.TopLevelClass;

/**
 * 
 * @author Jeff Butler
 *
 */
public class UpdateByExampleParmsInnerclassGenerator extends
        AbstractDAOElementGenerator {

    public UpdateByExampleParmsInnerclassGenerator() {
        super();
    }

    @Override
    public void addImplementationElements(TopLevelClass topLevelClass) {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        topLevelClass.addImportedType(introspectedTable.getExampleType());
        
        InnerClass innerClass = new InnerClass(
                new FullyQualifiedJavaType("UpdateByCriteriaParms")); //$NON-NLS-1$
        innerClass.setVisibility(JavaVisibility.PRIVATE);
        innerClass.setStatic(true);
        innerClass.setSuperClass(introspectedTable.getExampleType());
        ibatorContext.getCommentGenerator().addClassComment(innerClass, table);
        
        Method method = new Method();
        method.setConstructor(true);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(innerClass.getType().getShortName());
        method.addParameter(
                new Parameter(FullyQualifiedJavaType.getObjectInstance(),
                        "record")); //$NON-NLS-1$
        method.addParameter(
                new Parameter(introspectedTable.getExampleType(),
                        "criteria")); //$NON-NLS-1$
        method.addBodyLine("super(criteria);"); //$NON-NLS-1$
        method.addBodyLine("this.record = record;"); //$NON-NLS-1$
        innerClass.addMethod(method);
        
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(FullyQualifiedJavaType.getObjectInstance());
        field.setName("record"); //$NON-NLS-1$
        innerClass.addField(field);
        
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getObjectInstance());
        method.setName("getRecord"); //$NON-NLS-1$
        method.addBodyLine("return record;"); //$NON-NLS-1$
        innerClass.addMethod(method);
        
        topLevelClass.addInnerClass(innerClass);
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        // nothing to add to the interface
        ;
    }
}
