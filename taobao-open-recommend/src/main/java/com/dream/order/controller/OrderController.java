/*******************************************************************************
 * Copyright (c) 2005, 2014 com.dream
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dream.order.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.rapid.base.BaseController;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TradeFullinfoGetRequest;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.response.TradeFullinfoGetResponse;
import com.taobao.api.response.TradesSoldGetResponse;

/**
 * OrderController负责打开订单列表和订单详情页面，
 * 
 * 
 * 
 * @author Frank
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {
	

	@RequestMapping(value = "list",method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		String sessionKey = getOAuth().getAccessToken();
		TaobaoClient client=new DefaultTaobaoClient("http://gw.api.tbsandbox.com/router/rest", "1021697746", "sandbox18b7c5cc19a602a635ebb8bcd");
		TradesSoldGetRequest req=new TradesSoldGetRequest();
		req.setFields("seller_nick,buyer_nick,title,type,created,sid,tid,seller_rate,buyer_rate,status,payment,discount_fee,adjust_fee,post_fee,total_fee,pay_time,end_time,modified,consign_time,buyer_obtain_point_fee,point_fee,real_point_fee,received_payment,commission_fee,pic_path,num_iid,num_iid,num,price,cod_fee,cod_status,shipping_type,receiver_name,receiver_state,receiver_city,receiver_district,receiver_address,receiver_zip,receiver_mobile,receiver_phone,orders.title,orders.pic_path,orders.price,orders.num,orders.iid,orders.num_iid,orders.sku_id,orders.refund_status,orders.status,orders.oid,orders.total_fee,orders.payment,orders.discount_fee,orders.adjust_fee,orders.sku_properties_name,orders.item_meal_name,orders.buyer_rate,orders.seller_rate,orders.outer_iid,orders.outer_sku_id,orders.refund_id,orders.seller_type");
		TradesSoldGetResponse response = client.execute(req , sessionKey);
		model.addAttribute("trades", response.getTrades());
		return "order/list";
	}
	
	@RequestMapping(value = "detail/{id}",method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model)  throws Exception {
		String sessionKey = getOAuth().getAccessToken();
		TaobaoClient client=new DefaultTaobaoClient("http://gw.api.tbsandbox.com/router/rest", "1021697746", "sandbox18b7c5cc19a602a635ebb8bcd");
		TradeFullinfoGetRequest req=new TradeFullinfoGetRequest();
		req.setFields("seller_nick,buyer_nick,title,type,created,sid,tid,seller_rate,buyer_rate,status,payment,discount_fee,adjust_fee,post_fee,total_fee,pay_time,end_time,modified,consign_time,buyer_obtain_point_fee,point_fee,real_point_fee,received_payment,commission_fee,pic_path,num_iid,num_iid,num,price,cod_fee,cod_status,shipping_type,receiver_name,receiver_state,receiver_city,receiver_district,receiver_address,receiver_zip,receiver_mobile,receiver_phone,orders.title,orders.pic_path,orders.price,orders.num,orders.iid,orders.num_iid,orders.sku_id,orders.refund_status,orders.status,orders.oid,orders.total_fee,orders.payment,orders.discount_fee,orders.adjust_fee,orders.sku_properties_name,orders.item_meal_name,orders.buyer_rate,orders.seller_rate,orders.outer_iid,orders.outer_sku_id,orders.refund_id,orders.seller_type");
		req.setTid(id);
		TradeFullinfoGetResponse response = client.execute(req , sessionKey);
		model.addAttribute("trade", response.getTrade());
		return "order/detail";
	}

}
