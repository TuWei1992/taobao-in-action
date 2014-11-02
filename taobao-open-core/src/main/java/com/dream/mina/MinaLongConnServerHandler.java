package com.dream.mina;

import java.net.InetSocketAddress;
import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaLongConnServerHandler extends IoHandlerAdapter {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	 

    @Override

    public void sessionOpened(IoSession session) {

       InetSocketAddress remoteAddress = (InetSocketAddress)session.getRemoteAddress();

       String clientIp = remoteAddress.getAddress().getHostAddress();

       logger.info("Long connection server opened session id = {}",session.getId());

       logger.info("接收来自客户端 :" + clientIp + "的连接.");

       Initialization init = Initialization.getInstance();

       Map<String, IoSession> clientMap =init.getClientMap();

       clientMap.put(clientIp, session);

    }

 

    @Override

    public void messageReceived(IoSession session, Object message) {

       logger.info("Message received in the long connection server..");

       String expression = message.toString();

       logger.info("Message is:" + expression);

       IoSession shortConnSession =(IoSession) session.getAttribute("shortConnSession");

       logger.info("Short connection server session id = {}",shortConnSession.getId());

       shortConnSession.write(expression);

    }

 

    @Override

    public void sessionIdle(IoSession session, IdleStatus status) {

       logger.info("Disconnectingthe idle.");

       // disconnect an idle client

       session.close(true);

    }

 

    @Override

    public void exceptionCaught(IoSession session, Throwable cause) {

       // close the connection onexceptional situation

       logger.warn(cause.getMessage(), cause);

       session.close(true);

    }

}
