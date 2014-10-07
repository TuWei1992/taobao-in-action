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

import com.dream.rapid.base.BaseController;
import com.dream.rapid.web.scope.Flash;
import com.dream.recommend.model.RecommendModeSetting;
import com.dream.recommend.model.RecommendModeSettingCriteria;
import com.dream.recommend.model.RecommendStatus;
import com.dream.recommend.service.RecommendModeSettingService;
import com.dream.recommend.service.RecommendUpdatedEvent;
import com.dream.recommend.vo.query.RecommendModeSettingQuery;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/recommendmodesetting")
public class RecommendModeSettingController extends BaseController<RecommendModeSetting,RecommendModeSetting,RecommendModeSettingCriteria>{

	//================================********Generated code start here********================================
	
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	@Autowired
	private RecommendModeSettingService recommendModeSettingService;
	
	private final String LIST_ACTION = "redirect:/recommendmodesetting";
	
	/**
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 * @param service
	 */
	public void setRecommendModeSettingService(RecommendModeSettingService service) {
		this.recommendModeSettingService = service;
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
	public String index(ModelMap model,RecommendModeSettingQuery query,HttpServletRequest request,HttpServletResponse response) throws Exception {
		RecommendModeSetting recommendModeSetting = (RecommendModeSetting)recommendModeSettingService.queryById(new RecommendModeSetting(getShop().getSid()));
		model.addAttribute("setting", recommendModeSetting);
		return "/recommendmodesetting/index";
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
		RecommendModeSetting recommendModeSetting = (RecommendModeSetting)recommendModeSettingService.queryById(new RecommendModeSetting(getShop().getSid()));
		model.addAttribute("recommendModeSetting",recommendModeSetting);
		return "/recommendmodesetting/show";
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
	public String _new(ModelMap model,RecommendModeSetting recommendModeSetting,HttpServletRequest request,HttpServletResponse response) throws Exception {
		model.addAttribute("recommendModeSetting",recommendModeSetting);
		return "/recommendmodesetting/new";
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
	public String create(ModelMap model,@Valid RecommendModeSetting recommendModeSetting,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return  "/recommendmodesetting/new";
		}
		recommendModeSettingService.save(recommendModeSetting);
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
	@RequestMapping(value="/edit/{id}",method=RequestMethod.POST)
	public String edit(ModelMap model,@PathVariable java.lang.Long id,RecommendModeSetting recommendModeSetting) throws Exception {
		recommendModeSetting.setShopId(id);
		recommendModeSetting.setLastModifiedBy(getAuth().getUserId());
		recommendModeSetting.setLastModifiedTime(new Date());
		RecommendModeSettingCriteria criteria = new RecommendModeSettingCriteria();
		criteria.createCriteria().andShopIdEqualTo(id);
		recommendModeSettingService.saveOrUpdate(recommendModeSetting, criteria);
		
		RecommendStatus status = new RecommendStatus(id);
		status.setUpdatedUserId(getAuth().getUserId());
		getApplicationContext().publishEvent(new RecommendUpdatedEvent(status));
		
		return LIST_ACTION;
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
	public String update(ModelMap model,@PathVariable java.lang.Long id,RecommendModeSetting recommendModeSetting,BindingResult errors,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(errors.hasErrors()) {
			return "/recommendmodesetting/index";
		}
		recommendModeSetting.setShopId(id);
		recommendModeSetting.setLastModifiedBy(getAuth().getUserId());
		recommendModeSetting.setLastModifiedTime(new Date());
		RecommendModeSettingCriteria criteria = new RecommendModeSettingCriteria();
		criteria.createCriteria().andShopIdEqualTo(id);
		recommendModeSettingService.saveOrUpdate(recommendModeSetting, criteria);
//		Flash.current().success(UPDATE_SUCCESS)
		
		
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
		recommendModeSettingService.removeById(new RecommendModeSetting(getShop().getSid()));
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
			recommendModeSettingService.removeById(new RecommendModeSetting(items[i]));
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	

	//================================********Generated code end here********================================
	
	//================================********Customized code start here********==============================

	
	
	//================================********Customized code end here********================================
	
}

