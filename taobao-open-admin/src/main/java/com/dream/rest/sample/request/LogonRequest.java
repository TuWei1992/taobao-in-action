/**
 *  
 * 12-7-14
 */
package com.dream.rest.sample.request;

import com.dream.rest.AbstractRopRequest;
import com.dream.rest.annotation.IgnoreSign;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class LogonRequest extends AbstractRopRequest{

    @NotNull
    @Pattern(regexp = "\\w{4,30}")
    private String userName;

    @IgnoreSign
    @Pattern(regexp = "\\w{6,30}")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

