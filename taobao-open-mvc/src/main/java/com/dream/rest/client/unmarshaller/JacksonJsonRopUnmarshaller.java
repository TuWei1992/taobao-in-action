/**
 * 
 * 
 */
package com.dream.rest.client.unmarshaller;

import com.dream.rest.RopException;
import com.dream.rest.client.RopUnmarshaller;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

import java.io.IOException;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class JacksonJsonRopUnmarshaller implements RopUnmarshaller {

    private static ObjectMapper objectMapper;


    public <T> T unmarshaller(String content, Class<T> objectType) {
        try {
            return getObjectMapper().readValue(content, objectType);
        } catch (IOException e) {
            throw new RopException(e);
        }
    }

    private ObjectMapper getObjectMapper() throws IOException {
        if (this.objectMapper == null) {
            ObjectMapper objectMapper = new ObjectMapper();
            AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
            SerializationConfig serializationConfig = objectMapper.getSerializationConfig();
            serializationConfig = serializationConfig.without(SerializationConfig.Feature.WRAP_ROOT_VALUE)
                                                     .withAnnotationIntrospector(introspector);
            objectMapper.setSerializationConfig(serializationConfig);
            this.objectMapper = objectMapper;
        }
        return this.objectMapper;
    }
}

