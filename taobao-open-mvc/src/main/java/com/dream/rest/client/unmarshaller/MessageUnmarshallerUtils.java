/**
 * 
 */
package com.dream.rest.client.unmarshaller;

//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.rest.MessageFormat;
import com.dream.rest.RopException;
import com.dream.rest.RopRequest;
import com.dream.rest.client.RopUnmarshaller;

/**
 * <pre>
 *   对请求响应的对象转成相应的报文。
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class MessageUnmarshallerUtils {

    protected static final Logger logger = LoggerFactory.getLogger(MessageUnmarshallerUtils.class);


    private static ObjectMapper jsonObjectMapper = new ObjectMapper();

    private static RopUnmarshaller jaxbXmlRopUnmarshaller = new JaxbXmlRopUnmarshaller();

    static {
        SerializationConfig serializationConfig = jsonObjectMapper.getSerializationConfig();
        serializationConfig = serializationConfig.without(SerializationConfig.Feature.WRAP_ROOT_VALUE).with(SerializationConfig.Feature.INDENT_OUTPUT);
    }



    /**
     * 将{@link RopRequest}转换为字符串
     *
     * @param object
     * @param format
     * @return
     */
    public static Object getObject(String content,Class clazz, MessageFormat format) {
        if (content == null) {
            return null;
        }
        Object result = null;
        try {
            if (format == MessageFormat.json) {
                result = jsonObjectMapper.readValue(content, clazz);
            } else {
            	jaxbXmlRopUnmarshaller.unmarshaller(content,clazz);
            }
        } catch (Throwable e) {
        	logger.error("Get object errror.",e);
            throw new RopException(e);
        } finally {
        }
        return result;
    }

}

