/**
 * 
 * 
 */
package com.dream.rest.annotation;

/**
 * <pre>
 *   服务方法是否已经过期，过期的服务方法不能再访问
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public enum  ObsoletedType {
    YES, NO, DEFAULT;

     public static boolean isObsoleted(ObsoletedType type) {
         if (YES == type ) {
             return true;
         } else {
             return false;
         }
     }
}

