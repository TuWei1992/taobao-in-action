/**
 *
 * 
 */
package com.dream.rest.security;

import java.util.List;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public interface MainError {

    String getCode();

    String getMessage();

    String getSolution();

    List<SubError> getSubErrors();

    MainError addSubError(SubError subError);

}

