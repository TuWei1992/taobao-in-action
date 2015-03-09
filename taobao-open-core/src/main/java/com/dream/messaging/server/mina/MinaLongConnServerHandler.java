package com.dream.messaging.server.mina;

import java.net.InetSocketAddress;
import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.messaging.pool.Initialization;

public class MinaLongConnServerHandler extends IoHandlerAdapter {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    @Override
    public void sessionOpened(IoSession session) {
       InetSocketAddress remoteAddress = (InetSocketAddress)session.getRemoteAddress();
       String clientIp = remoteAddress.getAddress().getHostAddress();
       logger.info("Long connection server opened session id = {}",session.getId());
       logger.info("Connection from :" + clientIp);
       Initialization init = Initialization.getInstance();
       Map<String, IoSession> clientMap =init.getClientMap();
       clientMap.put(clientIp, session);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
       logger.info("Message received in the long connection server..");
       session.write(message);
    }

    @Override

    public void sessionIdle(IoSession session, IdleStatus status) {
       logger.info("Disconnectingthe idle.");
       session.close(true);

    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
       logger.warn(cause.getMessage(), cause);
       session.close(true);

    }
    
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
    	logger.info("Message sent.");
    }

}
