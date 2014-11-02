/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.dream.item.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.item.dao.ItemDao;
import com.dream.item.model.Item;
import com.dream.item.model.ItemCriteria;
import com.dream.item.vo.query.ItemQuery;
import com.dream.oauth.OAuth;
import com.dream.rapid.base.BaseService;
import com.dream.rapid.base.EntityDao;
import com.dream.rapid.beanutils.BeanUtils;
import com.dream.rapid.page.Page;
import com.taobao.api.ApiException;
import com.taobao.api.request.ItemsInventoryGetRequest;
import com.taobao.api.request.ItemsOnsaleGetRequest;
import com.taobao.api.response.ItemsInventoryGetResponse;
import com.taobao.api.response.ItemsOnsaleGetResponse;

/**
 * @author Frank email:46886799#163.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class ItemService extends BaseService<Item,Item,ItemCriteria>{
	@Autowired
	private ItemDao topItemDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setTopItemDao(ItemDao dao) {
		this.topItemDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.topItemDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(ItemQuery query) {
		return topItemDao.findPage(query);
	}
	
	
	public void syncOnSaleItems(OAuth oauth) throws ApiException{
		ItemsOnsaleGetRequest request = new ItemsOnsaleGetRequest();
		request.setFields("approve_status,num_iid,title,nick,type,cid,pic_url,num,props,valid_thru,list_time,price,has_discount,has_invoice,has_warranty,has_showcase,modified,delist_time,postage_id,seller_cids,outer_id");
		ItemsOnsaleGetResponse response =  (ItemsOnsaleGetResponse) getTaobaoResponse(request,oauth.getAccessToken());
		List<com.taobao.api.domain.Item> items = response.getItems();
		List<com.dream.item.model.Item> localItems = new ArrayList<com.dream.item.model.Item>(items.size());
		for(com.taobao.api.domain.Item item : items){
			com.dream.item.model.Item dest =  new com.dream.item.model.Item();
			if(item == null|| item.getNumIid() == null){
				continue;
			}
			BeanUtils.copyProperties(dest,item);
			dest.setType("1");
			localItems.add(dest);
		}
		ItemCriteria criteria = new ItemCriteria();
		criteria.createCriteria().andNickEqualTo(oauth.getTaobaoUserNick()).andTypeEqualTo("1");
		this.removeSaveBatch(criteria,localItems);
	}
	
	public void syncInventoryItems(OAuth oauth) throws ApiException{
		ItemsInventoryGetRequest request=new ItemsInventoryGetRequest();
		request.setFields("approve_status,num_iid,title,nick,type,cid,pic_url,num,props,valid_thru,list_time,price,has_discount,has_invoice,has_warranty,has_showcase,modified,delist_time,postage_id,seller_cids,outer_id");
		ItemsInventoryGetResponse response =  (ItemsInventoryGetResponse) getTaobaoResponse(request, oauth.getAccessToken());
		List<com.taobao.api.domain.Item> items = response.getItems();
		List<com.dream.item.model.Item> localItems = new ArrayList<com.dream.item.model.Item>(items.size());
		for(com.taobao.api.domain.Item item : items){
			com.dream.item.model.Item dest =  new com.dream.item.model.Item();
			if(item == null|| item.getNumIid() == null){
				continue;
			}
			BeanUtils.copyProperties(dest ,item);
			dest.setType("0");
			localItems.add(dest);
		}
		ItemCriteria criteria = new ItemCriteria();
		criteria.createCriteria().andNickEqualTo(oauth.getTaobaoUserNick()).andTypeEqualTo("0");
		this.removeSaveBatch(criteria,localItems);
	}
	
	
	public void syncItems(OAuth oauth) throws ApiException{
		syncOnSaleItems(oauth);
		syncInventoryItems(oauth);
	}
	

	
}
