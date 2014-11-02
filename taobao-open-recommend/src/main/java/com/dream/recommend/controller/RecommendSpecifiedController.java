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

import com.dream.item.model.Item;
import com.dream.item.model.ItemCriteria;
import com.dream.item.service.ItemService;
import com.dream.rapid.base.BaseController;
import com.dream.rapid.web.scope.Flash;
import com.dream.recommend.model.RecommendSpecified;
import com.dream.recommend.model.RecommendSpecifiedCriteria;
import com.dream.recommend.model.RecommendStatus;
import com.dream.recommend.service.RecommendSpecifiedService;
import com.dream.recommend.service.RecommendUpdatedEvent;
import com.dream.recommend.vo.query.RecommendSpecifiedQuery;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/recommendspecified")
public class RecommendSpecifiedController extends BaseController<RecommendSpecified,RecommendSpecified,RecommendSpecifiedCriteria>{

	//================================********Generated code start here********================================
	
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	@Autowired
	private RecommendSpecifiedService recommendSpecifiedService;
	
	private final String LIST_ACTION = "redirect:/recommendspecified";
	
	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 * @param service
	 */
	public void setRecommendSpecifiedService(RecommendSpecifiedService service) {
		this.recommendSpecifiedService = service;
	}
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 * @param service
	 */
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
	
	/**
	 * 列表
	 * @param model
	 * @param query
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping
	public String index(ModelMap model,RecommendSpecifiedQuery query,HttpServletRequest request,HttpServletResponse response) throws Exception {
		RecommendSpecifiedCriteria criteria = new RecommendSpecifiedCriteria();
		criteria.createCriteria().andShopIdEqualTo(getShop().getSid());
		List<RecommendSpecified> result = this.recommendSpecifiedService.queryAllByCriteria(criteria );
		model.addAttribute("spcifieds", result);
		
		ItemCriteria iCriteria = new ItemCriteria();
		iCriteria.createCriteria().andNickEqualTo(getAuth().getTaobaoUserNick());
		List<Item> items = itemService.queryAllByCriteria(iCriteria );
		model.addAttribute("items", items);
		
		return "/recommendspecified/index";
	}
	
	/**
	 * 显示
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable java.lang.String id) throws Exception {
//		RecommendSpecified recommendSpecified = (RecommendSpecified)recommendSpecifiedService.getById(id);
//		model.addAttribute("recommendSpecified",recommendSpecified);
		return "/recommendspecified/show";
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
	public String _new(ModelMap model,RecommendSpecified recommendSpecified,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("recommendSpecified",recommendSpecified);
		return "/recommendspecified/new";
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
	public String create(ModelMap model,@Valid RecommendSpecified recommendSpecified,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/recommendspecified/new";
		}
		
//		recommendSpecifiedService.save(recommendSpecified);
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
	@RequestMapping(value="/{id}/edit/{action}")
	public String edit(ModelMap model,@PathVariable java.lang.Long id,@PathVariable java.lang.String action) throws Exception {
		RecommendSpecifiedCriteria criteria = new RecommendSpecifiedCriteria();
		criteria.createCriteria().andShopIdEqualTo(getShop().getSid()).andItemIdEqualTo(id);
		RecommendSpecified specified = new RecommendSpecified();
		specified.setItemId(id);
		specified.setShopId(getShop().getSid());
		specified.setType(action);
		specified.setLastModifiedBy(getAuth().getUserId());
		specified.setLastModifiedTime(new Date());
		this.recommendSpecifiedService.saveOrUpdate(specified, criteria);
		
		RecommendStatus status = new RecommendStatus(id);
		status.setShopId(getShop().getSid());
		status.setUpdatedUserId(getAuth().getUserId());
		getApplicationContext().publishEvent(new RecommendUpdatedEvent(status));
		
		return "redirect:/recommendspecified";
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
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable java.lang.String id,@Valid RecommendSpecified recommendSpecified,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/recommendspecified/edit";
		}
		
//		recommendSpecifiedService.update(recommendSpecified);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**
	 * 删除
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable java.lang.String id) {
//		recommendSpecifiedService.removeById(id);
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
	public String batchDelete(ModelMap model,@RequestParam("items") java.lang.String[] items) {
		for(int i = 0; i < items.length; i++) {
//			recommendSpecifiedService.removeById(items[i]);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	

	//================================********Generated code end here********================================
	
	//================================********Customized code start here********==============================

	
	
	//================================********Customized code end here********================================
	
}

