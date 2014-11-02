/*******************************************************************************
 * Copyright (c) 2005, 2014 com.dream
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dream.index.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dream.auth.model.Auth;
import com.dream.auth.model.AuthCriteria;
import com.dream.auth.service.AuthService;
import com.dream.item.service.ItemService;
import com.dream.oauth.OAuth;
import com.dream.rapid.base.BaseController;
import com.dream.recommend.model.RecommendStatus;
import com.dream.recommend.service.RecommendStatusService;
import com.dream.shop.model.Shop;
import com.dream.shop.model.ShopCategory;
import com.dream.shop.service.ShopCategoryService;
import com.dream.shop.service.ShopService;
import com.taobao.api.ApiException;
import com.taobao.api.domain.SellerCat;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.SellercatsListGetRequest;
import com.taobao.api.request.ShopGetRequest;
import com.taobao.api.response.SellercatsListGetResponse;
import com.taobao.api.response.ShopGetResponse;

/**
 * 淘宝OAuth完成后的处理类，主要完成用户信息和店铺信息的同步。
 * 
 * @author Frank
 */

@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {
	
	
	@Autowired
	private AuthService authService;
	
	public void setAuthService(AuthService authService){
		this.authService = authService;
	}
	
	@Autowired
	private ShopService shopService;
	
	public void setShopService(ShopService shopService){
		this.shopService = shopService;
	}
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	public void setShopCategoryService(ShopCategoryService shopCategoryService){
		this.shopCategoryService = shopCategoryService;
	}
	
	
	@Autowired
	private RecommendStatusService recommendStatusService;
	
	public void setRecommendStatusService(RecommendStatusService recommendStatusService){
		this.recommendStatusService = recommendStatusService;
	}
	
	
	@Autowired
	private ItemService itemService;
	
	public void setItemService(ItemService itemService){
		this.itemService = itemService;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String index ( @RequestParam(value = "code", defaultValue = "-1") String code,HttpServletRequest request)  throws Exception {
		String result = "redirect:dashboard";
		
		if(getOAuth() != null){
			logger.debug("The user has been authorized with :", getOAuth());
			return result;
		}
		
		if(code.equals("-1") ){
			logger.error("The code can not be null");
			return "redirect:t";
		}
		
		//如果是第一次授权，需要将用户信息、店铺信息、分类信息、商品信息同步至数据库中
		try{
			doAuth(code, request);
			syncAuth(request);
			syncShop(request);
		}catch(Exception e){
			logger.error("Auth or sync failed,please relogin.", e);
			request.getSession().removeAttribute(TOP_OAUTH);
			request.getSession().removeAttribute(TOP_AUTH);
			request.getSession().removeAttribute(TOP_SHOP);
			throw new ServletException("Auth or sync failed,please relogin.");
		}
		return result;
	}
	
	
	
	private void doAuth(String code,HttpServletRequest servletRequest) throws IOException{
		WebUtils.setIgnoreSSLCheck(true);
		Map<String, String> props = new HashMap<String, String>();
		props.put("grant_type", "authorization_code");
		/* 测试时，需把test参数换成自己应用对应的值 */
		props.put("code", code);
		props.put("client_id", getSysParamValue("top.appkey"));
		props.put("client_secret", getSysParamValue("top.appsecret"));
		props.put("redirect_uri", getSysParamValue("ssk.redirecturl"));
		props.put("view", "web");
		String result = WebUtils.doPost(getSysParamValue("top.oauthurl"), props, Integer.valueOf(getSysParamValue("top.connectiontimeout")), Integer.valueOf(getSysParamValue("top.readtimeout")));
		logger.debug("The code has been authorized：{}",result);
		ObjectMapper objectMapper =  new ObjectMapper();
		OAuth auth = objectMapper.readValue(result, OAuth.class);
		servletRequest.getSession().setAttribute(TOP_OAUTH, auth);
	}
	
	private void syncAuth(HttpServletRequest servletRequest) throws ApiException{
		OAuth oauth = getOAuth();
		//仅存在淘宝ID时，不存在SubTaobaoUserId是说明是淘宝正常用户登录，否则就是子账户登录
		AuthCriteria criteria = new AuthCriteria();
		if(oauth.getTaobaoUserId()!=null && oauth.getSubTaobaoUserId()==null){
			criteria.createCriteria().andTaobaoUserIdEqualTo(oauth.getTaobaoUserId());
		}else if((oauth.getTaobaoUserId()!=null && oauth.getSubTaobaoUserId()!=null)){
			criteria.createCriteria().andTaobaoUserIdEqualTo(oauth.getTaobaoUserId()).andSubTaobaoUserIdEqualTo(oauth.getSubTaobaoUserId());
		}else{
			throw new IllegalArgumentException("Wrong!!!!!!!!!!");
		}
		Auth result = this.authService.queryByCriteria(criteria);
		Auth localAuth = new Auth();
		BeanUtils.copyProperties(oauth,localAuth);
		localAuth.setIsEnable("1");
		localAuth.setIsLock("1");
		localAuth.setRefreshedTime(new Date());
		//先判断用户是否已经存在
		if(result == null){
			localAuth.setCreateTime(new Date());
			authService.save(localAuth);
		} else {
			localAuth.setUserId(result.getUserId());
			authService.update(localAuth);
		}
		servletRequest.getSession().setAttribute(TOP_AUTH, localAuth);
	}
	
	private void syncShop(HttpServletRequest servletRequest) throws ApiException{
		//无论是淘宝用户还是其子账户，都通过淘宝用户的昵称来查询其店铺信息
		Shop result = shopService.getByNick(getOAuth().getTaobaoUserNick());
		Shop shopInSession = result ;
		//先判断店铺信息是否存在，如果店铺不存在，则通过接口查询并同步至本地；如果店铺存在，且同步店铺信息的开关为开时，则通过接口查询并同步至本地；否则，直接使用数据库中的店铺信息
		//如果是第一次授权，需要将用户信息、店铺信息、分类信息、商品信息同步至数据库中
		if(result == null){
			//店铺信息店铺信息同步
			ShopGetRequest request = new ShopGetRequest();
			request.setFields("sid,cid,title,nick,desc,bulletin,pic_path,created,modified");
			request.setNick(getOAuth().getTaobaoUserNick());
			ShopGetResponse response = (ShopGetResponse) getTaobaoResponse(request, getOAuth().getTaobaoUserNick());
			Shop shop = new Shop();
			BeanUtils.copyProperties(response.getShop(), shop);
			shopService.save(shop);
			//店铺分类信息同步
			SellercatsListGetRequest req=new SellercatsListGetRequest();
			req.setNick(getOAuth().getTaobaoUserNick());
			SellercatsListGetResponse resp = (SellercatsListGetResponse) getTaobaoResponse(req);
			if(resp.isSuccess()){
				List<SellerCat> shopCats = resp.getSellerCats();
				List<ShopCategory> shopCategories = new ArrayList<ShopCategory>();
				for(SellerCat sc : shopCats){
					ShopCategory cat = new ShopCategory();
					BeanUtils.copyProperties(sc, cat);
					cat.setShopId(shop.getSid());
					cat.setCreateTime(new Date());
					cat.setRefreshTime(new Date());
				}
				shopCategoryService.saveBatch(shopCategories);
			}
			//商品信息同步
			itemService.syncItems(getOAuth());
			
			
			shopInSession = shop;
			//判断如果默认打开自动橱窗推荐，则将相关信息记录至自动橱窗状态表
			if("1".equals(getSysParamValue("top.default.enabled"))){
				RecommendStatus recommendStatus = new RecommendStatus(shop.getSid());
				recommendStatus.setStatus("1");
				recommendStatus.setLastModifiedBy(getAuth().getUserId());
				recommendStatus.setLastModifiedTime(new Date());
				recommendStatusService.save(recommendStatus);
			}
		}else if ("1".equals(getSysParamValue("top.syncshop"))){
			ShopGetRequest request = new ShopGetRequest();
			request.setFields("sid,cid,title,nick,desc,bulletin,pic_path,created,modified");
			request.setNick(getOAuth().getTaobaoUserNick());
			ShopGetResponse response = (ShopGetResponse) getTaobaoResponse(request, getOAuth().getTaobaoUserNick());
			Shop shop = new Shop();
			BeanUtils.copyProperties(response.getShop(), shop);
			shop.setSid(result.getSid());
			shopService.update(shop);
			shopInSession = shop;
		}
		servletRequest.getSession().setAttribute(TOP_SHOP, shopInSession);
	}

}
