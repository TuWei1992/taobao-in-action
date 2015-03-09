package com.dream.basic;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YiWei {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * ~:取反运算
	 * >>>:逻辑右移位，左边空位补齐0
	 * >>: 算数右移位，左边空位补齐符号，负的就填1 正的就填0
	 * ^:异或运算符号,1^0=1, 1^1=0, 0^0=0
	 * &:与
	 * 
	 */
	@Test
	public void right() {
		int a = 54;
		
		logger.debug(a+"");
		logger.debug(Integer.toBinaryString(a));
		logger.debug("***********************");
//		logger.debug(a>>>2);
		logger.debug(Integer.toBinaryString(a>>>2));
		logger.debug("***********************");
//		logger.debug(a>>2);
		logger.debug(Integer.toBinaryString(a>>2));
		logger.debug("***********************");
		
		
		
		//负数以其正值的补码形式表达（即：原码[正值]的反码+1）
		
		int b = ~54;
		
		
		
//		logger.debug(b);
		logger.debug(Integer.toBinaryString(b));
		logger.debug("***********************");
//		logger.debug(b>>>2);
		logger.debug(Integer.toBinaryString(b>>>2));
		logger.debug("***********************");
//		logger.debug(b>>2);
		logger.debug(Integer.toBinaryString(b>>2));
		
//		logger.debug(1^0);
//		logger.debug(1&0);
		
//		logger.debug(1234567&5);
		
		
		Map map = new HashMap();
		
		map.put("1", "2");
		map.put("2", "2");
		
		map.get("1");
		
		
		
		Map concurrent = new ConcurrentHashMap();
		
		concurrent.put("1", "2");
		concurrent.put("2", "2");
		
		concurrent.get("1");
	}
	
	
	@Test
	public void weak(){
		
//		logger.debug(Runtime.getRuntime().maxMemory()/(1024*1024));
//		logger.debug(Runtime.getRuntime().freeMemory()/(1024*1024));
		
		WeakHashMap<String,byte[]> cache = new WeakHashMap<String,byte[]>();
		
		for(int i = 0; i< 10 ;i++){
//			if(i!=0){
//				cache.get(Integer.toString(i-1));
//			}
			byte[] b = new byte[1024*1024*256];
			cache.put(Integer.toString(i), b);
//			logger.debug(Runtime.getRuntime().maxMemory()/(1024*1024));
//			logger.debug(Runtime.getRuntime().freeMemory()/(1024*1024));
		}
		
		
//		logger.debug(Runtime.getRuntime().maxMemory()/(1024*1024));
//		logger.debug(Runtime.getRuntime().freeMemory()/(1024*1024));
	}
	
	
	@Test
	public void reference(){
		
//		logger.debug(Runtime.getRuntime().maxMemory()/(1024*1024));
//		logger.debug(Runtime.getRuntime().freeMemory()/(1024*1024));
		
		Map<String,SoftReference<byte[]>> cache = new HashMap<String,SoftReference<byte[]>>();
		
		for(int i = 0; i< 10 ;i++){
			byte[] b = new byte[1024*1024*256];
			SoftReference<byte[]> sf = new SoftReference<byte[]>(b);
			cache.put(Integer.toString(i), sf);
//			logger.debug(Runtime.getRuntime().maxMemory()/(1024*1024));
//			logger.debug(Runtime.getRuntime().freeMemory()/(1024*1024));
		}
		
		for(int i = 0; i< 10 ;i++){
			SoftReference<byte[]> sf = cache.get(Integer.toString(i));
			byte[] by = sf.get();
//			logger.debug(by);
		}
		
		
//		logger.debug(Runtime.getRuntime().maxMemory()/(1024*1024));
//		logger.debug(Runtime.getRuntime().freeMemory()/(1024*1024));
	}
	

	@Test
	public void linked(){
		
		LinkedHashMap<String,String> cache = new LinkedHashMap<String,String>(32,(float) 0.75,true);
		
		for(int i = 0; i< 20 ;i++){
			cache.put(Integer.toString(i), Integer.toString(i));
		}
		cache.get(18);
		cache.get(15);
		cache.get(16);
		
		
		for(Iterator itr = cache.entrySet().iterator();itr.hasNext();){
			Map.Entry<String,String> entry = (Entry<String, String>) itr.next();
			logger.debug(entry.getKey());
			logger.debug(entry.getValue());
			logger.debug("******************");
		}
		
		
	}
	
	
	@Test
	public void linked1(){
		
		LinkedHashMap<String,String> cache = new LinkedHashMap<String,String>(32,(float) 0.75,true);
		
		for(int i = 0; i< 20 ;i++){
			cache.put(Integer.toString(i), Integer.toString(i));
		}
		cache.get(Integer.toString(18));
		cache.get(Integer.toString(15));
		cache.get(Integer.toString(16));
		
//		logger.debug(cache.size());
		
		
		for(Iterator itr = cache.entrySet().iterator();itr.hasNext();){
			Map.Entry<String,String> entry = (Entry<String, String>) itr.next();
			logger.debug(entry.getKey());
			logger.debug(entry.getValue());
			logger.debug("******************");
		}
		
		
	}
}
