/*
 * @(#)SchedulingService.java 2011/1/25
 * 
 * Copyright (c) 2011. icbc., Ltd. All rights reserved.
 */

package com.dream.scheduling.service;

import com.dream.SchedulingException;



/**
 * @author Wenjianchuang
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 */
public interface AddJobService {

    /**
     * 根据 Quartz Cron Expression 调度任务
     * @param jobName  调度任务需要执行的服务
     * @param jobDetailName  调度任务需要执行的服务
     * @param arguments  任务参数（必须可序列化）
     * @param triggerName  触发器名称
     * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
     * @param triggerGroup 触发器组名称
     */
    void scheduleJob(String jobName,String jobDetailName,Object[] arguments, String triggerName, String cronExpression, String triggerGroup)
            throws SchedulingException;
    
    /**
     * 
     * @param triggerName
     * @param cronExpression
     * @param triggerGroup
     * @throws SchedulingException
     */
     void rescheduleJob(String jobName,String triggerName, String triggerGroup, String cronExpression)
            throws SchedulingException;


}
