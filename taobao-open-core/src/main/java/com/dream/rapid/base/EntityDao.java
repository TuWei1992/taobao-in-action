package com.dream.rapid.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
/**
 * @author Frank
 * 所有DAO的接口，定义了增删改查一些常用的操作
 */
public interface EntityDao <E,PK extends Serializable,Criteria>{
	/**
	 * 批量插入全部列
	 * @param entities
	 * @throws DataAccessException
	 */
	public void insertBatch(Collection<E> entities) throws DataAccessException;
	
	/**
	 * 批量插入部分列
	 * @param entities
	 * @throws DataAccessException
	 */
	public void insertSelectiveBatch(Collection<E> entities) throws DataAccessException;
	
    /**
     * 根据主键批量更新全部列
     * @param entities
     * @throws DataAccessException
     */
	public void updateByPrimaryKeyBatch(Collection<E> entities) throws DataAccessException;
	
	/**
     * 根据主键批量更新部分列
     * @param entities
     * @throws DataAccessException
     */
	public void updateByPrimaryKeySelectiveBatch(Collection<E> entities) throws DataAccessException;
	
	/**
     * 根据主键批量删除数据
     * @param entities
     * @throws DataAccessException
     */
	public void deleteByPrimaryKeyBatch(Collection<PK> ids) throws DataAccessException;
	

	/**
	 * 根据id检查是否插入或是更新数据
	 * @param record
	 * @throws DataAccessException
	 */
	public void insertOrUpdate(E record,Criteria criteria) throws DataAccessException;
	
	/**
	 * 检查数据是否存在
	 * @param record
	 * @param uniquePropertyNames
	 * @return
	 * @throws DataAccessException
	 */
	public boolean isUnique(E record, String uniquePropertyNames) throws DataAccessException;
	
	/**
	 * 用于hibernate.flush() 有些dao实现不需要实现此类  
	 * @throws DataAccessException
	 */
	public void flush() throws DataAccessException;
	

	/**
	 * 按条件查询数量  
	 * @throws DataAccessException
	 */
    public int countByCriteria(Criteria criteria)  throws DataAccessException;

    /**
	 * 按条件删除  
	 * @throws DataAccessException
	 */
    public int deleteByCriteria(Criteria criteria)  throws DataAccessException;

    /**
	 * 按主键删除
	 * @param id
	 * @throws DataAccessException
	 */
    public int deleteByPrimaryKey(PK key)  throws DataAccessException;

	/**
	 * 插入数据 
	 * @param record
	 * @throws DataAccessException
	 */
    public void insert(E record)  throws DataAccessException;
    
	/**
	 * 插入部分列 
	 * @param record
	 * @throws DataAccessException
	 */
    public void insertSelective(E record) throws DataAccessException;
    
	/**
	 * 根据条件查询
	 * @param criteria
	 * @throws DataAccessException
	 */
    public List<E> selectByCriteria(Criteria criteria) ;

    /**
	 * 按主键查询
	 * @param key
	 * @return
	 * @throws DataAccessException
	 */
    public E selectByPrimaryKey(PK key);

    /**
	 * 按主键更新部分列
	 * @param record
	 * @throws DataAccessException
	 */
    public int updateByPrimaryKeySelective(E record) ;

    /**
	 * 按主键更新全部列
	 * @param record
	 * @throws DataAccessException
	 */
    public int updateByPrimaryKey(E record);
    
    /**
     * 按条件更新部分列
     * @param record
     * @param criteria
     * @return
     */
    public int updateByCriteriaSelective(E record, Criteria criteria);
    
    /**
     * 按条件更新全部列
     * @param record
     * @param criteria
     * @return
     */
    public int updateByCriteria(E record, Criteria criteria);
	
}
