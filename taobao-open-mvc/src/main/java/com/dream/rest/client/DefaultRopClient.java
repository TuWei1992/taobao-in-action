/**
 * 
 * 
 */
package com.dream.rest.client;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import com.dream.rest.CommonConstant;
import com.dream.rest.MessageFormat;
import com.dream.rest.RopException;
import com.dream.rest.RopRequest;
import com.dream.rest.annotation.IgnoreSign;
import com.dream.rest.annotation.Temporary;
import com.dream.rest.client.unmarshaller.JacksonJsonRopUnmarshaller;
import com.dream.rest.client.unmarshaller.JaxbXmlRopUnmarshaller;
import com.dream.rest.cluster.LoadBalance;
import com.dream.rest.cluster.loadbalance.RoundRobinLoadBalance;
import com.dream.rest.config.SystemParameterNames;
import com.dream.rest.impl.DefaultRopContext;
import com.dream.rest.marshaller.MessageMarshallerUtils;
import com.dream.rest.request.CollectionsConverter;
import com.dream.rest.request.DateConverter;
import com.dream.rest.request.RopConverter;
import com.dream.rest.request.UploadFile;
import com.dream.rest.request.UploadFileConverter;
import com.dream.rest.response.ErrorResponse;
import com.dream.rest.utils.RopUtils;

/**
 * <pre>
 * 功能说明：
 * 注意事项：所有请求持有共同的RopClient， 改类所有的成员变量都是线程安全。
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class DefaultRopClient implements RopClient {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    //服务地址
    private String serverUrl;
    
  //服务地址
    private String[] serverUrls;

    //应用键
    private String appKey;

    //应用密钥
    private String appSecret;
    
    //会话ID
    private String sessionId;

    //报文格式
    private MessageFormat messageFormat = MessageFormat.xml;
    
    //区域
    private Locale locale = Locale.SIMPLIFIED_CHINESE;

    //通过HttpClient完成通信
    private RestTemplate restTemplate = new RestTemplate();
    
    
    private LoadBalance<String> loadBalance = new  RoundRobinLoadBalance<String>();

    //基于XML的Unmarshaller
    private RopUnmarshaller xmlUnmarshaller = new JaxbXmlRopUnmarshaller();

    //基于JSON的Unmarshaller
    private RopUnmarshaller jsonUnmarshaller = new JacksonJsonRopUnmarshaller();

    //请求类所有请求参数
    private Map<Class<?>, List<Field>> requestAllFields = new HashMap<Class<?>, List<Field>>();

    //请求类所有不需要进行签名的参数
    private Map<Class<?>, List<String>> requestIgnoreSignFieldNames = new HashMap<Class<?>, List<String>>();

    //键为转换的目标类型
    private static Map<Class<?>, RopConverter<String, ?>> ropConverterMap = new HashMap<Class<?>, RopConverter<String, ?>>();
    {
        ropConverterMap.put(UploadFile.class, new UploadFileConverter());
        ropConverterMap.put(Date.class, new DateConverter());
        ropConverterMap.put(Collection.class, new CollectionsConverter());
    }
    
    
    public DefaultRopClient(String serverUrl, String appKey, String appSecret) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public DefaultRopClient(String serverUrl, String appKey, String appSecret, MessageFormat messageFormat) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.messageFormat = messageFormat;
    }

    public DefaultRopClient(String serverUrl, String appKey, String appSecret, MessageFormat messageFormat, Locale locale) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.messageFormat = messageFormat;
        this.locale = locale;
    }
    
    public DefaultRopClient(String[] serverUrls, String appKey, String appSecret, MessageFormat messageFormat, Locale locale) {
    	this.serverUrls = serverUrls;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.messageFormat = messageFormat;
        this.locale = locale;
    }


    public MessageFormat getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(MessageFormat messageFormat) {
        this.messageFormat = messageFormat;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    public RopClient setAppKeyParamName(String paramName) {
        SystemParameterNames.setAppKey(paramName);
        return this;
    }


    public RopClient setSessionIdParamName(String paramName) {
        SystemParameterNames.setSessionId(paramName);
        return this;
    }


    public RopClient setMethodParamName(String paramName) {
        SystemParameterNames.setMethod(paramName);
        return this;
    }


    public RopClient setVersionParamName(String paramName) {
        SystemParameterNames.setVersion(paramName);
        return this;
    }


    public RopClient setFormatParamName(String paramName) {
        SystemParameterNames.setFormat(paramName);
        return this;
    }


    public RopClient setLocaleParamName(String paramName) {
        SystemParameterNames.setLocale(paramName);
        return this;
    }


    public RopClient setSignParamName(String paramName) {
        SystemParameterNames.setSign(paramName);
        return this;
    }


    public void addRopConvertor(RopConverter ropConverter) {
        ropConverterMap.put(ropConverter.getTargetClass(), ropConverter);
    }


    public ClientRequest buildClientRequest() {
        return new DefaultClientRequest(this);
    }

    private class DefaultClientRequest implements ClientRequest {

        private RopClient ropClient;

        private Map<String, String> paramMap = new HashMap<String, String>(20);

        private List<String> ignoreSignParams = new ArrayList<String>();

        private DefaultClientRequest(RopClient ropClient) {
            this.setRopClient(ropClient);
            paramMap.put(SystemParameterNames.getAppKey(), appKey);
            paramMap.put(SystemParameterNames.getFormat(), messageFormat.name());
            paramMap.put(SystemParameterNames.getLocale(), locale.toString());
            if (sessionId != null) {
                paramMap.put(SystemParameterNames.getSessionId(), sessionId);
            }
        }


        public ClientRequest addParam(String paramName, Object paramValue) {
            addParam(paramName,paramValue,false);
            return this;
        }


        public ClientRequest clearParam() {
            paramMap.clear();
            return this;
        }


        public ClientRequest addParam(String paramName, Object paramValue, boolean ignoreSign) {
            Assert.isTrue(paramName != null && paramName.length() > 0, "参数名不能为空");
            Assert.notNull(paramValue, "参数值不能为null");

            //将参数添加到参数列表中
            String valueAsStr = paramValue.toString();
            if (ropConverterMap.containsKey(paramValue.getClass())) {
                RopConverter ropConverter = ropConverterMap.get(paramValue.getClass());
                valueAsStr = (String) ropConverter.unconvert(paramValue);
            }
            paramMap.put(paramName, valueAsStr);

            IgnoreSign typeIgnore = AnnotationUtils.findAnnotation(paramValue.getClass(), IgnoreSign.class);
            if (ignoreSign || typeIgnore != null) {
                ignoreSignParams.add(paramName);
            }
            return this;
        }


        public <T> CompositeResponse post(Class<T> ropResponseClass, String methodName, String version) {
            Map<String, String> requestParams = addOtherParamMap(methodName, version);
            return post(ropResponseClass, requestParams);
        }


        public <T> CompositeResponse post(RopRequest ropRequest, Class<T> ropResponseClass, String methodName, String version) {
            Map<String, String> requestParams = getRequestForm(ropRequest, methodName, version);
            return post(ropResponseClass, requestParams);
        }
        
        

       

        private <T> CompositeResponse post(Class<T> ropResponseClass, Map<String, String> requestParams) {
            String responseContent = restTemplate.postForObject(selectUrl(), toMultiValueMap(requestParams), String.class);
            if (logger.isDebugEnabled()) {
                logger.debug("response:\n" + responseContent);
            }
            return toCompositeResponse(responseContent, ropResponseClass);
        }
        
        private <T> CompositeResponse post(Class<T> ropResponseClass, String ropRequest) {
            String responseContent = restTemplate.postForObject(selectUrl(), ropRequest, String.class);
            if (logger.isDebugEnabled()) {
                logger.debug("response:\n" + responseContent);
            }
            return toCompositeResponse(responseContent, ropResponseClass);
        }


        public <T> CompositeResponse get(Class<T> ropResponseClass, String methodName, String version) {
            Map<String, String> requestParams = addOtherParamMap(methodName, version);
            return get(ropResponseClass, requestParams);
        }


        public <T> CompositeResponse get(RopRequest ropRequest, Class<T> ropResponseClass, String methodName, String version) {
            Map<String, String> requestParams = getRequestForm(ropRequest, methodName, version);
            return get(ropResponseClass, requestParams);
        }

        private <T> CompositeResponse get(Class<T> ropResponseClass, Map<String, String> requestParams) {
            String responseContent = restTemplate.getForObject(buildGetUrl(requestParams), String.class, requestParams);
            if (logger.isDebugEnabled()) {
                logger.debug("response:\n" + responseContent);
            }
            return toCompositeResponse(responseContent, ropResponseClass);
        }

        private Map<String, String> addOtherParamMap(String methodName, String version) {
            paramMap.put(SystemParameterNames.getMethod(), methodName);
            paramMap.put(SystemParameterNames.getVersion(), version);
            String signValue = RopUtils.sign(paramMap, ignoreSignParams, appSecret);
            paramMap.put(SystemParameterNames.getSign(), signValue);
            return paramMap;
        }

        private <T> CompositeResponse toCompositeResponse(String content, Class<T> ropResponseClass) {
            if(logger.isDebugEnabled()){
                logger.debug(content);
            }
            boolean successful = isSuccessful(content);
            DefaultCompositeResponse<T> compositeResponse = new DefaultCompositeResponse<T>(successful);

            if (MessageFormat.json == messageFormat) {
                if (successful) {
                    T ropResponse = jsonUnmarshaller.unmarshaller(content, ropResponseClass);
                    compositeResponse.setSuccessRopResponse(ropResponse);
                } else {
                    ErrorResponse errorResponse = jsonUnmarshaller.unmarshaller(content, ErrorResponse.class);
                    compositeResponse.setErrorResponse(errorResponse);
                }
            } else {
                if (successful) {
                    T ropResponse = xmlUnmarshaller.unmarshaller(content, ropResponseClass);
                    compositeResponse.setSuccessRopResponse(ropResponse);
                } else {
                    ErrorResponse errorResponse = xmlUnmarshaller.unmarshaller(content, ErrorResponse.class);
                    compositeResponse.setErrorResponse(errorResponse);
                }
            }
            return compositeResponse;
        }

        private boolean isSuccessful(String content) {
            return !(content.contains(CommonConstant.ERROR_TOKEN));
        }

        private String buildGetUrl(Map<String, String> form) {
            StringBuilder requestUrl = new StringBuilder();
            requestUrl.append(selectUrl());
            requestUrl.append("?");
            String joinChar = "";
            for (Map.Entry<String, String> entry : form.entrySet()) {
                requestUrl.append(joinChar);
                requestUrl.append(entry.getKey());
                requestUrl.append("=");
                requestUrl.append(entry.getValue());
                joinChar = "&";
            }
            return requestUrl.toString();
        }
        
        private String selectUrl(){
        	StringBuilder requestUrl = new StringBuilder();
        	if(serverUrl!=null){
            	requestUrl.append(serverUrl);
            }else{
            	try{
            		String url = loadBalance.select(serverUrls);
            		requestUrl.append(url);
            	}catch(Exception ex){
            		logger.error("Building the url exception:", ex);
            		throw new RopException(ex);
            	}
            }
        	return requestUrl.toString();
        }
        
        

        private Map<String, String> getRequestForm(RopRequest ropRequest, String methodName, String version) {

            Map<String, String> form = new LinkedHashMap<String, String>(16);

            //系统级参数
            form.put(SystemParameterNames.getAppKey(), appKey);
            form.put(SystemParameterNames.getMethod(), methodName);
            form.put(SystemParameterNames.getVersion(), version);
            form.put(SystemParameterNames.getFormat(), messageFormat.name());
            form.put(SystemParameterNames.getLocale(), locale.toString());
            if (sessionId != null) {
                form.put(SystemParameterNames.getSessionId(), sessionId);
            }

            //业务级参数
            form.putAll(getParamFields(ropRequest, messageFormat));

            //对请求进行签名
            String signValue = sign(ropRequest.getClass(), appSecret, form);
            form.put("sign", signValue);
            return form;
        }

        private MultiValueMap<String, String> toMultiValueMap(Map<String, String> form) {
            MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
            for (Map.Entry<String, String> entry : form.entrySet()) {
                mvm.add(entry.getKey(), entry.getValue());
            }
            return mvm;
        }

        /**
         * 对请求参数进行签名
         *
         * @param ropRequestClass
         * @param appSecret
         * @param form
         * @return
         */
        private String sign(Class<?> ropRequestClass, String appSecret, Map<String, String> form) {
            List<String> ignoreFieldNames = requestIgnoreSignFieldNames.get(ropRequestClass);
            return RopUtils.sign(form, ignoreFieldNames, appSecret);
        }

        /**
         * 获取ropRequest对应的参数名列表
         *
         * @param ropRequest
         * @param mf
         * @return
         */
        private Map<String, String> getParamFields(RopRequest ropRequest, MessageFormat mf) {
            if (!requestAllFields.containsKey(ropRequest.getClass())) {
                parseRopRequestClass(ropRequest);
            }
            return toParamValueMap(ropRequest, mf);
        }

        /**
         * 获取ropRequest对象的对应的参数列表
         *
         * @param ropRequest
         * @param mf
         * @return
         */
        private Map<String, String> toParamValueMap(RopRequest ropRequest, MessageFormat mf) {
            List<Field> fields = requestAllFields.get(ropRequest.getClass());
            Map<String, String> params = new HashMap<String, String>();
            for (Field field : fields) {
                RopConverter convertor = getConvertor(field.getType());
                Object fieldValue = ReflectionUtils.getField(field, ropRequest);
                if (fieldValue != null) {
                    if (convertor != null) {//有对应转换器
                        String strParamValue = (String) convertor.unconvert(fieldValue);
                        params.put(field.getName(), strParamValue);
                    } else if (field.getType().isAnnotationPresent(XmlRootElement.class) ||
                            field.getType().isAnnotationPresent(XmlType.class)) {
                        String message = MessageMarshallerUtils.getMessage(fieldValue, mf);
                        params.put(field.getName(), message);
                    } else {
                        params.put(field.getName(), fieldValue.toString());
                    }
                }
            }
            return params;
        }


		/**
		 * @return the ropClient
		 */
		public RopClient getRopClient() {
			return ropClient;
		}


		/**
		 * @param ropClient the ropClient to set
		 */
		public void setRopClient(RopClient ropClient) {
			this.ropClient = ropClient;
		}


		@Override
		public <T> CompositeResponse post(String ropRequestString,Class<T> ropResponseClass, String methodName, String version) {
			Map<String, String> requestParams = addOtherParamMap(methodName, version);
        	String responseContent = restTemplate.postForObject(selectUrl(), ropRequestString, String.class,requestParams);
            if (logger.isDebugEnabled()) {
                logger.debug("response:\n" + responseContent);
            }
            return toCompositeResponse(responseContent, ropResponseClass);
		}
    }

    private RopConverter getConvertor(Class<?> fieldType) {
        for (Class<?> aClass : ropConverterMap.keySet()) {
            if (ClassUtils.isAssignable(aClass, fieldType)) {
                return ropConverterMap.get(aClass);
            }
        }
        return null;
    }

    private void parseRopRequestClass(RopRequest ropRequest) {
        final ArrayList<Field> allFields = new ArrayList<Field>();
        final List<String> ignoreSignFieldNames = DefaultRopContext.getIgnoreSignFieldNames(ropRequest.getClass());
        ReflectionUtils.doWithFields(ropRequest.getClass(), new ReflectionUtils.FieldCallback() {

            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                ReflectionUtils.makeAccessible(field);
                if (!isTemporaryField(field)) {
                    allFields.add(field);
                }
            }

            private boolean isTemporaryField(Field field) {
                Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                if (declaredAnnotations != null) {
                    for (Annotation declaredAnnotation : declaredAnnotations) {
                        Temporary varTemporary = field.getAnnotation(Temporary.class);
                        if (varTemporary != null) {
                            return true;
                        }
                    }
                }
                return false;
            }
        });

        requestAllFields.put(ropRequest.getClass(), allFields);
        requestIgnoreSignFieldNames.put(ropRequest.getClass(), ignoreSignFieldNames);
    }

    /**
     * 获取ropRequest对应的参数名列表
     *
     * @param ropRequest
     * @param mf
     * @return
     */
    private Map<String, String> getParamFields(RopRequest ropRequest, MessageFormat mf) {
        if (!requestAllFields.containsKey(ropRequest.getClass())) {
            parseRopRequestClass(ropRequest);
        }
        return toParamValueMap(ropRequest, mf);
    }

    /**
     * 获取ropRequest对象的对应的参数列表
     *
     * @param ropRequest
     * @param mf
     * @return
     */
    private Map<String, String> toParamValueMap(RopRequest ropRequest, MessageFormat mf) {
        List<Field> fields = requestAllFields.get(ropRequest.getClass());
        Map<String, String> params = new HashMap<String, String>();
        for (Field field : fields) {
            RopConverter convertor = getConvertor(field.getType());
            Object fieldValue = ReflectionUtils.getField(field, ropRequest);
            if (fieldValue != null) {
                if (convertor != null) {//有对应转换器
                    String strParamValue = (String) convertor.unconvert(fieldValue);
                    params.put(field.getName(), strParamValue);
                } else if (field.getType().isAnnotationPresent(XmlRootElement.class) ||
                        field.getType().isAnnotationPresent(XmlType.class)) {
                    String message = MessageMarshallerUtils.getMessage(fieldValue, mf);
                    params.put(field.getName(), message);
                } else {
                    params.put(field.getName(), fieldValue.toString());
                }
            }
        }
        return params;
    }


}

