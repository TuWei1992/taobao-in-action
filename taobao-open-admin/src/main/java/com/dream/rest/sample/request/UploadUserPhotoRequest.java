/**
 *  
 * 
 */
package com.dream.rest.sample.request;

import com.dream.rest.AbstractRopRequest;
import com.dream.rest.request.UploadFile;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class UploadUserPhotoRequest extends AbstractRopRequest {

    private String userId;

    private UploadFile photo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UploadFile getPhoto() {
        return photo;
    }

    public void setPhoto(UploadFile photo) {
        this.photo = photo;
    }
}

