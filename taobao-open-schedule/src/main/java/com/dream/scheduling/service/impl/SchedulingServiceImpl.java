/*
 * @(#)SchedulingServiceImpl.java  2011/1/25
 *
 * Copyright (c) 2011. icbc., Ltd. All rights reserved.
 */

package com.dream.scheduling.service.impl;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.ErrorCode;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.dream.SchedulingException;
import com.dream.scheduling.service.SchedulingService;



public class SchedulingServiceImpl implements SchedulingService, ApplicationContextAware {
	private static Logger logger=Logger.getLogger(SchedulingServiceImpl.class);
    private ApplicationContext context_ = null;
    private Scheduler scheduler_ = null;
    
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        context_ = context;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler_ = scheduler;
    }

    public void scheduleJob(String jobName, Object[] arguments, String triggerName, String cronExpression) throws SchedulingException {
        scheduleJob(jobName, arguments, triggerName, cronExpression, Scheduler.DEFAULT_GROUP);
    }

    public void scheduleJob(String jobName, Object[] arguments, String triggerName, String cronExpression, String triggerGroup) throws SchedulingException {
        JobDetail jobDetail = (JobDetail) context_.getBean(jobName);
        
        if (arguments != null) {
            jobDetail.getJobDataMap().put("arguments", arguments);
        }
        
        try {
            // 使用TriggerName作为作业
            jobDetail.setName(triggerName);
            scheduler_.addJob(jobDetail, true);

            CronTrigger cronTrigger = new CronTrigger(triggerName, triggerGroup, jobDetail.getName(),
                    Scheduler.DEFAULT_GROUP);
            cronTrigger.setCronExpression(cronExpression);
            if (scheduler_.getTrigger(triggerName, triggerGroup) == null) {
                scheduler_.scheduleJob(cronTrigger);
            } else {
                scheduler_.rescheduleJob(triggerName, triggerGroup, cronTrigger);
            }
        } catch (SchedulerException e) {
            logger.error("Exception raised-------->",e);
            throw new SchedulingException("", "",
                                          new Object[]{jobName, triggerName, cronExpression, triggerGroup});
        } catch (ParseException e) {
            logger.error("Exception raised-------->",e);
            throw new SchedulingException("", "",
                    new Object[]{jobName, triggerName, cronExpression, triggerGroup});
        }
    }

    public void rescheduleJob(String triggerName, String cronExpression) throws SchedulingException {
        rescheduleJob(triggerName, cronExpression, Scheduler.DEFAULT_GROUP);
    }

    public void rescheduleJob(String triggerName, String cronExpression, String triggerGroup)
            throws SchedulingException {
        try {
            CronTrigger newTrigger = new CronTrigger(triggerName, triggerGroup, triggerName,
                    Scheduler.DEFAULT_GROUP);
            newTrigger.setCronExpression(cronExpression);
            scheduler_.rescheduleJob(triggerName, triggerGroup, newTrigger);
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

    public void scheduleJob(String jobName, Object[] arguments, String triggerName, Date startTime) throws SchedulingException {
        scheduleJob(jobName, arguments, triggerName, startTime, Scheduler.DEFAULT_GROUP);
    }

    public void scheduleJob(String jobName, Object[] arguments, String triggerName, Date startTime, String triggerGroup)
            throws SchedulingException {
        scheduleJob(jobName, arguments, triggerName, startTime, null, triggerGroup);
        
    }

    public void scheduleJob(String jobName, Object[] arguments, String triggerName, Date startTime, Date endTime)
            throws SchedulingException {
        scheduleJob(jobName, arguments, triggerName, startTime, endTime, Scheduler.DEFAULT_GROUP);
    }

    public void scheduleJob(String jobName, Object[] arguments, String triggerName, Date startTime, Date endTime, String triggerGroup)
            throws SchedulingException {
        scheduleJob(jobName, arguments, triggerName, startTime, endTime, 0, 1L, triggerGroup);
    }

    public void scheduleJob(String jobName, Object[] arguments, String triggerName, Date startTime, Date endTime, int repeatCount,
            long repeatInterval) throws SchedulingException {
        scheduleJob(jobName, arguments, triggerName, startTime, endTime, repeatCount, repeatInterval, Scheduler.DEFAULT_GROUP);
    }

    public void scheduleJob(String jobName, Object[] arguments, String triggerName, Date startTime, Date endTime, int repeatCount,
            long repeatInterval, String triggerGroup) throws SchedulingException {
        JobDetail jobDetail = (JobDetail) context_.getBean(jobName);

        if (arguments != null) {
            jobDetail.getJobDataMap().put("arguments", arguments);
        }
        
        try {
            // 使用TriggerName作为作业
            jobDetail.setName(triggerName);
            scheduler_.addJob(jobDetail, true);

            SimpleTrigger simpleTrigger = new SimpleTrigger(triggerName, triggerGroup, jobDetail.getName(),
                    Scheduler.DEFAULT_GROUP, startTime, endTime, repeatCount, repeatInterval*1000L);
            if (scheduler_.getTrigger(triggerName, triggerGroup) == null) {
                scheduler_.scheduleJob(simpleTrigger);
            } else {
                scheduler_.rescheduleJob(triggerName, triggerGroup, simpleTrigger);
            }
        } catch (SchedulerException e) {
            logger.error("Exception raised-------->",e);
            throw new SchedulingException("", "",
                                          new Object[]{
                                              jobName,
                                              triggerName, 
                                              formatDate(startTime), 
                                              formatDate(endTime),
                                              triggerGroup});
        }
    }

    private static String formatDate(Date d) {
        if (d == null) {
            return "unlimited";
        } else {
            return DateFormatUtils.format(d, "yyyy-MM-dd HH:mm:ss.SSS");
        }
    }
    
    public void rescheduleJob(String triggerName, Date startTime) throws SchedulingException {
        rescheduleJob(triggerName, startTime, Scheduler.DEFAULT_GROUP);
    }

    public void rescheduleJob(String triggerName, Date startTime, String triggerGroup)
            throws SchedulingException {
        rescheduleJob(triggerName, startTime, null, triggerGroup);
    }

    public void rescheduleJob(String triggerName, Date startTime, Date endTime) throws SchedulingException {
        rescheduleJob(triggerName, startTime, endTime, Scheduler.DEFAULT_GROUP);
    }

    public void rescheduleJob(String triggerName, Date startTime, Date endTime, String triggerGroup)
            throws SchedulingException {
        rescheduleJob(triggerName, startTime, endTime, 0, 1L, triggerGroup);
    }

    public void rescheduleJob(String triggerName, Date startTime, Date endTime, int repeatCount, long repeatInterval)
            throws SchedulingException {
        rescheduleJob(triggerName, startTime, endTime, repeatCount, repeatInterval, Scheduler.DEFAULT_GROUP);
    }

    public void rescheduleJob(String triggerName, Date startTime, Date endTime, int repeatCount, long repeatInterval,
            String triggerGroup) throws SchedulingException {
        try {
            SimpleTrigger newTrigger = new SimpleTrigger(triggerName, triggerGroup, triggerName,
                    Scheduler.DEFAULT_GROUP, startTime, endTime, repeatCount, repeatInterval*1000L);
            scheduler_.rescheduleJob(triggerName, triggerGroup, newTrigger);
        } catch (SchedulerException e) {
            logger.error("Exception raised-------->",e);
            throw new SchedulingException("", "",
                                          new Object[]{
                                              triggerName, 
                                              formatDate(startTime), 
                                              formatDate(endTime),
                                              triggerGroup});
        }
    }


}
