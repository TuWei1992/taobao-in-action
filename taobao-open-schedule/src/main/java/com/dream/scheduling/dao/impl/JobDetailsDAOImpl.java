package com.dream.scheduling.dao.impl;

import com.dream.scheduling.dao.JobDetailsDAO;




/**
 	* A data access object (DAO) providing persistence and search support for I2sTmcJobDetails entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.soft.I2sTmcJobDetails
  * @author MyEclipse Persistence Tools 
 */

public class JobDetailsDAOImpl implements JobDetailsDAO {/*
    
    
    private static final Logger log = LoggerFactory.getLogger(JobDetailsDAOImpl.class);
    // property constants
    public static final String DESCRIPTION = "description";
    public static final String JOB_CLASS_NAME = "jobClassName";
    public static final String JOB_DATA = "jobData";



    protected void initDao() {

        // do nothing
    }



    public void save(JobDetails transientInstance) {

        log.debug("saving JobDetails instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }



    public void delete(JobDetails persistentInstance) {

        log.debug("deleting JobDetails instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }



    public JobDetails findById(JobDetailsId id) {

        log.debug("getting JobDetails instance with id: " + id);
        try {
            JobDetails instance = (JobDetails) getHibernateTemplate().get("com.icbc.tabao-open.scheduling.model.JobDetails", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }



    public List findByExample(JobDetails instance) {

        log.debug("finding JobDetails instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }



    public List findByProperty(String propertyName, Object value) {

        log.debug("finding JobDetails instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from JobDetails as model where model." + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }



    public List findByDescription(Object description) {

        return findByProperty(DESCRIPTION, description);
    }



    public List findByJobClassName(Object jobClassName) {

        return findByProperty(JOB_CLASS_NAME, jobClassName);
    }



    public List findByJobData(Object jobData) {

        return findByProperty(JOB_DATA, jobData);
    }



    public List findAll() {

        log.debug("finding all JobDetails instances");
        try {
            String queryString = "from JobDetails";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }



    public JobDetails merge(JobDetails detachedInstance) {

        log.debug("merging JobDetails instance");
        try {
            JobDetails result = (JobDetails) getHibernateTemplate().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }



    public void attachDirty(JobDetails instance) {

        log.debug("attaching dirty JobDetails instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }



    public void attachClean(JobDetails instance) {

        log.debug("attaching clean JobDetails instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }



    public static JobDetailsDAO getFromApplicationContext(ApplicationContext ctx) {

        return (JobDetailsDAO) ctx.getBean("I2sTmcJobDetailsDAO");
    }

*/}