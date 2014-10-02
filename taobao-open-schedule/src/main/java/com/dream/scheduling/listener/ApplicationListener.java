package com.dream.scheduling.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.scheduling.service.SchedulingService;


public class ApplicationListener {
    

    private SchedulingService schedulingService;

    private String cronExpression;

    private Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

    public void setSchedulingService(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public void init() throws Exception {
            if (logger.isDebugEnabled()) {
                logger
                        .debug("***********>> Scheduling job");
            }

            schedulingService.scheduleJob("helloTask", null,
                    "helloTaskTrriger", cronExpression);

            if (logger.isDebugEnabled()) {
                logger
                        .debug("***********<< Scheduled job ");
            }
    }

}
