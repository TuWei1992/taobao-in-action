/*******************************************************************************
 * Copyright (c) 2005, 2014 com.dream
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dream.dashboard.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.rapid.base.BaseController;
import com.taobao.api.ApiException;
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
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String dashboard ( Model model )  throws Exception {
		String result = "dashboard";
		
		
		model.addAttribute("oauth", getOAuth());
		model.addAttribute("lastest", getLastest10Auths());
		model.addAttribute("store", getStore().getShop());
		model.addAttribute("seller", getSeller().getUser());
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
	
	
	
	
	private UserSellerGetResponse getSeller() throws ApiException{
		UserSellerGetRequest req=new UserSellerGetRequest();
		req.setFields("user_id,nick,sex,seller_credit,type,has_more_pic,item_img_num,item_img_size,prop_img_num,prop_img_size,auto_repost,promoted_type,status,alipay_bind,consumer_protection,avatar,liangpin,sign_food_seller_promise,has_shop,is_lightning_consignment,has_sub_stock,is_golden_seller,vip_info,magazine_subscribe,vertical_market,online_gaming");
		UserSellerGetResponse resp =  (UserSellerGetResponse) getTaobaoResponse(req,getOAuth().getAccessToken());
		return  resp;
	}
	
	private ShopGetResponse getStore() throws ApiException{
		ShopGetRequest req=new ShopGetRequest();
		req.setFields("sid,cid,title,nick,desc,bulletin,pic_path,created,modified");
		req.setNick(getOAuth().getTaobaoUserNick());
		ShopGetResponse response = (ShopGetResponse) getTaobaoResponse(req);
		return response;
	}
	
	private void getShowcases() throws ApiException{
		ShopRemainshowcaseGetRequest req=new ShopRemainshowcaseGetRequest();
		ShopRemainshowcaseGetResponse resp =  (ShopRemainshowcaseGetResponse) getTaobaoResponse(req,getOAuth().getAccessToken());
	}
	
	
	
	
	


}
