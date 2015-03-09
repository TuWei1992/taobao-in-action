package com.dream.rapid.jdbc.dialect;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;
public class OracleDialectTest {
	protected   final Logger logger = LoggerFactory.getLogger(getClass());
	OracleDialect dialect = new OracleDialect();
	@Test
	public void test() {
		String sql = dialect.getLimitString("select * from user", 10, 100);
		assertEquals("select * from ( select row_.*, rownum rownum_ from ( select * from user ) row_ ) where rownum_ <= 10+100 and rownum_ > 10",sql);
		logger.debug(sql);
		
		String sql0limit = dialect.getLimitString("select * from user", 0, 100);
		assertEquals("select * from ( select * from user ) where rownum <= 100",sql0limit);
		logger.debug(sql0limit);
		
		String forUpdateSql = dialect.getLimitString("select * from user for update", 10, 100);
		assertEquals("select * from ( select row_.*, rownum rownum_ from ( select * from user ) row_ ) where rownum_ <= 10+100 and rownum_ > 10 for update",forUpdateSql);
		logger.debug(forUpdateSql);
	}
	@Test
	public void getLimitStringWithPlaceHolader() {
		String OFFSET = ":offset";
		String LIMIT = ":limit";
		Assert.assertEquals("select * from ( select * from user ) where rownum <= :limit", dialect.getLimitString("select * from user", 0,OFFSET, 0,LIMIT));
		Assert.assertEquals("select * from ( select * from user ) where rownum <= :limit", dialect.getLimitString("select * from user", 0,OFFSET,12,LIMIT));
		Assert.assertEquals("select * from ( select row_.*, rownum rownum_ from ( select * from user ) row_ ) where rownum_ <= :offset+:limit and rownum_ > :offset", dialect.getLimitString("select * from user", 12, OFFSET,0,LIMIT));
		Assert.assertEquals("select * from ( select row_.*, rownum rownum_ from ( select * from user ) row_ ) where rownum_ <= :offset+:limit and rownum_ > :offset", dialect.getLimitString("select * from user", 12, OFFSET, 34,LIMIT));
	}
	
	@Test
	public void getLimitString() {
		String sql = new OracleDialect().getLimitString("select * from user", 10, 100);
		assertEquals("select * from ( select row_.*, rownum rownum_ from ( select * from user ) row_ ) where rownum_ <= 10+100 and rownum_ > 10",sql);
	}
}
