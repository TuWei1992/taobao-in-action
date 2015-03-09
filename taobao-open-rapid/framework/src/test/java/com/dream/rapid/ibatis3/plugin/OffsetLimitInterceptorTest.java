package com.dream.rapid.ibatis3.plugin;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.rapid.jdbc.dialect.DerbyDialect;
import com.dream.rapid.jdbc.dialect.Dialect;
import com.dream.rapid.jdbc.dialect.MySQLDialect;
import com.dream.rapid.jdbc.dialect.OracleDialect;
import com.dream.rapid.jdbc.dialect.SQLServerDialect;



public class OffsetLimitInterceptorTest {
	protected   final Logger logger = LoggerFactory.getLogger(getClass());
	
	OffsetLimitInterceptor di = new OffsetLimitInterceptor();
	
	@Test(timeout=4500)
	public void preformance_processIntercept() throws Throwable {
		testWithDialect(new MySQLDialect(), RowBounds.NO_ROW_OFFSET,RowBounds.NO_ROW_LIMIT,"select * from userinfo limit 100,200");
		testWithDialect(new OracleDialect(),RowBounds.NO_ROW_OFFSET,RowBounds.NO_ROW_LIMIT, "select * from ( select row_.*, rownum rownum_ from ( select * from userinfo ) row_ ) where rownum_ <= 100+200 and rownum_ > 100");
		testWithDialect(new SQLServerDialect(),100,RowBounds.NO_ROW_LIMIT, "select top 200 * from userinfo");
		testWithDialect(new DerbyDialect(),100,200, "select * from userinfo ");
	}

	private void testWithDialect(Dialect dialect,int expectedOffset,int expectedLimit, String expctedSql) {
		di.dialect = dialect;
		Configuration conf = new Configuration();
		
		////queryArgs = query(MappedStatement ms, Object parameter, int offset, int limit, ResultHandler resultHandler)
		
		int count = 10000 * 10;
		long start = System.currentTimeMillis();
		for(int i = 0; i < count; i++) {
			Builder builder = new MappedStatement.Builder(conf,null,new StaticSqlSource(conf,"select * from userinfo "),null);
			MappedStatement ms = builder.build();
			Object[] args = new Object[]{ms,new Object(),new RowBounds(100,200),null};
			di.processIntercept(args);
			
			MappedStatement newMs = (MappedStatement)args[0];
			BoundSql sql = newMs.getBoundSql(null);
			RowBounds rowBounds = (RowBounds)args[2];
			int offset = rowBounds.getOffset();
			int limit = rowBounds.getLimit();
			Assert.assertEquals(expectedOffset,offset);
			Assert.assertEquals(expectedLimit,limit);
			Assert.assertEquals(expctedSql,sql.getSql());
		}
		float cost = System.currentTimeMillis() - start;
		
		logger.debug(String.format("costTime:%s perMethodCost:%s tps:%s %s",cost,cost/count,count/(cost/1000),dialect.getClass().getSimpleName()));
	}
}
