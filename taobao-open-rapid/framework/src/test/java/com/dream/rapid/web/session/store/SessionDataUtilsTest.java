package com.dream.rapid.web.session.store;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class SessionDataUtilsTest {
	
	protected   final Logger logger = LoggerFactory.getLogger(getClass());
	@Test
	public void encode() throws UnsupportedEncodingException {
		Map map = new HashMap();
		map.put("empty", "");
		map.put("blank", "  ");
		map.put("null", null);
		map.put("abc", "abc");
		String data = JdbcSessionStore.encode(map);
		logger.debug(data);
		
		Map decodeMap = JdbcSessionStore.decode(data);
//		logger.debug(decodeMap.get("null"));
		
		assertEquals(decodeMap.get("empty"),"");
		assertEquals(decodeMap.get("blank"),"  ");
		assertEquals(decodeMap.get("abc"),"abc");
		assertEquals(null,decodeMap.get("null"));
//		assertTrue(decodeMap.get("null") == null);
	}
}
