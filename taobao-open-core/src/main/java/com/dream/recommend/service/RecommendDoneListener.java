/**
 * 
 */
package com.dream.recommend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.dream.recommend.model.RecommendStatus;


/**
 * @author Frank
 *
 */
@Component
public class RecommendDoneListener  implements ApplicationListener<RecommendDoneEvent> {
	
	@Autowired
	private RecommendStatusService recommendStatusService; 
	
	@Override
	public void onApplicationEvent(RecommendDoneEvent event) {
		RecommendStatus status = (RecommendStatus) event.getSource();
		status.setIsRecommended("1");
		this.recommendStatusService.updateSelective(status);
	}

	public RecommendStatusService getRecommendStatusService() {
		return recommendStatusService;
	}

	public void setRecommendStatusService(RecommendStatusService recommendStatusService) {
		this.recommendStatusService = recommendStatusService;
	}
	
	
}
