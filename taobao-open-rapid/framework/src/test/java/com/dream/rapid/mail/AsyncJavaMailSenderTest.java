package com.dream.rapid.mail;

import junit.framework.TestCase;

public class AsyncJavaMailSenderTest extends TestCase {
//	AsyncJavaMailSender asyncMailSender;
//	
//	public void setUp()throws Exception {
//		ApplicationContext context = new ClassPathXmlApplicationContext("fortest_spring/applicationContext-mail.xml");
//		asyncMailSender = (AsyncJavaMailSender)context.getBean("asyncJavaMailSender");
//	}
//	
//	public void tearDown() throws Exception{
//	}
//	
//	boolean executedSuccess = false;
//	boolean executedFail = false;
//	public void testSend() throws Exception {
//		SimpleMailMessage msg = new SimpleMailMessage();
//		msg.setTo("badqiu@gmail.com");
//		msg.setFrom("rapidframework@sohu.com");
//		msg.setText("hello: badqiu, <h1>2008</h1>");
//		msg.setSubject("test subject");
//		
//		AsyncToken token = asyncMailSender.send(msg);
//		
//		token.addResponder(new IResponder() {
//			public void onFault(Exception e) {
//				System.out.println("send email fail,cause:"+e);
//				e.printStackTrace();
//				executedFail = true;
//			}
//
//			public void onResult(Object result) {
//				System.out.println("send email success");
//				executedSuccess = true;
//			}
//		});
//		
//		token.waitForResult();
//		Thread.sleep(100);
//		assertTrue(executedSuccess);
//		assertFalse(executedFail);
//	}
//	

}