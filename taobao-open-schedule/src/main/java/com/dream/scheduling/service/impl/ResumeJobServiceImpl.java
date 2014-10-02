
package com.dream.scheduling.service.impl;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.dream.AbstractService;
import com.dream.scheduling.dao.JobDetailsDAO;
import com.dream.scheduling.service.ResumeJobService;



/**
 * @author Wenjianchuang
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 */
public class ResumeJobServiceImpl extends AbstractService implements ResumeJobService {

    private Scheduler scheduler;
    private JobDetailsDAO jobDetailsDAO;



    public JobDetailsDAO getJobDetailsDAO() {

        return jobDetailsDAO;
    }



    public void setJobDetailsDAO(JobDetailsDAO jobDetailsDAO) {

        this.jobDetailsDAO = jobDetailsDAO;
    }



    public Scheduler getScheduler() {

        return scheduler;
    }



    public void setScheduler(Scheduler scheduler) {

        this.scheduler = scheduler;
    }



    public void resumeJob(String jobName, String groupName) throws SchedulerException {

        this.scheduler.resumeJob(jobName, groupName);

//        JobDetailsId jobDetailsId = new JobDetailsId(jobName, groupName);
//
//        JobDetails transientInstance = this.jobDetailsDAO.findById(jobDetailsId);
//
//        transientInstance.setState(scheduler.getTriggerState(jobName, groupName) + "");
//
//        this.jobDetailsDAO.save(transientInstance);

    }



    public void resumeJob(String jobName) throws SchedulerException {

        resumeJob(jobName, this.scheduler.DEFAULT_GROUP);

    }



    public void resumeJob(JobDetail jobDatail) throws SchedulerException {

        resumeJob(jobDatail.getName(), jobDatail.getName());

    }

}
