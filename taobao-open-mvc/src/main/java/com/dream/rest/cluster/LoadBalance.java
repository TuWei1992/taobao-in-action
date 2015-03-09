package com.dream.rest.cluster;

import java.util.List;

public interface LoadBalance<T> {
	
	
	/**
	 * select one invoker in list.
	 * 
	 * @param invokers invokers.
	 * @param url refer url
	 * @param invocation invocation.
	 * @return selected invoker.
	 */
	T select(List<T> invokers) throws Exception;
	
	
	/**
	 * select one invoker in list.
	 * 
	 * @param invokers invokers.
	 * @param url refer url
	 * @param invocation invocation.
	 * @return selected invoker.
	 */
	T select(T[] invokers) throws Exception;

}
