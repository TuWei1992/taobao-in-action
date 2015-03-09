package com.dream.rest.sample; /**
 *  
 * 12-5-25
 */

import com.dream.rest.security.AppSecretManager;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class SampleAppSecretManager implements AppSecretManager {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
    private static Map<String, String> appKeySecretMap = new HashMap<String, String>();

    static {
        appKeySecretMap.put("00001", "abcdeabcdeabcdeabcdeabcde");
        appKeySecretMap.put("00002","abcdeabcdeabcdeabcdeaaaaa");
        appKeySecretMap.put("00003","abcdeabcdeabcdeabcdeaaaaa");
    }


    public String getSecret(String appKey) {
        logger.debug("use SampleAppSecretManager!");
        return appKeySecretMap.get(appKey);
    }


    public boolean isValidAppKey(String appKey) {
        return getSecret(appKey) != null;
    }
}

