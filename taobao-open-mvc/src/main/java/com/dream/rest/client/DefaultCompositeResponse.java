/**
 * 
 * 
 */
package com.dream.rest.client;

import com.dream.rest.response.ErrorResponse;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class DefaultCompositeResponse<T> implements CompositeResponse {

    private boolean successful;

    private ErrorResponse errorResponse;

    private T successRopResponse;

    public DefaultCompositeResponse(boolean successful) {
        this.successful = successful;
    }


    public ErrorResponse getErrorResponse() {
        return this.errorResponse;
    }


    public T getSuccessResponse() {
        return this.successRopResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public void setSuccessRopResponse(T successRopResponse) {
        this.successRopResponse = successRopResponse;
    }


    public boolean isSuccessful() {
        return successful;
    }
}

