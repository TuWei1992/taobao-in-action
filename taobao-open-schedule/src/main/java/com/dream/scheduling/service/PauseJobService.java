
package com.dream.scheduling.service;

import org.quartz.JobDetail;

import com.dream.SchedulingException;



/**
 * @author Wenjianchuang
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 */
public interface PauseJobService {

    /**
     * <b>Method Function Description:</b><br>
     * TODO Pleas Add your method function description here
     * @param jobName
     * <br><b>Method Logic Description:</b>
     * TODO Pleas Add your method Logic description here, if needed
     */
    void stopJob(String jobName)throws SchedulingException;



    /**
     * <b>Method Function Description:</b><br>
     * TODO Pleas Add your method function description here
     * @param jobName
     * @param jobGroup
     * <br><b>Method Logic Description:</b>
     * TODO Pleas Add your method Logic description here, if needed
     */
    void stopJob(String jobName, String jobGroup)throws SchedulingException;



    /**
     * <b>Method Function Description:</b><br>
     * TODO Pleas Add your method function description here
     * @param jobDetail
     * <br><b>Method Logic Description:</b>
     * TODO Pleas Add your method Logic description here, if needed
     */
    void stopJob(JobDetail jobDetail)throws SchedulingException;

}
