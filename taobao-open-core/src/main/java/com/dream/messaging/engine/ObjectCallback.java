/**
 * 
 */
package com.dream.messaging.engine;


/**
 * @author Frank
 * A Call back interface that do something useful when the object generated.
 */
public interface ObjectCallback {
	
	Object handleObject(Object origin) throws Exception;

}
