/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */
package com.dream.recommend.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dream.auth.service.AuthService;
import com.dream.item.service.ItemService;
import com.dream.rapid.base.BaseController;
import com.dream.recommend.service.RecommendService;
import com.dream.recommend.service.RecommendStatusService;
import com.dream.shop.service.ShopService;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/recommendwizard")
public class RecommendWizardController extends BaseController{
	
	//================================********Generated code start here********================================
	
	@Autowired
	private AuthService authService;
	
	/**
	 * @param service
	 */
	public void setAuthService(AuthService service) {
		this.authService = service;
	}
	
	
	@Autowired
	private RecommendService recommendService;
	
	/**
	 * @param service
	 */
	public void setRecommendService(RecommendService service) {
		this.recommendService = service;
	}
	
	
	@Autowired
	private ShopService shopService;
	
	/**
	 * @param service
	 */
	public void setShopService(ShopService service) {
		this.shopService = service;
	}

	
	@Autowired
	private RecommendStatusService recommendStatusService;
	
	public void setRecommendStatusService(RecommendStatusService service) {
		this.recommendStatusService = service;
	}
	
	
	@Autowired
	private ItemService itemService;
	
	public void setItemService(ItemService service) {
		this.itemService = service;
	}
	
	/**
	 * binder用于bean属性的设置
	 * @param binder
	 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
	}
	   
	
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 * @param model
	 */
	@ModelAttribute
	public void init(ModelMap model) {
		model.put("now", new java.sql.Timestamp(System.currentTimeMillis()));
	}
	
	
	@RequestMapping
	public String index(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return "wizard";
	}
	
	
	
	
}

