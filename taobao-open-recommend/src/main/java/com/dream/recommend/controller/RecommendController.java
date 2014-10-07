/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */
package com.dream.recommend.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.dream.auth.model.Auth;
import com.dream.auth.model.AuthCriteria;
import com.dream.auth.service.AuthService;
import com.dream.item.model.Item;
import com.dream.item.model.ItemCriteria;
import com.dream.item.service.ItemService;
import com.dream.rapid.base.BaseController;
import com.dream.recommend.model.RecommendStatus;
import com.dream.recommend.model.RecommendStatusCriteria;
import com.dream.recommend.service.RecommendDoneEvent;
import com.dream.recommend.service.RecommendService;
import com.dream.recommend.service.RecommendStatusService;
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
	 * 1.查询过去10分钟内？尚未被定时推荐的店铺更新过推荐配置的店铺，返回店铺列表。
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
		//查询所有应该自动推荐的店铺
		RecommendStatusCriteria criteria = new RecommendStatusCriteria();
		criteria.createCriteria().andIsRecommendedEqualTo("0").andStatusEqualTo("1");
		List<RecommendStatus> result = recommendStatusService.queryAllByCriteria(criteria);
		for(RecommendStatus status : result){
			//根据店铺编号获取应该推荐的商品候选列表，同时获取auth信息
 			Long shopId = status.getShopId();
 			Shop shop = shopService.queryById(new Shop(shopId));
 			AuthCriteria aCriteria = new AuthCriteria();
 			aCriteria.createCriteria().andTaobaoUserNickEqualTo(shop.getNick());
 			Auth auth = authService.queryByCriteria(aCriteria);
			List<Item> items =  this.recommendService.recommendItems(shop, auth);
			
			//当根据设置查询出来的推荐商品少于橱窗个数时，进行以下处理
			if(items == null || items.isEmpty()){
				ItemCriteria iCriteria = new ItemCriteria();
	 			iCriteria.createCriteria().andNickEqualTo(shop.getNick()).andApproveStatusEqualTo("onsale");
	 			iCriteria.setOrderByClause("DELIST_TIME");
	 			items = this.itemService.queryAllByCriteria(iCriteria);
			}
			//店铺橱窗总个数，暂时写死为10；后期优化
			int allCount = 10;
			int size = items.size();
			if(items!=null && size < allCount && size > 0 ){
				ItemCriteria iCriteria = new ItemCriteria();
				List<Long> values = new ArrayList<Long>();
				for(Item item : items){
					values.add(item.getNumIid());
				}
	 			iCriteria.createCriteria().andNickEqualTo(shop.getNick()).andApproveStatusEqualTo("onsale").andNumIidNotIn(values);
	 			iCriteria.setOrderByClause("DELIST_TIME");
	 			List<Item> rItems  = this.itemService.queryAllByCriteria(iCriteria);
	 			int part = allCount - size;
	 			if(rItems.size() < part){
	 				part = rItems.size();
	 			}
	 			items.addAll(rItems.subList( 0, part ));
			}
			
			
			ItemCriteria iCriteria = new ItemCriteria();
 			iCriteria.createCriteria().andNickEqualTo(shop.getNick()).andApproveStatusEqualTo("onsale").andHasShowcaseEqualTo("true");
 			List<Item> rItems = this.itemService.queryAllByCriteria(iCriteria);
			
 			//先处理应该退出橱窗的商品
 			List<Item> shouldDeleteRecommend = new ArrayList<Item>();
			for(Item item:rItems){
				if(!items.contains(item)){
					shouldDeleteRecommend.add(item);
					ItemRecommendDeleteRequest req=new ItemRecommendDeleteRequest();
					req.setNumIid(item.getNumIid());
					ItemRecommendDeleteResponse resp = (ItemRecommendDeleteResponse) this.getTaobaoResponse(req, auth.getAccessToken());
					if(resp.isSuccess()){
						logger.debug("The item {} has been put off showcases", item.getNumIid());
					}else{
						logger.debug("The item {} has not been put off showcases for {}", item,resp.getErrorCode());
					}
				}
			}
 			
			//后处理应该推上橱窗的商品
			List<Item> shouldAddRecommend = new ArrayList<Item>();
			for(Item item:items){
				//如果已经推荐，则继续循环
				if(item.getHasShowcase().equals("true")){
					continue;
				}
				shouldAddRecommend.add(item);
				ItemRecommendAddRequest req=new ItemRecommendAddRequest();
				req.setNumIid(item.getNumIid());
				ItemRecommendAddResponse resp = (ItemRecommendAddResponse) this.getTaobaoResponse(req, auth.getAccessToken());
				if(resp.isSuccess()){
					logger.debug("The item {} has been put on showcases", item);
				}else{
					logger.debug("The item {} has not been put on showcases for {}", item,resp.getErrorCode());
				}
			}
			
			RecommendDoneEvent event = new RecommendDoneEvent(status);
			this.getApplicationContext().publishEvent(event);
		}
		return "ok";
	}
	
	
}

