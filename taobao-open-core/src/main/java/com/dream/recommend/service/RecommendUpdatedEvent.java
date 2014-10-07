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
public class RecommendUpdatedEvent  extends ApplicationEvent {
	

	public RecommendUpdatedEvent(RecommendStatus source) {
		super(source);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
