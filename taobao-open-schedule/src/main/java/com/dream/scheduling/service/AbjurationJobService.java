
package com.dream.scheduling.service;

import org.quartz.SchedulerException;



/**
 * @author Wenjianchuang
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 */
public interface AbjurationJobService {

    /**
     * <b>Method Function Description:</b><br>
     * TODO Pleas Add your method function description here
     * @param triggerName
     * @throws SchedulerException
     * <br><b>Method Logic Description:</b>
     * TODO Pleas Add your method Logic description here, if needed
     */
    public void unscheduleJob(String triggerName) throws SchedulerException;



    /**
     * <b>Method Function Description:</b><br>
     * TODO Pleas Add your method function description here
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     * <br><b>Method Logic Description:</b>
     * TODO Pleas Add your method Logic description here, if needed
     */
    public void unscheduleJob(String jobName, String jobGroup) throws SchedulerException;

}
