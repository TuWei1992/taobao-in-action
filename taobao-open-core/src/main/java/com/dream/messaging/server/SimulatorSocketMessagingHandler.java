
package com.dream.messaging.server;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.dream.messaging.ByteArrayMessage;
import com.dream.messaging.Message;
import com.dream.messaging.engine.MessageEngine;
import com.dream.messaging.engine.TranCodeAware;
import com.dream.messaging.sender.socket.SocketUtil;


/**
 * 
 * @author Frank
 *
 */
public  class SimulatorSocketMessagingHandler extends SocketMessagingHandler{

	private static final String DIRECTORY = "directory";
	private static final String VERSION = "version";
	private static final String HEADER = "header";
	
	private static final String SURFIX = ".txt";

	@Override
	public void handle() throws Exception {
		
 		InputStream is = null;
		
		OutputStream os = null;
		try{
			is = socket.getInputStream();
			byte[] bytes =new byte[1024];
			int len = 0 ;
			if(is!=null){
		       len=is.read(bytes);
		    }
			logger.debug("Get bytes with length:"+len+",and message is " + new String(bytes));
			Message rsp = new ByteArrayMessage();
			byte[] bs =new byte[len-8];
			System.arraycopy(bytes, 8, bs, 0, len-8);
			rsp.setContent(bs);
			String version = properties.getProperty(VERSION, "1.0");
			
			Map condition = new HashMap();
			String header = properties.getProperty(HEADER, "TIA");
			Object headerObj = engine.extractHeader(rsp, header, version, condition);
			
			String transCode = "20"+((TranCodeAware) headerObj).getTranCode().toString();
			
			logger.debug("Get request is:"+transCode);

			Object request = engine.toObject(rsp, transCode, version, MessageEngine.MTYPE_REQUEST, condition);
			
			logger.debug("Get request is:"+request);
			
			String dir = properties.getProperty(DIRECTORY);
			if(dir == null){
				logger.error("Please set directory!!!!!!");
			}
		
			String path = dir+File.separator+transCode+SURFIX;
			logger.info("The response message path is:"+path);
			File file = new File(path);
			byte[] content = FileUtils.readFileToByteArray(file);
			String encoding = properties.getProperty("encoding","UTF-8");
			os = socket.getOutputStream();
			SocketUtil.writeByte(os, content, encoding);
		}catch(Exception e){
			logger.error("error.", e);
		}finally{
			if(is!=null){
				is.close();
			}
			if(os!=null){
				os.close();
			}
		}
		
		
		
		
		
	}
	
	
}
