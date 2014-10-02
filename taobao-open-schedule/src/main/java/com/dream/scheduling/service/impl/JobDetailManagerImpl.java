package com.dream.scheduling.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.dream.SchedulingException;
import com.dream.scheduling.service.AddJobService;
import com.dream.scheduling.service.JobDetailManager;
import com.dream.scheduling.service.PauseJobService;
import com.dream.AbstractService;
//import com.dream.task.model.Task;
//import com.dream.task.service.TaskLogEvent;
//import com.dream.task.service.TaskService;
/**
 * 
 * @author Frank
 * 实现说明：由Quartz定时调度本Service的manageJobDetail方法，通过TaskService查询数据库中的表B2C_TASK中状态为0的记录。
 * TASK_ID:任务编号
 * TASK_TYPE:任务类型
 * TASK_NAME:任务名称
 * TASK_JOB_DETAIL：对应于Spring容器中的Bean的ID
 * TASK_PARAMS:TASK_JOB_DETAIL中对应的Service方法的参数
 * TASK_CRON_EXPRESSION:CRON表达式
 * SCHEDULE_STATUS:是否已经被调度
 * TASK_DESC：任务描述
 */
public class JobDetailManagerImpl extends AbstractService implements JobDetailManager, ApplicationContextAware{
	/**
	 * Trigger的后缀
	 */
	private static final String TRIGGER_SURFIX = "_trigger";
	private static final String TRIGGER_GROUP_SURFIX = "_triggerGroup";
	private ApplicationContext applicationContext;
	
	public void manageJobDetail() throws SchedulingException {
		
//		logger.info("Job manager starting scan the jobs DB.");
//		
//		//查询未调度的任务
//		TaskService taskService = (TaskService)applicationContext.getBean("taskService");
//		List<Task> tasks = taskService.getTaskList();
//		
//		//调度完成的Task列表，需要有AbstractJobService批量更新状态
//		List<Task> taskScheduled = null;
//		
//		//循环将执行任务调度
//		for(Task task : tasks){
//			if(taskScheduled==null){
//				taskScheduled = new ArrayList<Task>();
//			}
//			//自定义的Job名称
//			String jobName = task.getTaskName();
//			if(StringUtils.isEmpty(jobName)){
//				logger.warn("The job name can not be null or empty.");
//				continue;
//			}
//			//Job Detail名称，用来指定tabao-open-schedule-timer.xml中的com.icbc.tabao-open.scheduling.BeanInvokingJobDetailFactoryBean的名称
//			String jobDetailName = task.getTaskJobDetail();
//			//Job Detail名称反射调用Service的接口方法的参数
//			Object[] jobParams = null;
//			if(task.getTaskParams()!=null){
//				//TODO something need to handle.
//				jobParams = new String[]{task.getTaskParams()};
//			}
//			//Cron表达式，用来生成CronTrigger
//			String cronExpression = task.getTaskCronExpression();
//			//Job的类型，ADD，PAUSE，RESUME
//			String jobType = task.getTaskType();
//			//按照任务类型执行对应的调度
//			
//			switch (TaskType.getTaskType(jobType)) {
//			case ADD: {
//				logger.info("Add..................");
//				if(StringUtils.isEmpty(jobDetailName)||StringUtils.isEmpty(cronExpression)){
//					logger.warn("When task type is add,the job detail name and cron can not be null");
//					continue;
//				}
//				AddJobService addJobService = (AddJobService)applicationContext.getBean("addJobService");
//				try{
//					addJobService.scheduleJob(jobName,jobDetailName, jobParams, jobName+TRIGGER_SURFIX, cronExpression,  jobName+TRIGGER_GROUP_SURFIX);
//				}catch(Exception e){
//					logger.error("Can not add a job.", e);
//					continue;
//				}
//				task.setScheduleStatus(Constants.SCHEDULE_DONE);
//				taskScheduled.add(task);
//				break;
//			}
//			case PAUSE: {
//				logger.info("Pause..................");
//				PauseJobService pauseJobService = (PauseJobService)applicationContext.getBean("pauseJobService");
//				try{
//					pauseJobService.stopJob(jobName);
//				}catch(Exception e){
//					logger.error("Can not pause a job.", e);
//					continue;
//				}
//				taskScheduled.add(task);
//				task.setScheduleStatus(Constants.SCHEDULE_DONE);
//				break;
//			}
//			case RESUME: {
//				logger.info("Resume..................");
//				if(StringUtils.isEmpty(cronExpression)){
//					logger.warn("When task type is resume,the cron can not be null");
//					continue;
//				}
//				AddJobService addJobService = (AddJobService)applicationContext.getBean("addJobService");
//				try{
//					addJobService.rescheduleJob(jobName, jobName+TRIGGER_SURFIX,jobName+TRIGGER_GROUP_SURFIX,   cronExpression);
//				}catch(Exception e){
//					logger.error("Can not resume a job.", e);
//					continue;
//				}
//				taskScheduled.add(task);
//				task.setScheduleStatus(Constants.SCHEDULE_DONE);
//				break;
//			}
//			case NOVALUE: {
//				break;
//			}
//			default:
//			}
//		}
//		//更新已经调度的任务
//		if(taskScheduled!=null&&!taskScheduled.isEmpty())
//			taskService.updateTaskList(taskScheduled);
//		
//		logger.info("Job manager ending scan the jobs DB.");
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
	
	
	public enum TaskType {
		/**
		 * 新增
		 */
		ADD,
		/**
		 * 暂停
		 */
		PAUSE,
		/**
		 * 重新开始
		 */
		RESUME,
		/**
		 * 其他
		 */
		NOVALUE;

		public static TaskType getTaskType(String s) {
			try {
				return valueOf(s.toUpperCase());
			}
			catch (Exception ex) {
				return NOVALUE;
			}
		}
	}
	
}
