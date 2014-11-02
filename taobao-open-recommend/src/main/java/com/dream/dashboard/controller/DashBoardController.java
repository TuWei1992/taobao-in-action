/*******************************************************************************
 * Copyright (c) 2005, 2014 com.dream
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dream.dashboard.controller;


import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.item.model.ItemCriteria;
import com.dream.item.service.ItemService;
import com.dream.rapid.base.BaseController;
import com.dream.recommend.model.RecommendModeSetting;
import com.dream.recommend.model.RecommendScopeCategory;
import com.dream.recommend.model.RecommendScopeSetting;
import com.dream.recommend.model.RecommendSpecifiedCriteria;
import com.dream.recommend.model.RecommendStatus;
import com.dream.recommend.service.RecommendModeSettingService;
import com.dream.recommend.service.RecommendScopeCategoryService;
import com.dream.recommend.service.RecommendScopeSettingService;
import com.dream.recommend.service.RecommendSpecifiedService;
import com.dream.recommend.service.RecommendStatusService;
import com.dream.shop.model.ShopCategory;
import com.dream.shop.model.ShopCategoryCriteria;
import com.dream.shop.service.ShopCategoryService;
import com.taobao.api.ApiException;
import com.taobao.api.domain.Shop;
import com.taobao.api.domain.User;
import com.taobao.api.request.ShopGetRequest;
import com.taobao.api.request.ShopRemainshowcaseGetRequest;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.response.ShopGetResponse;
import com.taobao.api.response.ShopRemainshowcaseGetResponse;
import com.taobao.api.response.UserSellerGetResponse;

/**
 * ItemController负责打开商品列表和商品详情页面，
 * 
 * 
 * 
 * @author Frank
 */

@Controller
@RequestMapping(value = "/dashboard")
public class DashBoardController extends BaseController {
	
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
	public String dashboard ( Model model )  throws Exception {
		String result = "dashboard";
		model.addAttribute("oauth", getOAuth());
		model.addAttribute("lastest", getLastest10Auths());
		model.addAttribute("store", getStore());
		model.addAttribute("seller", getSeller());
		model.addAttribute("showcases", getShowcases());
		model.addAttribute("onsales", queryOnSaleItems());
		model.addAttribute("instocks", queryInventoryItems());
		Long shopId = getShop().getSid();
		model.addAttribute("status", recommendStatusService.queryById(new RecommendStatus(shopId)));
		RecommendScopeSetting scopeSetting = recommendScopeSettingService.queryById(new RecommendScopeSetting(shopId));
		model.addAttribute("scopeSetting",scopeSetting);
		if("1".equals(scopeSetting.getScopeType())){
			model.addAttribute("scopeCategory",recommendScopeCategoryService.queryById(new RecommendScopeCategory(shopId)));
		}
		model.addAttribute("modeSetting", recommendModeSettingService.queryById(new RecommendModeSetting(shopId)));
		RecommendSpecifiedCriteria rsc = new RecommendSpecifiedCriteria();
		rsc.createCriteria().andShopIdEqualTo(shopId).andTypeEqualTo("1");
		model.addAttribute("mustRecommend", recommendSpecifiedService.queryAllByCriteria(rsc));
		rsc.clear();
		rsc.createCriteria().andShopIdEqualTo(shopId).andTypeEqualTo("0");
		model.addAttribute("notRecommend", recommendSpecifiedService.queryAllByCriteria(rsc));
		
		
		List<ShopCategory> shopCats = getShopCategories();
		if(shopCats==null || shopCats.isEmpty()){
			model.addAttribute("shopcats","{}");
		}else{
			ObjectMapper objectMapper =  new ObjectMapper();
			String json = objectMapper.writeValueAsString(shopCats);
			model.addAttribute("shopcats",json);
		}
		
		return result;
	}
	
	private List getLastest10Auths(){
		List result = null;
		return result;
	}
	
	private List getOnSellProducts(){
		List result = null;
		return result;
	}
	
	
	
	private List getUnReadComments(){
		List result = null;
		return result;
	}
	
	private List getUnHandledOrders(){
		List result = null;
		return result;
	}

	
	private List getNewTasks(){
		List result = null;
		return result;
	}
	
	private List getOthers(){
		List result = null;
		return result;
	}
	
	
	private List getRatios(){
		List result = null;
		return result;
	}
	
	
	private List<ShopCategory> getShopCategories(){
		ShopCategoryCriteria criteria = new ShopCategoryCriteria();
		criteria.createCriteria().andShopIdEqualTo(getShop().getSid());
		List<ShopCategory> shopCats = shopCategoryService.queryAllByCriteria(criteria );
		return shopCats;
	}
	
	
	
	
	private User getSeller() throws ApiException{
		UserSellerGetRequest req=new UserSellerGetRequest();
		req.setFields("user_id,nick,sex,seller_credit,type,has_more_pic,item_img_num,item_img_size,prop_img_num,prop_img_size,auto_repost,promoted_type,status,alipay_bind,consumer_protection,avatar,liangpin,sign_food_seller_promise,has_shop,is_lightning_consignment,has_sub_stock,is_golden_seller,vip_info,magazine_subscribe,vertical_market,online_gaming");
		UserSellerGetResponse resp =  (UserSellerGetResponse) getTaobaoResponse(req,getOAuth().getAccessToken());
		return  resp.getUser();
	}
	
	private Shop getStore() throws ApiException{
		ShopGetRequest req=new ShopGetRequest();
		req.setFields("sid,cid,title,nick,desc,bulletin,pic_path,created,modified");
		req.setNick(getOAuth().getTaobaoUserNick());
		ShopGetResponse response = (ShopGetResponse) getTaobaoResponse(req);
		return response.getShop();
	}
	
	
	
	
	@Autowired
	private ItemService itemService ;
	
	public void setItemService(ItemService service) {
		this.itemService = service;
	}
	
	private List queryOnSaleItems(){
		ItemCriteria criteria = new ItemCriteria();
		criteria.createCriteria().andNickEqualTo(getOAuth().getTaobaoUserNick()).andApproveStatusEqualTo("onsale");
		return itemService.queryAllByCriteria(criteria);
	}
	
	private List queryInventoryItems(){
		ItemCriteria criteria = new ItemCriteria();
		criteria.createCriteria().andNickEqualTo(getOAuth().getTaobaoUserNick()).andApproveStatusEqualTo("instock");
		return itemService.queryAllByCriteria(criteria);
	}
	
	//taobao.shop.remainshowcase.get 
	private Shop getShowcases() throws ApiException{
		ShopRemainshowcaseGetRequest request = new ShopRemainshowcaseGetRequest();
		ShopRemainshowcaseGetResponse response = (ShopRemainshowcaseGetResponse) getTaobaoResponse(request, getOAuth().getAccessToken()); 
		return response.getShop();
	}
	
	
	
	


}
