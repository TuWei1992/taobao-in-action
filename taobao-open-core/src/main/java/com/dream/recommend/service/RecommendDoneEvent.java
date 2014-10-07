/**
 * 
 */
package com.dream.recommend.service;

import org.springframework.context.ApplicationEvent;

import com.dream.recommend.model.RecommendStatus;

/**
 * @author Frank
 *
 */
public class RecommendDoneEvent  extends ApplicationEvent {
	

	public RecommendDoneEvent(RecommendStatus source) {
		super(source);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
