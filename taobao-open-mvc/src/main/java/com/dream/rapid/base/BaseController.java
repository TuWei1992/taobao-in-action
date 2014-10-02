package com.dream.rapid.base;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.WebContentGenerator;

import com.dream.auth.model.Auth;
import com.dream.common.model.SysParam;
import com.dream.common.service.SysParamService;
import com.dream.oauth.OAuth;
import com.dream.rapid.beanutils.BeanUtils;
import com.dream.rapid.page.Page;
import com.dream.rapid.page.PageRequest;
import com.dream.rapid.util.ConvertRegisterHelper;
import com.dream.rapid.util.PageRequestFactory;
import com.dream.shop.model.Shop;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;


/**
 * 标准的rest方法列表
 * <pre>
 * /userinfo                => index()  
 * /userinfo/new            => _new()  注意: 不使用/userinfo/add => add()的原因是ad会被一些浏览器当做广告URL拦截
 * /userinfo/{id}           => show()  
 * /userinfo/{id}/edit      => edit()  
 * /userinfo        POST    => create()  
 * /userinfo/{id}   PUT     => update()  
 * /userinfo/{id}   DELETE  => delete()  
 * /userinfo        DELETE  => batchDelete()  
 * </pre>
 * 
 * @author Frank
 */
public abstract class BaseController<Entity,PK,Criteria> extends WebContentGenerator {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
	protected final static String TOP_OAUTH = "TOP_OAUTH";
	protected final static String TOP_AUTH = "TOP_AUTH";
	protected final static String TOP_SHOP = "TOP_SHOP";
    protected final static String CREATED_SUCCESS = "创建成功";
    protected final static String UPDATE_SUCCESS = "更新成功";
    protected final static String DELETE_SUCCESS = "删除成功";
    
    static {
        //注册converters
        ConvertRegisterHelper.registerConverters();
    }
    
    public static void copyProperties(Object target,Object source) {
        BeanUtils.copyProperties(target, source);
    }

    public static <T> T copyProperties(Class<T> destClass,Object orig) {
        return BeanUtils.copyProperties(destClass, orig);
    }
    
    public static ModelMap toModelMap(Page page,PageRequest pageRequest) {
        return toModelMap("",page, pageRequest);
    }
    
    public static ModelMap toModelMap(String tableId,Page page,PageRequest pageRequest) {
        ModelMap model = new ModelMap();
        saveIntoModelMap(tableId,page,pageRequest,model);
        return model;
    }
    
    /**
     * 用于一个页面有多个extremeTable是使用
     * @param tableId 等于extremeTable的tableId属性
     */
    public static void saveIntoModelMap(String tableId,Page page,PageRequest pageRequest,ModelMap model){
        Assert.notNull(tableId,"tableId must be not null");
        Assert.notNull(page,"page must be not null");
        model.addAttribute(tableId+"page", page);
        model.addAttribute(tableId+"totalRows", new Integer(page.getTotalCount()));
        model.addAttribute(tableId+"pageRequest", pageRequest);
        model.addAttribute(tableId+"query", pageRequest);
    }
    
    public static PageRequest bindPageRequest(HttpServletRequest request,PageRequest pageRequest,String defaultSortColumns){
        return PageRequestFactory.bindPageRequest(pageRequest,request, defaultSortColumns);
    }
    
    public static <T> T getOrCreateRequestAttribute(HttpServletRequest request, String key,Class<T> clazz) {
        Object value = request.getAttribute(key);
        if(value == null) {
            try {
                value = clazz.newInstance();
            } catch (Exception e) {
                ReflectionUtils.handleReflectionException(e);
            }
            request.setAttribute(key, value);
        }
        return (T)value;
    }
	
	protected TaobaoClient getTaobaoClient(){
		String serverUrl = getSysParamValue("top.serverurl");
		String appKey = getSysParamValue("top.appkey");
		String appSecret = getSysParamValue("top.appsecret");
		TaobaoClient client=new DefaultTaobaoClient(serverUrl, appKey, appSecret);
		return client;
	}
	
	protected <T extends TaobaoResponse> T getTaobaoResponse(TaobaoRequest<T> request) throws ApiException{
		logger.debug("Start sending top request:{}",request);
		T response = getTaobaoClient().execute(request);
		logger.debug("End sending top request:{},We get top response:{}",new Object[]{request,response});
		return response;
	}
	
	protected <T extends TaobaoResponse> T getTaobaoResponse(TaobaoRequest<T> request,String session) throws ApiException{
		logger.debug("Start sending top request:{} with session:{}",request,session);
		T response = getTaobaoClient().execute(request,session);
		logger.debug("End sending top request:{} with session:{},We get top response:{}",new Object[]{request,session,response});
		return response;
	}
	
	protected OAuth getOAuth(){
		OAuth auth = (OAuth) RequestContextHolder.currentRequestAttributes().getAttribute(TOP_OAUTH, RequestAttributes.SCOPE_SESSION);
		if(auth == null){
			 new javax.servlet.ServletException("Can not get the OAuth");
		}
		return auth;
	}
	
	protected Auth getAuth(){
		Auth auth = (Auth) RequestContextHolder.currentRequestAttributes().getAttribute(TOP_AUTH, RequestAttributes.SCOPE_SESSION);
		if(auth == null){
			 new javax.servlet.ServletException("Can not get the auth");
		}
		return auth;
	}
	
	protected Shop getShop(){
		Shop shop = (Shop) RequestContextHolder.currentRequestAttributes().getAttribute(TOP_SHOP, RequestAttributes.SCOPE_SESSION);
		if(shop == null){
			 new javax.servlet.ServletException("Can not get the shop");
		}
		return shop;
	}
	
	/**
	 * 根据Key获取参数值
	 * @param key
	 * @return
	 */
	protected String getSysParamValue(String key){
		SysParamService service = this.getApplicationContext().getBean("sysParamService", SysParamService.class);
		SysParam param  = service.getByKey(key);
		return param.getValue();
	}
	
}
