package com.dream;

import java.rmi.registry.LocateRegistry;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = { "classpath:/tbo/taobao-common.xml","classpath:/tbo/taobao-dao.xml" })
@TransactionConfiguration(defaultRollback = true)
public  class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {
	public static String APPLICATIONID = "";
	public static String APPLICATIONKEY = "";
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@BeforeClass
	public static void setUpClass() throws Exception {
//		System.setProperty("propertyPath", "E:\\env");
		LocateRegistry.createRegistry(1098);
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
		System.setProperty(Context.PROVIDER_URL, "rmi://localhost:1098");
		
//		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
//		System.setProperty(Context.PROVIDER_URL, "file:///tmp");

		//1.构建一个InitialContext
		InitialContext ctx = new InitialContext();
		
		//2.构建一个Reference，使用基于dbcp的数据源
//		Reference ref = new Reference("javax.sql.DataSource","org.apache.commons.dbcp.BasicDataSourceFactory", null);
//		ref.add(new StringRefAddr("driverClassName","oracle.jdbc.driver.OracleDriver"));
//		ref.add(new StringRefAddr("url","jdbc:oracle:thin:@128.128.97.40:1521:iecdb"));
//		ref.add(new StringRefAddr("username", "CCBUAT"));
//		ref.add(new StringRefAddr("password", "CCBUAT"));
//		jdbc.username=sa
//		jdbc.password=
//		jdbc.url=jdbc:hsqldb:mem:mymemdb
//		jdbc.driver=org.hsqldb.jdbc.JDBCDriver
		Reference ref = new Reference("javax.sql.DataSource","org.apache.commons.dbcp.BasicDataSourceFactory", null);
		ref.add(new StringRefAddr("driverClassName","org.hsqldb.jdbcDriver"));
		ref.add(new StringRefAddr("url","jdbc:hsqldb:mem:mymemdb"));
		ref.add(new StringRefAddr("username", "sa"));
		ref.add(new StringRefAddr("password", ""));
		
		//3.绑定一个JNDI到第2步创建的reference
		ctx.rebind("jdbc/mall", ref);
		ctx.close();
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		InitialContext ic2 = new InitialContext();
		DataSource ds = (DataSource) ic2.lookup("jdbc/mall");
		Connection conn = ds.getConnection();
		conn.close();
	}
	
	
	@Test
	public  void test() throws Exception {
		
		
	}

}
