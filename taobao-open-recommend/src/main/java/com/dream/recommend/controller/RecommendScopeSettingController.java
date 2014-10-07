/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */
package com.dream.recommend.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dream.item.model.ItemCriteria;
import com.dream.item.service.ItemService;
import com.dream.rapid.base.BaseController;
import com.dream.rapid.web.scope.Flash;
import com.dream.recommend.model.RecommendScopeSetting;
import com.dream.recommend.model.RecommendScopeSettingCriteria;
import com.dream.recommend.model.RecommendStatus;
import com.dream.recommend.service.RecommendScopeSettingService;
import com.dream.recommend.service.RecommendUpdatedEvent;
import com.dream.recommend.vo.query.RecommendScopeSettingQuery;
import com.taobao.api.ApiException;
import com.taobao.api.domain.Shop;
import com.taobao.api.request.ShopRemainshowcaseGetRequest;
import com.taobao.api.response.ShopRemainshowcaseGetResponse;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/recommendscopesetting")
public class RecommendScopeSettingController extends BaseController<RecommendScopeSetting,RecommendScopeSetting,RecommendScopeSettingCriteria>{
	
	//================================********Generated code start here********================================
	
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	@Autowired
	private RecommendScopeSettingService recommendScopeSettingService;
	
	
	
	private final String LIST_ACTION = "redirect:/recommendscopesetting";
	
	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 * @param service
	 */
	public void setRecommendScopeSettingService(RecommendScopeSettingService service) {
		this.recommendScopeSettingService = service;
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
		model.put("oauth", getOAuth());
		model.put("shop", getShop());
		model.put("auth", getAuth());
	}
	
	/**
	 * 列表
	 * @param model
	 * @param query
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping
	public String index(ModelMap model,RecommendScopeSettingQuery query,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("showcase", getShowcases());
		int onsale = queryOnSaleItems().size();
		model.addAttribute("onsale", onsale);
		int inventory = queryInventoryItems().size();
		model.addAttribute("inventory", inventory);
		int all = onsale+inventory;
		model.addAttribute("all",all);
		RecommendScopeSetting setting = this.recommendScopeSettingService.queryById(new RecommendScopeSetting(getShop().getSid()));
		model.addAttribute("setting",setting);
		return "/recommendscopesetting/index";
	}
	
	/**
	 * 显示
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		RecommendScopeSetting recommendScopeSetting = (RecommendScopeSetting)recommendScopeSettingService.queryById(new RecommendScopeSetting(id));
		model.addAttribute("recommendScopeSetting",recommendScopeSetting);
		return "/recommendscopesetting/show";
	}

	/**
	 * 进入新增 
	 * @param model
	 * @param recommendScopeSetting
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/new")
	public String _new(ModelMap model,RecommendScopeSetting recommendScopeSetting,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("recommendScopeSetting",recommendScopeSetting);
		return "/recommendscopesetting/new";
	}
	
	/**
	 * 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult
	 * @param model
	 * @param recommendScopeSetting
	 * @param errors
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid RecommendScopeSetting recommendScopeSetting,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/recommendscopesetting/new";
		}
		
		recommendScopeSettingService.save(recommendScopeSetting);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**
	 * 编辑
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}/edit")
	public String edit(ModelMap model,@PathVariable java.lang.Long id) throws Exception {
		RecommendScopeSetting recommendScopeSetting = (RecommendScopeSetting)recommendScopeSettingService.queryById(new RecommendScopeSetting(id));
		model.addAttribute("recommendScopeSetting",recommendScopeSetting);
		return "/recommendscopesetting/edit";
	}
	
	/**
	 * 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult
	 * @param model
	 * @param id
	 * @param recommendScopeSetting
	 * @param errors
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public String update(ModelMap model,@PathVariable java.lang.Long id,RecommendScopeSetting recommendScopeSetting,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/recommendscopesetting/edit";
		}
		recommendScopeSetting.setShopId(id);
		recommendScopeSetting.setLastModifiedBy(getAuth().getUserId());
		recommendScopeSetting.setLastModifiedTime(new Date());
		RecommendScopeSettingCriteria criteria = new RecommendScopeSettingCriteria();
		criteria.createCriteria().andShopIdEqualTo(id);
		recommendScopeSettingService.saveOrUpdate(recommendScopeSetting, criteria);
		
		RecommendStatus status = new RecommendStatus(id);
		status.setUpdatedUserId(getAuth().getUserId());
		getApplicationContext().publishEvent(new RecommendUpdatedEvent(status));
		return LIST_ACTION;
	}
	
	/**
	 * 删除
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.Long id) {
		recommendScopeSettingService.removeById(new RecommendScopeSetting(id));
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**
	 * 批量删除
	 * @param model
	 * @param items
	 * @return
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.Long[] items) {
		for(int i = 0; i < items.length; i++) {
			recommendScopeSettingService.removeById(new RecommendScopeSetting(items[i]));
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	//================================********Generated code end here********================================
	
	//================================********Customized code start here********================================
	
	@Autowired
	private ItemService itemService ;
	
	public void setItemService(ItemService service) {
		this.itemService = service;
	}
	
	private List queryOnSaleItems(){
		ItemCriteria criteria = new ItemCriteria();
		criteria.createCriteria().andNickEqualTo(getOAuth().getTaobaoUserNick()).andTypeEqualTo("1");
		return itemService.queryAllByCriteria(criteria);
	}
	
	private List queryInventoryItems(){
		ItemCriteria criteria = new ItemCriteria();
		criteria.createCriteria().andNickEqualTo(getOAuth().getTaobaoUserNick()).andTypeEqualTo("0");
		return itemService.queryAllByCriteria(criteria);
	}
	
	//taobao.shop.remainshowcase.get 
	private Shop getShowcases() throws ApiException{
		ShopRemainshowcaseGetRequest request = new ShopRemainshowcaseGetRequest();
		ShopRemainshowcaseGetResponse response = getTaobaoResponse(request, getOAuth().getAccessToken()); 
		return response.getShop();
	}
	
	//================================********Customized code end here********================================
}

