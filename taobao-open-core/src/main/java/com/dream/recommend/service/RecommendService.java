/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.recommend.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.auth.model.Auth;
import com.dream.item.model.Item;
import com.dream.item.model.ItemCriteria;
import com.dream.item.service.ItemService;
import com.dream.rapid.base.BaseService;
import com.dream.rapid.base.EntityDao;
import com.dream.recommend.model.RecommendModeSetting;
import com.dream.recommend.model.RecommendModeSettingCriteria;
import com.dream.recommend.model.RecommendScopeSetting;
import com.dream.recommend.model.RecommendScopeSettingCriteria;
import com.dream.shop.model.Shop;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Service
public class RecommendService extends BaseService{
	
	@Autowired
	private RecommendScopeSettingService recommendScopeSettingService;
	
	@Autowired
	private RecommendModeSettingService recommendModeSettingService;
	
	@Autowired
	private RecommendLogService recommendLogService;
	
	@Autowired
	private ItemService itemService;
	
	public void setRecommendScopeSettingService(
			RecommendScopeSettingService recommendScopeSettingService) {
		this.recommendScopeSettingService = recommendScopeSettingService;
	}

	public void setRecommendModeSettingService(
			RecommendModeSettingService recommendModeSettingService) {
		this.recommendModeSettingService = recommendModeSettingService;
	}

	public void setRecommendLogService(RecommendLogService recommendLogService) {
		this.recommendLogService = recommendLogService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	@Override
	protected EntityDao getEntityDao() {
		throw new UnsupportedOperationException("This is method is a Facede Service.");
	}
	
	
	/**
	 * 推荐商品
	 * 1.根据推荐范围筛选商品
	 * 2.根据推荐设置筛选商品
	 * 3.根据推荐特定设置筛选商品
	 * 4.处理各自的结果集，并返回。
	 * @throws Exception
	 */
	public List<Item> recommendItems(Shop shop,Auth auth) throws Exception{
		List<Item> items = new ArrayList<Item>();
		
		List<Item> scopedItems = queryScopedItems(shop,auth);
		items.addAll(scopedItems);
		
		List<Item> sepcifiedItems = querySepcifiedItems(shop,auth);
		items.addAll(sepcifiedItems);
		
		return items;
	}

	private List<Item> querySepcifiedItems(Shop shop,Auth auth) {
		List<Item> items = new ArrayList<Item>();
		return items;
	}


	private List<Item> queryScopedItems(Shop shop,Auth auth) {
		List<Item> result = null;
		RecommendScopeSettingCriteria scopeCriteria = new RecommendScopeSettingCriteria();
		scopeCriteria.createCriteria().andShopIdEqualTo(shop.getSid());
		RecommendScopeSetting  scope = this.recommendScopeSettingService.queryByCriteria( scopeCriteria );
		
		RecommendModeSettingCriteria modeCriteria = new RecommendModeSettingCriteria();
		modeCriteria.createCriteria().andShopIdEqualTo(shop.getSid());
		RecommendModeSetting  mode = this.recommendModeSettingService.queryByCriteria( modeCriteria );
		
		ItemCriteria itemCriteria = new ItemCriteria();
		ItemCriteria.Criteria c = itemCriteria.createCriteria().andNickEqualTo(auth.getTaobaoUserNick());
		
		Boolean enabled = BooleanUtils.toBooleanObject(scope.getIsEnabled(), "1", "0", "");
		if( enabled ){
			String type = scope.getScopeType();
			//type:0,1,2
			c.andTypeEqualTo("1");
		}else{
			c.andTypeEqualTo("1");
		}
		
		Boolean saled = BooleanUtils.toBooleanObject(mode.getIsSaledFirst(),"1", "0", "");
		if(saled){
			c.andSoldQuantityGreaterThan(mode.getSaledNum());
		}
		Boolean storaged = BooleanUtils.toBooleanObject(mode.getIsStorageFiltered(), "1", "0", "");
		if(storaged){
			c.andNumGreaterThan(mode.getStorageNum());
		}
		Boolean offSh = BooleanUtils.toBooleanObject(mode.getIsOffshelvesFiltered(), "1", "0", "");
		if(offSh){
			
		}
		
		result = this.itemService.queryAllByCriteria(itemCriteria);
		
		return result;
	}
	

}
