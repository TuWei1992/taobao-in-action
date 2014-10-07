package com.dream.rapid.base;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.dream.rapid.page.Page;
import com.dream.rapid.page.PageRequest;
import com.dream.rapid.util.MapAndObject;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;


/**
 * 所有DAO的接口，通过泛型来实现常用的操作
 * @author Frank
 * @version 1.0
 */
public abstract class BaseIbatisDao<E,PK extends Serializable,Criteria> extends SqlMapClientDaoSupport implements EntityDao<E,PK,Criteria> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource(name = "sqlMapClient")
	private SqlMapClient sqlMapClient;
    
    
    /**
	 * 用于子类覆盖,在insert,update之前调用
	 * @param record
	 */
    protected abstract void prepareObjectForSaveOrUpdate(E record,E first);
    /**
     * 用于子类设置Namespace
     * @return
     */
    protected abstract String getIbatisSqlMapNamespace();
    
    /**
     * 创建按条件更新的包装类
     * @param record
     * @param criteria
     * @return
     */
    protected abstract Criteria getCriteriaParam(E record, Criteria criteria);
    

	@PostConstruct
	public void initSqlMapClient() {
		super.setSqlMapClient(sqlMapClient);
	}

    public int countByCriteria(Criteria criteria) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject(getIbatisSqlMapNamespace()+".countByCriteria", criteria);
        return count.intValue();
    }

    public int deleteByCriteria(Criteria criteria) {
        int rows = getSqlMapClientTemplate().delete(getIbatisSqlMapNamespace()+".deleteByCriteria", criteria);
        return rows;
    }

    public int deleteByPrimaryKey(PK key) {
        int rows = getSqlMapClientTemplate().delete(getIbatisSqlMapNamespace()+".deleteByPrimaryKey", key);
        return rows;
    }

    public Object insert(E record) {
      return getSqlMapClientTemplate().insert(getIbatisSqlMapNamespace()+".insert", record);
    }

    public Object insertSelective(E record) {
       return getSqlMapClientTemplate().insert(getIbatisSqlMapNamespace()+".insertSelective", record);
    }
    
    public List<E> selectByCriteria(Criteria criteria) {
        List list = getSqlMapClientTemplate().queryForList(getIbatisSqlMapNamespace()+".selectByCriteria", criteria);
        return list;
    }

  
    public E selectByPrimaryKey(PK key) {
        E record = (E) getSqlMapClientTemplate().queryForObject(getIbatisSqlMapNamespace()+".selectByPrimaryKey", key);
        return record;
    }

   
    public int updateByPrimaryKeySelective(E record) {
        int rows = getSqlMapClientTemplate().update(getIbatisSqlMapNamespace()+".updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(E record) {
        int rows = getSqlMapClientTemplate().update(getIbatisSqlMapNamespace()+".updateByPrimaryKey", record);
        return rows;
    }
    
	public void deleteByPrimaryKeyBatch(final Collection<PK> ids) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			public Integer doInSqlMapClient(final SqlMapExecutor executor) throws SQLException {
				Integer result = 0;
				executor.startBatch();
				for (final PK id : ids) {
					int i = executor.delete(getIbatisSqlMapNamespace()+".deleteByPrimaryKey", id);
					logger.debug("Delete OK,{} row deleted.", i);
					result++;
				}
				executor.executeBatch();
				return result;
			}
		});
	}
    
	public void updateByPrimaryKeyBatch(final Collection<E> entities) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			public Integer doInSqlMapClient(final SqlMapExecutor executor) throws SQLException {
				Integer result = 0;
				executor.startBatch();
				for (final E entity : entities) {
					int i = executor.update(getIbatisSqlMapNamespace()+".updateByPrimaryKey", entity);
					logger.debug("Update OK,{} row updated.", i);
					result++;
				}
				executor.executeBatch();
				return result;
			}
		});
	}
    

	
	public void updateByPrimaryKeySelectiveBatch(final Collection<E> entities) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			public Integer doInSqlMapClient(final SqlMapExecutor executor) throws SQLException {
				Integer result = 0;
				executor.startBatch();
				for (final E entity : entities) {
					int i =  executor.update(getIbatisSqlMapNamespace()+".updateByPrimaryKeySelective", entity);
					logger.debug("Update OK,{} row updated.", i);
					result++;
				}
				executor.executeBatch();
				return result;
			}
		});
	}
	
	@Override
	public List<Object> insertBatch(final Collection<E> entities) throws DataAccessException {
		return getSqlMapClientTemplate().execute(new SqlMapClientCallback<List<Object>>() {
			public List<Object> doInSqlMapClient(final SqlMapExecutor executor) throws SQLException {
				Integer counter = 0;
				List<Object> result = new ArrayList<Object>();
				executor.startBatch();
				for (final E entity : entities) {
					Object o = executor.insert(getIbatisSqlMapNamespace()+".insert", entity);
					result.add(o);
					logger.debug("Insert OK,return the inserted primary key is {}", o);
					counter++;
				}
				executor.executeBatch();
				return result;
			}
		});
	}

	@Override
	public void insertSelectiveBatch(final Collection<E> entities)
			throws DataAccessException {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			public Integer doInSqlMapClient(final SqlMapExecutor executor) throws SQLException {
				Integer result = 0;
				executor.startBatch();
				for (final E entity : entities) {
					Object o = executor.insert(getIbatisSqlMapNamespace()+".insertSelective", entity);
					logger.debug("Insert OK,return the inserted primary key is {}", o);
					result++;
				}
				executor.executeBatch();
				return result;
			}
		});
		
	}
    public int updateByCriteriaSelective(E record, Criteria criteria) {
    	Criteria params = getCriteriaParam(record, criteria);
        int rows = getSqlMapClientTemplate().update(getIbatisSqlMapNamespace()+".updateByCriteriaSelective", params);
        return rows;
    }

    public int updateByCriteria(E record, Criteria criteria) {
    	Criteria params = getCriteriaParam(record, criteria);
        int rows = getSqlMapClientTemplate().update(getIbatisSqlMapNamespace()+".updateByCriteria", params);
        return rows;
    }
    

	@Override
	public void insertOrUpdate(E record,Criteria criteria) throws DataAccessException {
		List<E> result = this.selectByCriteria(criteria);
		if(result == null || result.isEmpty()){
			this.insertSelective(record);
		}else{
			this.prepareObjectForSaveOrUpdate(record, result.get(0));
			this.updateByPrimaryKeySelective(record);
		}
	}

	public boolean isUnique(E entity, String uniquePropertyNames) {
		throw new UnsupportedOperationException();
	}
	
	public void flush() {
		//ignore
	}
	
    @SuppressWarnings("unchecked")
    public Page<E> pageQuery(String statementName, PageRequest<E> pageRequest) {
		return pageQuery(getSqlMapClientTemplate(),getIbatisSqlMapNamespace()+"."+statementName,getIbatisSqlMapNamespace()+".countByCriteria",pageRequest);
	}
	
	@SuppressWarnings("unchecked")
	public Page pageQuery(SqlMapClientTemplate sqlMapClientTemplate,String statementName,String countStatementName, PageRequest pageRequest) {
		
		Number totalCount = (Number) sqlMapClientTemplate.queryForObject(countStatementName,pageRequest);
		if(totalCount == null || totalCount.longValue() <= 0) {
			return new Page(pageRequest,0);
		}
		
		Page page = new Page(pageRequest,totalCount.intValue());
		
		//其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用. 与getSqlMapClientTemplate().queryForList(statementName, parameterObject)配合使用
		Map otherFilters = new HashMap();
		otherFilters.put("offset", page.getFirstResult());
		otherFilters.put("pageSize", page.getPageSize());
		otherFilters.put("lastRows", page.getFirstResult() + page.getPageSize());
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		
		//混合两个filters为一个filters,MapAndObject.get()方法将在两个对象取值,Map如果取值为null,则再在Bean中取值
		Map parameterObject = new MapAndObject(otherFilters,pageRequest);
		List list = sqlMapClientTemplate.queryForList(statementName, parameterObject,page.getFirstResult(),page.getPageSize());
		page.setResult(list);
		return page;
	}

}
