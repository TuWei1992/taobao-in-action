/**
 *
 * 
 */
package com.dream.rest.response;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre>
 *    通用的响应对象
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "response")
public class CommonRopResponse{

    @XmlAttribute
    private boolean successful = false;

    public static final CommonRopResponse SUCCESSFUL_RESPONSE = new CommonRopResponse(true);
    public static final CommonRopResponse FAILURE_RESPONSE = new CommonRopResponse(false);

    public CommonRopResponse() {
    }

    private CommonRopResponse(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }
}

