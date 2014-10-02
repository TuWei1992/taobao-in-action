package com.dream.scheduling.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dream.SchedulingException;
import com.dream.scheduling.service.AddJobService;


public class HelloApplicationListenerImpl {
    

    private AddJobService schedulingService_;

    private String cronExpression_;

    private Log log_ = LogFactory
            .getLog(getClass());

    public void setSchedulingService(AddJobService schedulingService) {
        this.schedulingService_ = schedulingService;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression_ = cronExpression;
    }

    public void init() {
//        try {
//            if (log_.isDebugEnabled()) {
//                log_
//                        .debug("***********>> Scheduling job HelloTask");
//            }
//
////            schedulingService_.scheduleJob("helloTask1", null,
////                    "helloTaskTrriger1", cronExpression_);
//
//            if (log_.isDebugEnabled()) {
//                log_
//                        .debug("***********<< Scheduled job HelloTask");
//            }
//        } catch (SchedulingException e) {
//            if (log_.isErrorEnabled()) {
//                log_.error("Error scheduling job prodOrderFreezScannTask", e);
//            }
//        }
    }

}
