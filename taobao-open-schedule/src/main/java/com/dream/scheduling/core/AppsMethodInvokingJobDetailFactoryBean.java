
package com.dream.scheduling.core;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MethodInvoker;



/**
 * @author Wenjianchuang
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 */
public class AppsMethodInvokingJobDetailFactoryBean extends MethodInvokingJobDetailFactoryBean {

    private static final Logger logger = LoggerFactory.getLogger(AppsMethodInvokingJobDetailFactoryBean.class);



    public void afterPropertiesSet() throws Exception {

        super.afterPropertiesSet();

        logger.info("origin jobClass : " + ((JobDetail) super.getObject()).getJobClass().getName());

        if (MethodInvokingJob.class.getName().equals(((JobDetail) super.getObject()).getJobClass().getName())) {
            ((JobDetail) super.getObject()).setJobClass(AppsMethodInvokingJob.class);
        } else {
            ((JobDetail) super.getObject()).setJobClass(AppsStatefulMethodInvokingJob.class);
        }
        logger.info("new jobClass : " + ((JobDetail) super.getObject()).getJobClass().getName());
    }


    public static class AppsMethodInvokingJob extends MethodInvokingJob {

        private static final Logger logger = LoggerFactory.getLogger(AppsMethodInvokingJobDetailFactoryBean.class);
        private MethodInvoker methodInvoker;
        private String errorMessage;



        /** 
         * Set the MethodInvoker to use. 
         */
        public void setMethodInvoker(MethodInvoker methodInvoker) {

            this.methodInvoker = methodInvoker;
            this.errorMessage = "Could not invoke method '" + this.methodInvoker.getTargetMethod()
                    + "' on target object [" + this.methodInvoker.getTargetObject() + "]";
        }



        /** 
         * Invoke the method via the MethodInvoker. 
         */
        protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

            Date startDate = new Date();
            String taskName = methodInvoker.getTargetClass().getName();
            try {
                if (logger.isInfoEnabled()) {
                    logger.info(taskName + " job start at " + startDate);
                }

                // 调用具体task执行method代码
                this.methodInvoker.invoke();

            } catch (Exception ex) {
                logger.error(taskName + " accounted a error : " + this.errorMessage, ex);
                throw new JobExecutionException(this.errorMessage, ex, false);
            } finally {
                if (logger.isInfoEnabled()) {
                    logger.info(taskName + " job end   at " + new Date());
                }

            }
        }
    }

    public static class AppsStatefulMethodInvokingJob extends AppsMethodInvokingJob {
    }
}
