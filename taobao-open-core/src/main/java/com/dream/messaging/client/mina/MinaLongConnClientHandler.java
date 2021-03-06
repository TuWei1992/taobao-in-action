package com.dream.messaging.client.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaLongConnClientHandler extends IoHandlerAdapter {
	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	 

    public MinaLongConnClientHandler() {
    }

 

    @Override

    public void sessionOpened(IoSession session) {
    }

 

    @Override
    public void messageReceived(IoSession session, Object message) {
       logger.info("Messagereceived in the client..");
       logger.info("Message is:" + message.toString());
       session.setAttribute("result", message.toString());
    }
    
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
    	logger.info("Message sent.");
    }
 

    @Override

    public void exceptionCaught(IoSession session, Throwable cause) {
       session.close(true);
    }
}
