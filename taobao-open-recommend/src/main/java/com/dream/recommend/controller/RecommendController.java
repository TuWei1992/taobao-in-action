/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */
package com.dream.recommend.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.item.model.Item;
import com.dream.rapid.base.BaseController;
import com.dream.recommend.service.RecommendService;
import com.dream.shop.model.Shop;
import com.dream.shop.service.ShopService;
import com.taobao.api.request.ItemRecommendAddRequest;
import com.taobao.api.request.ItemRecommendDeleteRequest;
import com.taobao.api.request.ItemsOnsaleGetRequest;
import com.taobao.api.response.ItemRecommendAddResponse;
import com.taobao.api.response.ItemRecommendDeleteResponse;
import com.taobao.api.response.ItemsOnsaleGetResponse;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/recommend")
public class RecommendController extends BaseController{
	
	//================================********Generated code start here********================================
	
	
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
		ItemsOnsaleGetRequest req = new ItemsOnsaleGetRequest();
		req.setFields("approve_status,num_iid,title,nick,type,cid,pic_url,num,props,valid_thru,list_time,price,has_discount,has_invoice,has_warranty,has_showcase,modified,delist_time,postage_id,seller_cids,outer_id");
		req.setHasShowcase(true);
		req.setOrderBy("list_time:desc");
		req.setPageSize(100L);
		ItemsOnsaleGetResponse resp =  (ItemsOnsaleGetResponse) getTaobaoResponse(req, getOAuth().getAccessToken());
		if(!resp.isSuccess())
		{	
			throw new ServletException("Error");
		}
		model.addAttribute("showcases", resp.getItems());
		return "recommend/index";
	}
	
	
	@RequestMapping(value="/{id}")
	public String putoff(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		ItemRecommendDeleteRequest req =new ItemRecommendDeleteRequest();
		req.setNumIid(id);
		ItemRecommendDeleteResponse resp =  (ItemRecommendDeleteResponse) getTaobaoResponse(req, getOAuth().getAccessToken());
		return "redirect:/recommend";
	}
	
	
	/**
	 * http://open.taobao.com/support/question_detail.htm?spm=a219a.7386797.0.0.kIT4ui&id=7489
	 * http://bbs.taobao.com/catalog/thread/154504-5113004.htm?spm=a219a.7386793.0.0.CbJKjd
	 * 1.查询过去10分钟内更新过推荐配置的店铺，返回店铺列表。
	 * 2.根据店铺编号循环查询待推荐橱窗的商品集合[集合A]?????
	 * 3.根据店铺编号查询已推荐橱窗的商品集合[集合B]，通过调用：taobao.items.onsale.get接口将其入参has_showcase（是否橱窗推荐）设置成true来查询卖家橱窗推荐的商品。?????
	 * 4.比较2和3的差异，做逻辑处理?????
	 * 5.相关TOP API:taobao.item.recommend.add 橱窗推荐一个商品；taobao.item.recommend.delete 取消橱窗推荐一个商品
	 * @param model
	 * @param query
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/add")
	public @ResponseBody String recommend(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<Shop> shops = shopService.queryAll();
 		for(Shop shop : shops){
			List<Item> items =  this.recommendService.recommendItems(shop, getAuth());
			for(Item item:items){
				ItemRecommendAddRequest req =new ItemRecommendAddRequest();
				req.setNumIid(item.getNumIid());
				ItemRecommendAddResponse resp =  (ItemRecommendAddResponse) getTaobaoResponse(req, getOAuth().getAccessToken());
				if(resp.isSuccess()){
					logger.debug("The item {} has been recommended successfully.", item.getNumIid());
				}else{
					logger.debug("Oh,what wrong,the item {} has not been recommended,the resean is {}", item.getNumIid(),resp.getMsg());
				}
			}
		}
		return "ok";
	}
	
	
}

