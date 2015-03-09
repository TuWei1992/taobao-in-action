package com.dream.messaging.connector;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.dream.messaging.connector.MessagingDataConnector;


@ContextConfiguration(locations={"classpath:/tbo/taobao-message.xml"})
public class MessagingDataConnectorTest extends AbstractJUnit4SpringContextTests {
	
	private static final Logger logger = Logger.getLogger(MessagingDataConnectorTest.class);
	
	private MessagingDataConnector connector;
	
	
	 @Before 
	 public void initialize() {
		 connector = (MessagingDataConnector) this.applicationContext.getBean("messagingDataConnector");
	 }

	
	@Test
	public void executeRequest() {
		
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		Object domain = new com.dream.messaging.connector.TDpCorpStmtInqRq();
		String sourceSystemId = "a", transCode = "T24",targetSystemId = "b";
		Object response = connector.executeRequest(domain, sourceSystemId, transCode, targetSystemId);
		logger.debug(response);
	}
	
	
	@Test
	public void executeRequestFixformat() {
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		TIA209813 domain = new TIA209813();
		TIA tia = new TIA();
		tia.setCICSTranCode("1000");
		tia.setTranCode(9813);
		tia.setAppCode(20);
		tia.setFrontEndTranCode(209813);
		domain.setId("123456");
		domain.setTia(tia);
		
		String sourceSystemId = "a", transCode = "209813", targetSystemId = "b";
		Object response = connector.executeRequest(domain, sourceSystemId, transCode, targetSystemId);
		logger.debug(response);
	}
	
	@Test
	public void executeRequestLV() {
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		TIA209813 domain = new TIA209813();
		TIA tia = new TIA();
		tia.setCICSTranCode("1000");
		tia.setTranCode(9813);
		tia.setAppCode(20);
		tia.setFrontEndTranCode(209813);
		domain.setId("123456");
		domain.setTia(tia);
		
		String sourceSystemId = "a", transCode = "209813", targetSystemId = "b";
		Object response = connector.executeRequest(domain, sourceSystemId, transCode, targetSystemId);
		logger.debug(response);
	}
	
	@Test
	public void executeRequestXML() {
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		TIA209813 domain = new TIA209813();
		TIA tia = new TIA();
		tia.setCICSTranCode("1000");
		tia.setTranCode(9813);
		tia.setAppCode(20);
		tia.setFrontEndTranCode(209813);
		domain.setId("123456");
		domain.setTia(tia);
		
		String sourceSystemId = "a", transCode = "209813", targetSystemId = "b";
		Object response = connector.executeRequest(domain, sourceSystemId, transCode, targetSystemId);
		logger.debug(response);
	}
	
	@Test
	public void executeRequestJSON() {
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		TIA209813 domain = new TIA209813();
		TIA tia = new TIA();
		tia.setCICSTranCode("1000");
		tia.setTranCode(9813);
		tia.setAppCode(20);
		tia.setFrontEndTranCode(209813);
		domain.setId("123456");
		domain.setTia(tia);
		
		String sourceSystemId = "a", transCode = "209813", targetSystemId = "b";
		
		connector = (MessagingDataConnector) this.applicationContext.getBean("jsonMessagingDataConnector");
		
		Object response = connector.executeRequest(domain, sourceSystemId, transCode, targetSystemId);
		logger.debug(response);
	}
	
	@Test
	public void executeRequestCSV() {
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		TIA209813 domain = new TIA209813();
		TIA tia = new TIA();
		tia.setCICSTranCode("1000");
		tia.setTranCode(9813);
		tia.setAppCode(20);
		tia.setFrontEndTranCode(209813);
		domain.setId("123456");
		domain.setTia(tia);
		
		String sourceSystemId = "a", transCode = "209813", targetSystemId = "b";
		Object response = connector.executeRequest(domain, sourceSystemId, transCode, targetSystemId);
		logger.debug(response);
	}
	
	@Test
	public void executeRequestRA() {
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		TIA209813 domain = new TIA209813();
		TIA tia = new TIA();
		tia.setCICSTranCode("1000");
		tia.setTranCode(9813);
		tia.setAppCode(20);
		tia.setFrontEndTranCode(209813);
		domain.setId("123456");
		domain.setTia(tia);
		
		String sourceSystemId = "a", transCode = "209813", targetSystemId = "b";
		Object response = connector.executeRequest(domain, sourceSystemId, transCode, targetSystemId);
		logger.debug(response);
	}


}
