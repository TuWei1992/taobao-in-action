package com.dream.rapid.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.dream.common.dao.SysParamDao;
import com.dream.common.model.SysParam;
import com.dream.item.model.Item;
import com.dream.item.model.ItemCriteria;
/**
 * @author Frank
 */
@Transactional
public abstract class BaseService <E,PK extends Serializable,Criteria> implements ApplicationContextAware {
	
	protected ApplicationContext applicationContext = null;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 获取当前模块的DAO
	 * @return
	 */
	protected abstract EntityDao<E,PK,Criteria> getEntityDao();
	

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		if (applicationContext != null) {
			this.applicationContext = applicationContext;
		}
	}
	
	/**
	 * 根据主键查询，无事务
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	@Transactional(readOnly=true)
	public E queryById(PK id) throws DataAccessException{
		if(id==null){
			throw new IllegalArgumentException("Can not get entity with null");
		}
		return (E)getEntityDao().selectByPrimaryKey(id);
	}
	
	/**
	 * 查询所有数据，无事务
	 * @return
	 * @throws DataAccessException
	 */
	@Transactional(readOnly=true)
	public List<E> queryAll() throws DataAccessException{
		return getEntityDao().selectByCriteria(null);
	}
	
	
	/**
	 * 查询所有数据，无事务
	 * @return
	 * @throws DataAccessException
	 */
	@Transactional(readOnly=true)
	public List<E> queryAllByCriteria(Criteria criteria) throws DataAccessException{
		return getEntityDao().selectByCriteria(criteria);
	}
	
	
	/**
	 * 查询所有数据，无事务
	 * @return
	 * @throws DataAccessException
	 */
	@Transactional(readOnly=true)
	public E queryByCriteria(Criteria criteria) throws DataAccessException{
		List<E>  result = getEntityDao().selectByCriteria(criteria);
		if(result != null && result.size()!=0){
			return	result.get(0);
		}else{
			return null;
		}
	}
	
	
	
	/**
	 * 判断是否存在
	 * @param entity
	 * @param uniquePropertyNames
	 * @return
	 * @throws DataAccessException
	 */
	@Transactional(readOnly=true)
	public boolean isUnique(E entity, String uniquePropertyNames) throws DataAccessException {
		if(entity == null || uniquePropertyNames == null){
			throw new IllegalArgumentException("Can not get entity with null");
		}
		return getEntityDao().isUnique(entity, uniquePropertyNames);
	}
	
	/**
	 * 根据id检查是否插入或是更新数据
	 * @param entity
	 * @throws DataAccessException
	 */
	public void saveOrUpdate(E entity,Criteria criteria) throws DataAccessException{
		if(entity == null || criteria == null ){
			throw new IllegalArgumentException("Can not save or update entity with null");
		}
		getEntityDao().insertOrUpdate(entity,criteria);
	}
	
	/**
	 * 插入数据
	 * @param entity
	 * @throws DataAccessException
	 */
	public void save(E entity) throws DataAccessException{
		if(entity == null ){
			throw new IllegalArgumentException("Can not save entity with null");
		}
		getEntityDao().insert(entity);
	}
	
	/**
	 * 批量插入数据
	 * @param entities
	 * @throws DataAccessException
	 */
	public void saveBatch(Collection<E> entities) throws DataAccessException{
		if(entities == null || entities.isEmpty()){
			throw new IllegalArgumentException("Can not save batch entities with null or empty collection.");
		}
		getEntityDao().insertBatch(entities);
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 * @throws DataAccessException
	 */
	public void removeById(PK id) throws DataAccessException{
		if( id == null ){
			throw new IllegalArgumentException("Can not delete entity with id.");
		}
		getEntityDao().deleteByPrimaryKey(id);
	}
	
	
	/**
	 * 根据主键删除
	 * @param id
	 * @throws DataAccessException
	 */
	public void removeByCriteria(Criteria criteria) throws DataAccessException{
		if( criteria == null ){
			throw new IllegalArgumentException("Can not delete entity with id.");
		}
		getEntityDao().deleteByCriteria(criteria);
	}
	
	
	
	/**
	 * 先删除，然后批量插入
	 * @param criteria
	 * @param entities
	 */
	public void removeSaveBatch(Criteria criteria ,List<E> entities){
		this.removeByCriteria(criteria);
		this.saveBatch(entities);
	}
	
	
	
	/**
	 * 更新数据
	 * @param entity
	 * @throws DataAccessException
	 */
	public void update(E entity) throws DataAccessException{
		if(entity == null ){
			throw new IllegalArgumentException("Can not update entity with null");
		}
		getEntityDao().updateByPrimaryKey(entity);
	}
	
	/**
	 * 批量更新数据
	 * @param entity
	 * @throws DataAccessException
	 */
	public void updateBatch(Collection<E> entities) throws DataAccessException{
		if(entities == null || entities.isEmpty()){
			throw new IllegalArgumentException("Can not save batch entities with null or empty collection.");
		}
		getEntityDao().updateByPrimaryKeyBatch(entities);
	}
	
	
	/**
	 * 根据Key获取参数值
	 * @param key
	 * @return
	 */
	protected String getSysParamValue(String key){
		if(key == null){
			throw new IllegalArgumentException("Can not get sys param with null.");
		}
		SysParamDao dao = this.applicationContext.getBean("sysParamDao", SysParamDao.class);
		SysParam sysParam = dao.getByKey(key);
		if(sysParam == null){
			throw new IllegalArgumentException("***Can not get sys param with key:"+key);
		}
		return sysParam.getValue();
	}
}
