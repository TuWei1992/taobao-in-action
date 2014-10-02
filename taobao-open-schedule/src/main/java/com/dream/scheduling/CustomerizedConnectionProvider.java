package com.dream.scheduling;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.quartz.utils.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jndi.JndiTemplate;
/**
 * Get the data source from Spring context.
 * @author Frank
 *
 */
public class CustomerizedConnectionProvider implements ConnectionProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerizedConnectionProvider.class);
	
	
	
	private final static String JNDI_NAME_TOMCAT="java:comp/env/jdbc/mall";
	private final static String JNDI_NAME_WEBLOGIC="jdbc/mall";
	
	private DataSource dataSource = null;
	
	public CustomerizedConnectionProvider(){
		JndiTemplate jndiTemplate = new JndiTemplate();
		try {
			this.dataSource = (DataSource) jndiTemplate.lookup(JNDI_NAME_TOMCAT,DataSource.class);
		} catch (NamingException e) {
			if(this.dataSource==null){
				try {
					this.dataSource = (DataSource) jndiTemplate.lookup(JNDI_NAME_WEBLOGIC,DataSource.class);
				} catch (NamingException ee) {
					logger.error("Fuck!!!Why could not get the data source with "+JNDI_NAME_WEBLOGIC, ee);
				}
			}
		}
	}
	
	

	@Override
	public Connection getConnection() throws SQLException {
		if(logger.isInfoEnabled()){
			logger.info("We will get the connection at {}", System.currentTimeMillis());
		}
		if(dataSource!=null){
			if(logger.isInfoEnabled()){
				logger.info("Yes,get the connection when you see me at {}", System.currentTimeMillis());
			}
			return dataSource.getConnection();
		}
		if(logger.isInfoEnabled()){
			logger.info("No!!!cant not get the connection for the data source is null at {}", System.currentTimeMillis());
		}
		return null;
	}

	@Override
	public void shutdown() throws SQLException {
		if(logger.isInfoEnabled()){
			logger.info("Nothing to do when the schedule is shutdown at {}", System.currentTimeMillis());
		}
	}

}
