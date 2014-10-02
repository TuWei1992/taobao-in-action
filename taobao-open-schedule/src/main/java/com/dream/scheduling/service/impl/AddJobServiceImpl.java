/*
 * @(#)SchedulingServiceImpl.java 2011/1/25
 * 
 * Copyright (c) 2011. icbc., Ltd. All rights reserved.
 */

package com.dream.scheduling.service.impl;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.dream.AbstractService;
import com.dream.SchedulingException;
import com.dream.scheduling.service.AddJobService;



/**
 * @author Wenjianchuang
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 */
public class AddJobServiceImpl extends AbstractService implements AddJobService{


    public void scheduleJob(String jobName,String jobDetailName,Object[] arguments, String triggerName, String cronExpression,
            String triggerGroup) throws SchedulingException {
    	//
        JobDetail jobDetail = (JobDetail) applicationContext.getBean(jobDetailName);

        if (arguments != null) {
            jobDetail.getJobDataMap().put("arguments", arguments);
        }
        try {
            jobDetail.setName(jobName);
            Scheduler scheduler = (Scheduler) applicationContext.getBean("quartzScheduler");
            scheduler.addJob(jobDetail, true);

            CronTrigger cronTrigger = new CronTrigger(triggerName, triggerGroup, jobDetail.getName(),
                    Scheduler.DEFAULT_GROUP);
            cronTrigger.setCronExpression(cronExpression);
            if (scheduler.getTrigger(triggerName, triggerGroup) == null) {
            	scheduler.scheduleJob(cronTrigger);
            } else {
            	scheduler.rescheduleJob(triggerName, triggerGroup, cronTrigger);
            }
        } catch (SchedulerException e) {
            logger.error("Exception raised-------->", e);
            throw new SchedulingException("", "", new Object[] { jobName, triggerName, cronExpression, triggerGroup });
        } catch (ParseException e) {
            logger.error("Exception raised-------->", e);
            throw new SchedulingException("", "", new Object[] { jobName, triggerName, cronExpression, triggerGroup });
        }
    }
    
    
    public void rescheduleJob(String jobName,String triggerName, String triggerGroup, String cronExpression)
            throws SchedulingException {
        try {
            CronTrigger newTrigger = new CronTrigger(triggerName, triggerGroup, jobName,
                    Scheduler.DEFAULT_GROUP);
            newTrigger.setCronExpression(cronExpression);
            Scheduler scheduler = (Scheduler) applicationContext.getBean("quartzScheduler");
            scheduler.rescheduleJob(triggerName, triggerGroup, newTrigger);
        } catch (SchedulerException e) {
            logger.error("Exception raised-------->",e);
            throw new SchedulingException("", "",
                    new Object[]{triggerName, cronExpression, triggerGroup});
        } catch (ParseException e) {
            logger.error("Exception raised-------->",e);
            throw new SchedulingException("", "",
                    new Object[]{triggerName, cronExpression, triggerGroup});
        }
    }




}
