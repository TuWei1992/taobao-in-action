/*******************************************************************************
 * Copyright (c) 2005, 2014 com.dream
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dream.dashboard.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.rapid.base.BaseController;
import com.dream.recommend.service.RecommendModeSettingService;
import com.dream.recommend.service.RecommendScopeCategoryService;
import com.dream.recommend.service.RecommendScopeSettingService;
import com.dream.recommend.service.RecommendSpecifiedService;
import com.dream.recommend.service.RecommendStatusService;
import com.dream.shop.service.ShopCategoryService;

/**
 * 自动推荐向导页面
 * 
 * 
 * 
 * @author Frank
 */

@Controller
@RequestMapping(value = "/wizard")
public class WizardController extends BaseController {
	
	@Autowired
	private RecommendStatusService recommendStatusService;
	@Autowired
	private RecommendScopeSettingService recommendScopeSettingService;
	@Autowired
	private RecommendScopeCategoryService recommendScopeCategoryService;
	@Autowired
	private RecommendModeSettingService recommendModeSettingService;
	@Autowired
	private RecommendSpecifiedService recommendSpecifiedService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	
	
	
	public void setRecommendStatusService(
			RecommendStatusService recommendStatusService) {
		this.recommendStatusService = recommendStatusService;
	}

	public void setRecommendScopeSettingService(
			RecommendScopeSettingService recommendScopeSettingService) {
		this.recommendScopeSettingService = recommendScopeSettingService;
	}

	public void setRecommendScopeCategoryService(
			RecommendScopeCategoryService recommendScopeCategoryService) {
		this.recommendScopeCategoryService = recommendScopeCategoryService;
	}

	public void setRecommendModeSettingService(
			RecommendModeSettingService recommendModeSettingService) {
		this.recommendModeSettingService = recommendModeSettingService;
	}

	public void setRecommendSpecifiedService(
			RecommendSpecifiedService recommendSpecifiedService) {
		this.recommendSpecifiedService = recommendSpecifiedService;
	}
	
	public void setShopCategoryService(
			ShopCategoryService shopCategoryService) {
		this.shopCategoryService = shopCategoryService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String wizard ( Model model )  throws Exception {
		return "wizard";
	}

}
