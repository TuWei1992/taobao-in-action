/**
 * 
 * 
 */
package com.dream.rest.client.unmarshaller;

import com.dream.rest.RopException;
import com.dream.rest.client.RopUnmarshaller;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 * 功能说明：将返回报文解析为Java Bean对象。
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class JaxbXmlRopUnmarshaller implements RopUnmarshaller {

    private static Map<Class, JAXBContext> jaxbContextHashMap = new ConcurrentHashMap<Class, JAXBContext>();


    public <T> T unmarshaller(String content, Class<T> objectType) {
        try {
            Unmarshaller unmarshaller = buildUnmarshaller(objectType);
            StringReader reader = new StringReader(content);
            new InputSource(reader);
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RopException(e);
        }

    }

    private Unmarshaller buildUnmarshaller(Class<?> objectType) throws JAXBException {
        if (!jaxbContextHashMap.containsKey(objectType)) {
            JAXBContext context = JAXBContext.newInstance(objectType);
            jaxbContextHashMap.put(objectType, context);
        }
        JAXBContext context = jaxbContextHashMap.get(objectType);
        Unmarshaller unmarshaller = context.createUnmarshaller();
//        unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        unmarshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
        return unmarshaller;
    }
}

