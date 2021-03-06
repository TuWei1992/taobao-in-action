/**
 * 
 * 
 */
package com.dream.rest.response;

import com.dream.rest.security.MainError;
import com.dream.rest.security.SubError;
import com.dream.rest.security.SubErrorType;
import com.dream.rest.security.SubErrors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Locale;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "error")
public class TimeoutErrorResponse extends ErrorResponse {

    private static final String ISP = "isp.";

    private static final String SERVICE_TIMEOUT = "-service-timeout";

    public TimeoutErrorResponse() {
    }

    public TimeoutErrorResponse(String method, Locale locale, int timeout) {
        MainError mainError = SubErrors.getMainError(SubErrorType.ISP_SERVICE_TIMEOUT, locale);

        ArrayList<SubError> subErrors = new ArrayList<SubError>();

        String errorCodeKey = ISP + transform(method) + SERVICE_TIMEOUT;
        SubError subError = SubErrors.getSubError(errorCodeKey,
                SubErrorType.ISP_SERVICE_TIMEOUT.value(),
                locale,
                method, timeout);
        subErrors.add(subError);

        setSubErrors(subErrors);
        setMainError(mainError);
    }

}

