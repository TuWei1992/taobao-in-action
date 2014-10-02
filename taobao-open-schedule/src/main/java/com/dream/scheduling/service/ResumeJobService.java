
package com.dream.scheduling.service;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;



/**
 * @author Wenjianchuang
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 */
public interface ResumeJobService  {

    /**
     * <b>Method Function Description:</b><br>
     * TODO Pleas Add your method function description here
     * @param jobName
     * @param groupName
     * @throws SchedulerException
     * <br><b>Method Logic Description:</b>
     * TODO Pleas Add your method Logic description here, if needed
     */
    void resumeJob(String jobName, String groupName) throws SchedulerException;



    /**
     * <b>Method Function Description:</b><br>
     * TODO Pleas Add your method function description here
     * @param jobName
     * @throws SchedulerException
     * <br><b>Method Logic Description:</b>
     * TODO Pleas Add your method Logic description here, if needed
     */
    void resumeJob(String jobName) throws SchedulerException;



    /**
     * <b>Method Function Description:</b><br>
     * TODO Pleas Add your method function description here
     * @param jobDatail
     * @throws SchedulerException
     * <br><b>Method Logic Description:</b>
     * TODO Pleas Add your method Logic description here, if needed
     */
    void resumeJob(JobDetail jobDatail) throws SchedulerException;

}
