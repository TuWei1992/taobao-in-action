/*
 * @(#)SchedulingService.java  2011/1/25
 *
 * Copyright (c) 2011. icbc., Ltd. All rights reserved.
 */

package com.dream.scheduling.service;

import java.util.Date;

import com.dream.SchedulingException;

public interface SchedulingService {
    /**
     * 根据 Quartz Cron Expression 调度任务
     * @param jobName  调度任务需要执行的服务
     * @param arguments  任务参数（必须可序列化）
     * @param triggerName  触发器名称
     * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
     */
    void scheduleJob(String jobName, Object[] arguments, String triggerName, String cronExpression) throws SchedulingException;
    
    /**
     * 根据 Quartz Cron Expression 调度任务
     * @param jobName  调度任务需要执行的服务
     * @param arguments  任务参数（必须可序列化）
     * @param triggerName  触发器名称
     * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
     * @param triggerGroup 触发器组名称
     */
     void scheduleJob(String jobName, Object[] arguments, String triggerName, String cronExpression, String triggerGroup) throws SchedulingException;

     /**
      * 根据 Quartz Cron Expression 重新调度任务
      * @param triggerName  触发器名称
      * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
      */
     void rescheduleJob(String triggerName, String cronExpression) throws SchedulingException;
     
     /**
      * 根据 Quartz Cron Expression 重新调度任务
      * @param triggerName  触发器名称
      * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
      * @param triggerGroup 触发器组名称
      */
      void rescheduleJob(String triggerName, String cronExpression, String triggerGroup) throws SchedulingException;

     /**
      * 在startTime时执行调度一次
      * @param jobName  调度任务需要执行的服务
      * @param arguments  任务参数（必须可序列化）
      * @param triggerName  触发器名称
      * @param startTime 调度开始时间
      */
     void scheduleJob(String jobName, Object[] arguments, String triggerName, Date startTime) throws SchedulingException;

     /**
      * 在startTime时执行调度一次
      * @param jobName  调度任务需要执行的服务
      * @param arguments  任务参数（必须可序列化）
      * @param triggerName  触发器名称
      * @param startTime 调度开始时间
      * @param triggerGroup 触发器组名称
      */
     void scheduleJob(String jobName, Object[] arguments, String triggerName, Date startTime, String triggerGroup) throws SchedulingException;

     /**
      * 在startTime时执行调度，endTime结束执行调度
      * @param jobName  调度任务需要执行的服务
      * @param arguments  任务参数（必须可序列化）
      * @param triggerName  触发器名称
      * @param startTime 调度开始时间
      * @param endTime 调度结束时间
      */
     void scheduleJob(String jobName, Object[] arguments, String triggerName, Date startTime, Date endTime) throws SchedulingException;

     /**
      * 在startTime时执行调度，endTime结束执行调度
      * @param jobName  调度任务需要执行的服务
      * @param arguments  任务参数（必须可序列化）
      * @param triggerName  触发器名称
      * @param startTime 调度开始时间
      * @param endTime 调度结束时间
      * @param triggerGroup 触发器组名称
      */
     void scheduleJob(String jobName, Object[] arguments, String triggerName, Date startTime, Date endTime, String triggerGroup) throws SchedulingException;

     /**
      * 在startTime时执行调度，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
      * @param jobName  调度任务需要执行的服务
      * @param arguments  任务参数（必须可序列化）
      * @param triggerName  触发器名称
      * @param startTime 调度开始时间
      * @param endTime 调度结束时间
      * @param repeatCount 重复执行次数
      * @param repeatInterval 执行时间间隔秒数
      */
     void scheduleJob(String jobName, Object[] arguments, String triggerName, Date startTime, Date endTime, 
             int repeatCount, long repeatInterval) throws SchedulingException;

     /**
      * 在startTime时执行调度，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
      * @param jobName  调度任务需要执行的服务
      * @param arguments  任务参数（必须可序列化）
      * @param triggerName  触发器名称
      * @param startTime 调度开始时间
      * @param endTime 调度结束时间
      * @param repeatCount 重复执行次数
      * @param repeatInterval 执行时间间隔秒数
      * @param triggerGroup 触发器组名称
      */
     void scheduleJob(String jobName, Object[] arguments, String triggerName, Date startTime, Date endTime, 
             int repeatCount, long repeatInterval, String triggerGroup) throws SchedulingException;

     /**
      * 重新调度，在startTime时执行一次
      * @param triggerName  触发器名称
      * @param startTime 调度开始时间
      */
     void rescheduleJob(String triggerName, Date startTime) throws SchedulingException;

     /**
      * 重新调度，在startTime时执行一次
      * @param triggerName  触发器名称
      * @param startTime 调度开始时间
      * @param triggerGroup 触发器组名称
      */
     void rescheduleJob(String triggerName, Date startTime, String triggerGroup) throws SchedulingException;

     /**
      * 重新调度，在startTime时执行，endTime结束执行
      * @param triggerName  触发器名称
      * @param startTime 调度开始时间
      * @param endTime 调度结束时间
      */
     void rescheduleJob(String triggerName, Date startTime, Date endTime) throws SchedulingException;

     /**
      * 重新调度，在startTime时执行，endTime结束执行
      * @param triggerName  触发器名称
      * @param startTime 调度开始时间
      * @param endTime 调度结束时间
      * @param triggerGroup 触发器组名称
      */
     void rescheduleJob(String triggerName, Date startTime, Date endTime, String triggerGroup) throws SchedulingException;

     /**
      * 重新调度，在startTime时执行，endTime结束执行，重复执行repeatCount次，每隔repeatInterval秒执行一次
      * @param triggerName  触发器名称
      * @param startTime 调度开始时间
      * @param endTime 调度结束时间
      * @param repeatCount 重复执行次数
      * @param repeatInterval 执行时间间隔秒数
      */
     void rescheduleJob(String triggerName, Date startTime, Date endTime, 
             int repeatCount, long repeatInterval) throws SchedulingException;

     /**
      * 重新调度，在startTime时执行，endTime结束执行，重复执行repeatCount次，每隔repeatInterval秒执行一次
      * @param triggerName  触发器名称
      * @param startTime 调度开始时间
      * @param endTime 调度结束时间
      * @param repeatCount 重复执行次数
      * @param repeatInterval 执行时间间隔秒数
      * @param triggerGroup 触发器组名称
      */
     void rescheduleJob(String triggerName, Date startTime, Date endTime, 
             int repeatCount, long repeatInterval, String triggerGroup) throws SchedulingException;
     

}
