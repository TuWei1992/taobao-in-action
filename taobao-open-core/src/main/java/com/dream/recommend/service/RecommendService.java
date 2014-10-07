/*
 * Powered By [com.dream.rapid]
 * GitHub: https://github.com/wallace46886799
 * Since 2008 - 2014
 */

package com.dream.recommend.service;

import java.util.ArrayList;
import java.util.Date;
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
import com.dream.recommend.model.RecommendScopeCategory;
import com.dream.recommend.model.RecommendScopeCategoryCriteria;
import com.dream.recommend.model.RecommendScopeSetting;
import com.dream.recommend.model.RecommendScopeSettingCriteria;
import com.dream.recommend.model.RecommendSpecified;
import com.dream.recommend.model.RecommendSpecifiedCriteria;
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
	private RecommendScopeCategoryService recommendScopeCategoryService;
	
	@Autowired
	private RecommendModeSettingService recommendModeSettingService;
	
	@Autowired
	private RecommendSpecifiedService recommendSpecifiedService;
	
	
	@Autowired
	private RecommendLogService recommendLogService;
	
	@Autowired
	private ItemService itemService;
	
	public void setRecommendScopeSettingService(
			RecommendScopeSettingService recommendScopeSettingService) {
		this.recommendScopeSettingService = recommendScopeSettingService;
	}
	
	public void setRecommendScopeCategoryService(
			RecommendScopeCategoryService recommendScopeCategoryService) {
		this.recommendScopeCategoryService = recommendScopeCategoryService;
	}

	public void setRecommendModeSettingService(
			RecommendModeSettingService recommendModeSettingService) {
		this.recommendModeSettingService = recommendModeSettingService;
	}
	
	public void setRecommendSpecifiedService(
			RecommendSpecifiedService recommendSpecifiedService) {
		this.recommendSpecifiedService = recommendSpecifiedService;
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
		
		List<Item> must = querySepcifiedItems(shop,auth,"1");
		items.addAll(must);
		
		
		List<Item> mustNot = querySepcifiedItems(shop,auth,"0");
		
		if(mustNot== null || mustNot.isEmpty()){
			return items;
		}
		
		int size = items.size();
		for(int i = size-1; i>0 ;i--){
			Item item = items.get(i);
			if(mustNot.contains(item)){
				items.remove(item);
			}
		}
		
		return items;
	}

	private List<Item> querySepcifiedItems(Shop shop,Auth auth,String type) {
		List<Item> items = null;
		RecommendSpecifiedCriteria criteria = new RecommendSpecifiedCriteria();
		criteria.createCriteria().andShopIdEqualTo(shop.getSid()).andTypeEqualTo(type);
		List<RecommendSpecified> result = this.recommendSpecifiedService.queryAllByCriteria(criteria);
		
		List<Long> itemIds = new ArrayList<Long>();
		for(RecommendSpecified re : result){
			itemIds.add(re.getItemId());
		}
		
		if(!itemIds.isEmpty()){
			ItemCriteria iCriteria = new ItemCriteria();
			iCriteria.createCriteria().andNumIidIn(itemIds);
			items =  itemService.queryAllByCriteria(iCriteria);
		}
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
		
		
		//设置推荐范围的筛选条件
		Boolean enabled = BooleanUtils.toBooleanObject(scope.getIsEnabled(), "1", "0", "");
		if( enabled ){
			//0：出售中的商品；1：特定分类下的商品，2：其他设置
			String type = scope.getScopeType();
			if("0".equals(type)){
				//筛选商品表中的出售中的商品，即type为1的商品
				c.andApproveStatusEqualTo("onsale");
			}else if("1".equals(type)){
				//筛选特定分类下的商品，
				RecommendScopeCategoryCriteria rCriteria = new RecommendScopeCategoryCriteria();
				rCriteria.createCriteria().andShopIdEqualTo(shop.getSid());
				List<RecommendScopeCategory> reuslt = this.recommendScopeCategoryService.queryAllByCriteria(rCriteria);
				List<String> cids = new ArrayList<String>();
				for(RecommendScopeCategory cat : reuslt){
					cids.add(cat.getCatId());
				}
				c.andCidIn(cids).andApproveStatusEqualTo("onsale");
			}else{
				c.andApproveStatusEqualTo("onsale");
			}
		}else{
			c.andApproveStatusEqualTo("onsale");
		}
		
		
		//设置推荐方式的筛选条件；按销量排行，取前N位的商品
		Boolean saled = BooleanUtils.toBooleanObject(mode.getIsSaledFirst(),"1", "0", "");
		if(saled){
			itemCriteria.setOrderByClause("SOLD_QUANTITY");
		}
		
		//设置库存的筛选条件；取库存数大于M的商品
		Boolean storaged = BooleanUtils.toBooleanObject(mode.getIsStorageFiltered(), "1", "0", "");
		if(storaged){
			c.andNumGreaterThan(mode.getStorageNum().toString());
		}
		
		//设置下架时间的筛选条件，过滤掉下架时间早于now+L秒的商品
		Boolean offSh = BooleanUtils.toBooleanObject(mode.getIsOffshelvesFiltered(), "1", "0", "");
		if(offSh){
			Integer offshelves = mode.getOffshelvesTime();
			Long current = System.currentTimeMillis() + offshelves*1000;
			Date offshelvesTime = new Date(current);
			c.andDelistTimeGreaterThan(offshelvesTime);
		}
		result = this.itemService.queryAllByCriteria(itemCriteria);
		return result;
	}
	

}
