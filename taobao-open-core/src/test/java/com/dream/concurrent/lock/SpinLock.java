package com.dream.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁示例
 * @author Frank
 * http://ifeve.com/java-concurrency-thread-directory/
 */
public class SpinLock {

	  private AtomicReference<Thread> sign =new AtomicReference<Thread>();
	  /**
	   * 
	   */
	  public void lock(){
	    Thread current = Thread.currentThread();
	    while(!sign .compareAndSet(null, current)){
	    }
	  }
	  /**
	   * 
	   */
	  public void unlock (){
	    Thread current = Thread.currentThread();
	    sign .compareAndSet(current, null);
	  }
	}