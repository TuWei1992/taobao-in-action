/*******************************************************************************
 * Copyright (c) 2005, 2014 com.dream
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dream.item.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.item.model.ItemCriteria;
import com.dream.item.service.ItemService;
import com.dream.rapid.base.BaseController;
import com.dream.rapid.beanutils.BeanUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Item;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.request.ItemsInventoryGetRequest;
import com.taobao.api.request.ItemsOnsaleGetRequest;
import com.taobao.api.response.ItemGetResponse;
import com.taobao.api.response.ItemsInventoryGetResponse;
import com.taobao.api.response.ItemsOnsaleGetResponse;

/**
 * ItemController负责打开商品列表和商品详情页面，
 * 
 * 
 * 
 * @author Frank
 */

@Controller
@RequestMapping(value = "/item")
public class ItemController extends BaseController<Item,Item,ItemCriteria> {
	
    
	@Autowired
	private ItemService itemService;
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(@RequestParam(value = "id", defaultValue = "-1") String code, Model model) throws Exception {
		
		String sessionKey = getOAuth().getAccessToken();
		TaobaoClient client=new DefaultTaobaoClient("http://gw.api.tbsandbox.com/router/rest", "1021697746", "sandbox18b7c5cc19a602a635ebb8bcd");
		ItemsOnsaleGetRequest req=new ItemsOnsaleGetRequest();
		req.setFields("approve_status,num_iid,title,nick,type,cid,pic_url,num,props,valid_thru,list_time,price,has_discount,has_invoice,has_warranty,has_showcase,modified,delist_time,postage_id,seller_cids,outer_id");
		ItemsOnsaleGetResponse response = client.execute(req , sessionKey);
		if(!response.isSuccess())
		{	
			throw new ServletException("Error");
		}
		model.addAttribute("onsale", response.getItems());
		
		
		ItemsInventoryGetRequest req1=new ItemsInventoryGetRequest();
		req1.setFields("approve_status,num_iid,title,nick,type,cid,pic_url,num,props,valid_thru,list_time,price,has_discount,has_invoice,has_warranty,has_showcase,modified,delist_time,postage_id,seller_cids,outer_id");
		ItemsInventoryGetResponse response1 = client.execute(req1 , sessionKey);
		if(!response1.isSuccess())
		{	
			throw new ServletException("Error");
		}
		model.addAttribute("inventory", response1.getItems());
		
		return "item/list";
	}
//	
	@RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model)  throws Exception {
		TaobaoClient client=new DefaultTaobaoClient("http://gw.api.tbsandbox.com/router/rest", "1021697746", "sandbox18b7c5cc19a602a635ebb8bcd");
		ItemGetRequest req=new ItemGetRequest();
		req.setFields("num_iid,title,price,desc_modules,sell_point");
		req.setNumIid(id);
		String sessionKey = getOAuth().getAccessToken();
		ItemGetResponse response = client.execute(req ,sessionKey);
		model.addAttribute("detail", response.getItem());
		return "item/detail";
	}
	
	@RequestMapping(value = "/sync",method = RequestMethod.GET)
	public @ResponseBody String sync() throws Exception {
		syncOnSaleItemsInternal();
		syncInventoryItemsInternal();
		return "ok";
	}
	
	@RequestMapping(value = "/synconsale",method = RequestMethod.GET)
	public @ResponseBody String syncOnSaleItems() throws ApiException{
		syncOnSaleItemsInternal();
		return "ok";
	}
	
	@RequestMapping(value = "/syncinventory",method = RequestMethod.GET)
	public @ResponseBody String syncInventoryItems() throws ApiException{
		syncInventoryItemsInternal() ;
		return "ok";
	}
	
	

	private void syncOnSaleItemsInternal() throws ApiException{
		ItemsOnsaleGetRequest request = new ItemsOnsaleGetRequest();
		request.setFields("approve_status,num_iid,title,nick,type,cid,pic_url,num,props,valid_thru,list_time,price,has_discount,has_invoice,has_warranty,has_showcase,modified,delist_time,postage_id,seller_cids,outer_id");
		ItemsOnsaleGetResponse response =  (ItemsOnsaleGetResponse) getTaobaoResponse(request, getOAuth().getAccessToken());
		List<Item> items = response.getItems();
		List<com.dream.item.model.Item> localItems = new ArrayList<com.dream.item.model.Item>(items.size());
		for(Item item : items){
			com.dream.item.model.Item dest =  new com.dream.item.model.Item();
			if(item == null|| item.getNumIid() == null){
				continue;
			}
			BeanUtils.copyProperties(dest,item);
			dest.setType("1");
			localItems.add(dest);
		}
		ItemCriteria criteria = new ItemCriteria();
		criteria.createCriteria().andNickEqualTo(getOAuth().getTaobaoUserNick()).andTypeEqualTo("1");
		this.itemService.removeSaveBatch(criteria,localItems);
	}
	
	private void syncInventoryItemsInternal() throws ApiException{
		ItemsInventoryGetRequest request=new ItemsInventoryGetRequest();
		request.setFields("approve_status,num_iid,title,nick,type,cid,pic_url,num,props,valid_thru,list_time,price,has_discount,has_invoice,has_warranty,has_showcase,modified,delist_time,postage_id,seller_cids,outer_id");
		ItemsInventoryGetResponse response =  (ItemsInventoryGetResponse) getTaobaoResponse(request, getOAuth().getAccessToken());
		List<Item> items = response.getItems();
		List<com.dream.item.model.Item> localItems = new ArrayList<com.dream.item.model.Item>(items.size());
		for(Item item : items){
			com.dream.item.model.Item dest =  new com.dream.item.model.Item();
			if(item == null|| item.getNumIid() == null){
				continue;
			}
			BeanUtils.copyProperties(dest ,item);
			dest.setType("0");
			localItems.add(dest);
		}
		ItemCriteria criteria = new ItemCriteria();
		criteria.createCriteria().andNickEqualTo(getOAuth().getTaobaoUserNick()).andTypeEqualTo("0");
		this.itemService.removeSaveBatch(criteria,localItems);
	}

}
