package com.dream.scheduling.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ScheduleAdvice implements ThrowsAdvice, MethodBeforeAdvice,
		AfterReturningAdvice, ApplicationContextAware
{

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		// TODO Auto-generated method stub
		
	}
//
//	private final static Logger logger = LoggerFactory
//			.getLogger(ScheduleAdvice.class);
//	private String batchCode;
//	private String batchTime;
//	// 应用监控全局上报开关
//	private String amcOnOff;
//	@Autowired
//	private TaskService taskService;
//	@Autowired
//	private AppMonitorBatchInfo appMonitorBatchInfo;
//	private ApplicationContext applicationContext;
//
//	@Override
//	public void afterReturning(Object arg0, Method method, Object[] arg2,
//			Object arg3) throws Throwable {
//		// 获取定时任务名
//		String jobDetileName = JobContextContainer.getJobDetileName();
//		// 获取任务
//		Task task = new Task();
//		task.setTaskName(jobDetileName);
//		task.setTaskType("ADD");
//		task = taskService.getTaskForControl(task);
//		// 封装定时任务日志
//		if (null != task) {
//			String taskId = task.getTaskId();
//			TaskLog taskLog = new TaskLog();
//			taskLog.setTaskId(taskId);
//			taskLog.setIsException(Constants.TASK_LOG.IS_NOT_EXCEPTION);
//			taskLog.setOperateType(Constants.TASK_LOG.TASK_AUTO);
//			taskLog.setOperateTime(new Date());
//			// 发布定时任务日志添加
//			applicationContext.publishEvent(new TaskLogEvent(this, taskLog));
//		}
//		// 添加上送成功信息给应用监控
//		if (Constants.AMC_SWITCH.AMC_OFF.endsWith(amcOnOff)) {
//			return;
//		}
//		boolean partAmcOnOff = JobContextContainer.getAmcOnOff();
//		if (partAmcOnOff) {
//			appMonitorBatchInfo.sendXml(batchCode, batchTime, "0", "2", "", "",
//					"");
//		}
//		logger.debug("==============afterReturning is over==================");
//	}
//
//	@Override
//	public void before(Method method, Object[] arg1, Object arg2)
//			throws Throwable {
//		CommomProperty instane = CommomProperty.getDBManager();
//		amcOnOff = instane.getsmsProperty("AmcOnOff");
//		// 批量开始执行时间
//		this.batchTime = "0";
//		// 初始化上送应用监控编号
//		this.batchCode = JobContextContainer.getJobDetileName();
//		logger.debug("==============before==================");
//	}
//
//	/**
//	 * 
//	 * @param method
//	 *            执行的方法
//	 * @param atgs
//	 *            方法参数
//	 * @param target
//	 *            代理的目标对象
//	 * @param throwable
//	 *            产生的异常
//	 * @throws tabao-openServiceException
//	 */
//
//	public void afterThrowing(Method method, Object[] atgs, Object target,
//			Exception throwable) {
//		logger.debug("==============afterThrowing is running==================");
//		logger.debug("****************抛出的异常：" + throwable.getMessage()
//				+ ">>>>>>>>>>>>");
//		// 获取定时任务名
//		String jobDetileName = JobContextContainer.getJobDetileName();
//		// 获取任务
//		Task task = new Task();
//		task.setTaskName(jobDetileName);
//		task.setTaskType("ADD");
//		task = taskService.getTaskForControl(task);
//		// 封装定时任务日志
//		if (null != task) {
//			String taskId = task.getTaskId();
//			TaskLog taskLog = new TaskLog();
//			taskLog.setTaskId(taskId);
//			taskLog.setIsException(Constants.TASK_LOG.IS_EXCEPTION);
//			taskLog.setOperateType(Constants.TASK_LOG.TASK_AUTO);
//			taskLog.setOperateTime(new Date());
//			taskLog.setExceptionDetail(throwable.getMessage());
//			// 发布定时任务日志添加
//			applicationContext.publishEvent(new TaskLogEvent(this, taskLog));
//		}
//
//		/**
//		 * 对Exception 运行时异常 tabao-openServiceException 回滚异常 处理方法抛给应用监控错误信息
//		 * tabao-openNoRollbackException 不回滚异常 处理方法抛给应用监控错误信息 根据开关判断是否要上送引用监控
//		 */
//		if (Constants.AMC_SWITCH.AMC_OFF.endsWith(amcOnOff)) {
//			return;
//		}
//		boolean partAmcOnOff = JobContextContainer.getAmcOnOff();
//		if (partAmcOnOff) {
//			String code = "";
//			String message = "";
//			if (throwable instanceof ICBCServiceException) {
//				// 产生错误 发送错误信息
//				code = ((ICBCServiceException) throwable).getCode();
//				message = throwable.getMessage();
//				logger.debug("=============异常类型为:" + throwable.toString());
//				logger.debug("=============上送给应用监控的错误信息:" + message);
//				// 发送给应用监控
//				appMonitorBatchInfo.sendXml(batchCode, batchTime, "0", "1",
//						code + ":" + message, "", "");
//			} else if (throwable instanceof ICBCNoRollbackServiceException) {
//				// 产生错误 发送错误信息
//				code = ((ICBCNoRollbackServiceException) throwable).getCode();
//				message = throwable.getMessage();
//				logger.debug("=============异常类型为:" + throwable.toString());
//				logger.debug("=============上送给应用监控的错误信息:" + message);
//				// 发送给应用监控
//				appMonitorBatchInfo.sendXml(batchCode, batchTime, "0", "3",
//						code + ":" + message, "", "");
//			} else {
//				// 产生错误 发送错误信息
//				message = throwable.getMessage();
//				logger.debug("=============异常类型为:" + throwable.toString());
//				logger.debug("=============上送给应用监控的错误信息:" + message);
//				// 发送给应用监控
//				appMonitorBatchInfo.sendXml(batchCode, batchTime, "0", "1",
//						code + ":" + message, "", "");
//			}
//		}
//		logger.debug("==============afterThrowing is over==================");
//	}
//
//	public void setAppMonitorBatchInfo(AppMonitorBatchInfo appMonitorBatchInfo) {
//		this.appMonitorBatchInfo = appMonitorBatchInfo;
//	}
//
//	@Override
//	public void setApplicationContext(ApplicationContext arg0)
//			throws BeansException {
//		this.applicationContext = arg0;
//	}
}
