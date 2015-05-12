/**
 * 
 * 
 */
package com.dream.rest.request;

import java.util.Collection;

import com.dream.rest.MessageFormat;
import com.dream.rest.client.unmarshaller.MessageUnmarshallerUtils;
import com.dream.rest.marshaller.MessageMarshallerUtils;


/**
 * 将集合类转化为String，或者将String转化为集合类
 * User: Frank
 * Date: 13-10-25
 * Time: 上午11:54
 */
public class CollectionsConverter implements RopConverter<String, Collection> {

	
    public String unconvert(Collection target) {
        return MessageMarshallerUtils.getMessage(target, MessageFormat.json);
    }


    public Class<String> getSourceClass() {
        return String.class;  
    }


    public Class<Collection> getTargetClass() {
        return Collection.class; 
    }


    public Collection convert(String content) {
    	return (Collection) MessageUnmarshallerUtils.getObject(content,Collection.class, MessageFormat.json);
    }
}
