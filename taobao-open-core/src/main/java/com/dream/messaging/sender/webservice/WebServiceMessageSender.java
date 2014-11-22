/**************************************************************************
 * Licensed Material - Property of Dawn InfoTek                           *
 * Copyright (c) Dawn InfoTek Inc. 1999, 2004, 2008 -All rights reserved. * 
 * (<http://www.dawninfotek.com>)                                         *
 *                                                                        *
 * This file contains proprietary intellectual property of                *
 * Dawn InfoTek Inc. The contents of and information in this file         *
 * is only to be used in conjunction with a valid Dawn4J license          *
 * as specified in the Dawn4J license agreement. All other use            *
 * is prohibited.                                                         *
 **************************************************************************/
package com.dream.messaging.sender.webservice;

import org.apache.log4j.Logger;

import com.dream.Constants;
import com.dream.messaging.Message;
import com.dream.messaging.MessagingException;
import com.dream.messaging.StringMessage;
import com.dream.messaging.sender.MessageSender;
import com.dream.messaging.utils.DESUtil;

/**
 * 
 * <p>webService通信服务
 *
 * @author yandx
 */
public class WebServiceMessageSender extends MessageSender{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WebServiceMessageSender.class);
	
	

	public WebServiceMessageSender() {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dawninfotek.base.packet.sender.MessageSender#send(com.dawninfotek.base.packet.common.Message)
	 */
	@Override
	protected Message send(Message req) throws MessagingException {
		//SystemParamInfoService syService = (SystemParamInfoService)this.applicationContext.getBean("systemParamInfoService");
//		SystemParam param = syService.getSystemParamInfo(Constants.APP_ID, Constants.APP_KEY, "48", "48001");
//		String key = param.getParamvalue();
//		//String key = "ghjbraq2";
//		String action = req.getMessageId();
//		if(action.equals("EMS01")){
//			action = "1";
//		}else if(action.equals("EMS02")){
//			action = "2";
//		}else if(action.equals("EMS03")){
//			action = "3";
//		}else if(action.equals("EMS04")){
//			action = "4";
//		}else if(action.equals("EMS06")){
//			action = "6";
//		}else if(action.equals("EMS07")){
//			action = "7";
//		}else if(action.equals("EMS11")){
//			action = "8";
//		}
//        String xmldate = req.getContent().toString();
//        String resString = null;
//        Message rsp = new StringMessage();
//        try {
//			xmldate = DESUtil.toHexString(DESUtil.encrypt(xmldate, key));
//			if (logger.isDebugEnabled()) {
//				logger.debug("===requestMessage:"+xmldate+"===");
//			}
//			resString = cmsbWebservice.cmsbMainWs(action, xmldate);
//			if (logger.isDebugEnabled()) {
//				logger.debug("===responseMessage:"+resString+"===");
//			}
//			//resString = DESUtil.decrypt(resString, key);
//			rsp.setContent(resString);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			try {
//				resString = "<outPutDatas><resultCode>999999</resultCode><reason>系统异常</reason></outPutDatas>";
//				rsp.setContent(resString);
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				throw new MessagingException("999999", "系统错误", e);
//			}
//		}
//		return rsp;	
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dawninfotek.base.packet.sender.MessageSender#send(java.lang.Object,
	 *      com.dawninfotek.base.packet.common.Message)
	 */
	@Override
	public Message send(Object obj, Message msgReq) throws MessagingException {
		return send(msgReq);
	}


}
