/**
 * 
 */
package com.dream.recommend.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.dream.recommend.model.RecommendStatus;


/**
 * @author Frank
 *
 */
@Component
public class RecommendUpdatedListener  implements ApplicationListener<RecommendUpdatedEvent> {
	
	@Autowired
	private RecommendStatusService recommendStatusService; 
	
	@Override
	public void onApplicationEvent(RecommendUpdatedEvent event) {
		RecommendStatus status = (RecommendStatus) event.getSource();
		status.setIsRecommended("0");
		status.setUpdatedTime(new Date());
		this.recommendStatusService.updateSelective(status);
	}

	public RecommendStatusService getRecommendStatusService() {
		return recommendStatusService;
	}

	public void setRecommendStatusService(RecommendStatusService recommendStatusService) {
		this.recommendStatusService = recommendStatusService;
	}
	
	
}
