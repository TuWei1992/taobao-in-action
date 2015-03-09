package com.dream.rapid.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.rapid.page.PageRequest;

public class BaseQuery extends PageRequest implements java.io.Serializable {
	private static final long serialVersionUID = -360860474471966681L;
	public static final int DEFAULT_PAGE_SIZE = 10;
	protected static  final Logger logger = LoggerFactory.getLogger(BaseQuery.class);
    static {
        logger.debug("BaseQuery.DEFAULT_PAGE_SIZE="+DEFAULT_PAGE_SIZE);
    }
    
	public BaseQuery() {
		setPageSize(DEFAULT_PAGE_SIZE);
	}
	  
}
