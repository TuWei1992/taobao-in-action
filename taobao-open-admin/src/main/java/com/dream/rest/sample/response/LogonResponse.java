
package com.dream.rest.sample.response;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre>
 * 功能说明：<xml><logonResponse><sessionId>123456789</sessionId><name>zhangsan</name></logonResponse></xml>
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "logonResponse")
public class LogonResponse{

    @XmlAttribute
    private String sessionId;
    
    @XmlAttribute(name = "name")
    private String name;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}

