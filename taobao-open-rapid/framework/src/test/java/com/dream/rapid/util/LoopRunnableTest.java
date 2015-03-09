package com.dream.rapid.util;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoopRunnableTest {
	protected   final Logger logger = LoggerFactory.getLogger(getClass());
	public int count = 0;
	@Test
	public void test_all_in_one() throws InterruptedException {
		LoopRunnable loopRunnable = new LoopRunnable(new Runnable() {
			public void run() {
				count ++;
//				logger.debug(new Timestamp(System.currentTimeMillis()));
			}
		});
		loopRunnable.setSleepInterval(100);
		
		Thread t = new Thread(loopRunnable);
		Assert.assertFalse(loopRunnable.isRunning());
		t.start();
		
		Thread.sleep(1000);
		Assert.assertTrue(loopRunnable.isRunning());
		
		Assert.assertFalse(loopRunnable.isPaused());
		loopRunnable.pause();
		int pauseBeforeCount = count;
		Assert.assertTrue(loopRunnable.isPaused());
		
		Thread.sleep(1000);
		Assert.assertEquals(pauseBeforeCount, count);
		
		Assert.assertTrue(loopRunnable.isPaused());
		loopRunnable.resume();
		Assert.assertFalse(loopRunnable.isPaused());
		
		Thread.sleep(1000);
		Assert.assertTrue(count > pauseBeforeCount);
		
		loopRunnable.shutdown();
		Assert.assertFalse(loopRunnable.isRunning());
		
		Thread.sleep(1000);
		Assert.assertFalse(t.isAlive());
	}
}
