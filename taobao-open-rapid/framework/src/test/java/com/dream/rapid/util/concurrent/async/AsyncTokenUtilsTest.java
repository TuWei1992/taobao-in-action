package com.dream.rapid.util.concurrent.async;

import java.util.Date;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;
import com.dream.rapid.util.concurrent.async.AsyncTokenRunnable;

public class AsyncTokenUtilsTest extends TestCase {
	
	protected   final Logger logger = LoggerFactory.getLogger(getClass());
	private Object RESULT = new Object();
	private boolean executedResult = false;
	
	public void testCallable() throws InterruptedException {
		final AsyncToken token = new AsyncToken();
		
		Callable task = new Callable() {
			public Object call() throws Exception {
				Thread.sleep(1000 * 3);
				return RESULT;
			}
		};
		
		Thread thread = new Thread(new AsyncTokenRunnable(token,task));
		thread.start();
		
		Thread.sleep(1500);
		
		token.addResponder(new IResponder() {
			public void onFault(Exception fault) {
				assertNull(fault);
			}
			public void onResult(Object result) {
				assertEquals(result,result);
				executedResult = true;
			}
		});
		
		assertFalse(executedResult);
		
		Thread.sleep(2000 + 500);
		
		assertTrue(executedResult);
	}
	
	public void testRunable() {
		AsyncToken<Date> token = new AsyncToken();
		token.addResponder(new IResponder<Date>(){
			public void onFault(Exception fault) {
				
			}

			public void onResult(Date result) {
				
			}
		});
		
		token.setComplete();
		
		int count = Integer.MAX_VALUE;
//		logger.debug(count);
//		logger.debug(++count);
	}
	
	public void testSendEmail() {
		final String address = "badqiu(a)gmail.com";
		final String subject = "test";
		final String content = "async token test";
		
		//返回的token,包含token.addResponder()用于监听异步方法的执行结果
		AsyncToken token = sendAsyncEmail(address,subject,content);
		
		//token可以继续传递给外部,以便外面感兴趣的listener监听这个异步方法的执行结果
		token.addResponder(new IResponder() {
			public void onFault(Exception fault) {
				logger.debug("email send fail,cause:"+fault);
				//此处可以直接引用address,subject,content,如,我们可以再次发送一次
				sendAsyncEmail(address,subject,content);
			}
			public void onResult(Object result) {
				logger.debug("email send success,result:"+result);
			}
		});
	}
	
	public AsyncToken sendAsyncEmail(String address,String subject,String content) {
		final AsyncToken token = new AsyncToken();
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					//do send email job...
					token.setComplete(); //通知Responder token执行完
				}catch(Exception e) {
					token.setFault(e); //通知Responder token发生错误
				}
			}
		});
		thread.start();
		
		return token;
	}
}
