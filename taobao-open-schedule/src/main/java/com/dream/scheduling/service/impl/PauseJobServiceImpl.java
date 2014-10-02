
package com.dream.scheduling.service.impl;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.dream.SchedulingException;
import com.dream.scheduling.service.PauseJobService;
import com.dream.AbstractService;



/**
 * @author Wenjianchuang
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 */
public class PauseJobServiceImpl extends AbstractService implements PauseJobService {

    /* *
     * (non-Javadoc)
     * @see com.icbc.tabao-open.scheduling.service.PauseJobService#stopJob(java.lang.String)
     **/
    public void stopJob(String jobName) throws SchedulingException {
        stopJob(jobName, Scheduler.DEFAULT_GROUP);

    }

    /* *
     * 
     * @see com.icbc.tabao-open.scheduling.service.PauseJobService#stopJob(java.lang.String, java.lang.String)
     * 
     **/
    public void stopJob(String jobName, String jobGroup) throws SchedulingException {
    	 Scheduler scheduler = (Scheduler) applicationContext.getBean("quartzScheduler");
        try {
        	scheduler.pauseJob(jobName, jobGroup);
		} catch (SchedulerException e) {
			logger.error("Pause a job failed", e);
		}
    }

    /* *
     * 
     * (non-Javadoc)
     * @see com.icbc.tabao-open.scheduling.service.PauseJobService#stopJob(org.quartz.JobDetail)
     */
    public void stopJob(JobDetail jobDetail) throws SchedulingException {
        stopJob(jobDetail.getName(), jobDetail.getGroup());
    }
}
