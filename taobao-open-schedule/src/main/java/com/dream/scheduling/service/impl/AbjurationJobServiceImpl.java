
package com.dream.scheduling.service.impl;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.SchedulingException;
import com.dream.scheduling.service.AbjurationJobService;



/**
 * @author Wenjianchuang
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 */
public class AbjurationJobServiceImpl implements AbjurationJobService {

    private Logger logger = LoggerFactory.getLogger(AbjurationJobServiceImpl.class);

    private Scheduler scheduler;



    public Scheduler getScheduler() {

        return scheduler;
    }



    public void setScheduler(Scheduler scheduler) {

        this.scheduler = scheduler;
    }



    public void unscheduleJob(String triggerName) throws SchedulerException {

        unscheduleJob(triggerName, Scheduler.DEFAULT_GROUP);
    }



    public void unscheduleJob(String jobName, String jobGroup) throws SchedulerException {

        this.scheduler.deleteJob(jobName, jobGroup);
    }

}
