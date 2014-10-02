/*
 *  Copyright 2006 The Apache Software Foundation
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
package org.apache.ibatis.ibator.generator.ibatis2.dao.templates;

import org.apache.ibatis.ibator.api.dom.java.FullyQualifiedJavaType;

/**
 * @author Jeff Butler
 */
public class CustomizedSpringDAOTemplate extends SpringDAOTemplate {

    

    @Override
    protected void configureSuperClass() {
    	FullyQualifiedJavaType type = new FullyQualifiedJavaType("com.dream.rapid.base.BaseIbatisDao");
        setSuperClass(type); //$NON-NLS-1$
    }
    
    
    protected void configureImplementationImports() {
    	FullyQualifiedJavaType type = new FullyQualifiedJavaType("org.springframework.stereotype.Repository");
    	addImplementationImport(type);
    }
}
