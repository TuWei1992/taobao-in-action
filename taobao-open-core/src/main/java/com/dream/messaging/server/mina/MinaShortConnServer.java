package com.dream.messaging.server.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaShortConnServer {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	private static final int PORT = 8001;

	   

    public void start()throws IOException{

       IoAcceptor acceptor = new NioSocketAcceptor();

 

       acceptor.getFilterChain().addLast("logger", new LoggingFilter());

       acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

 

       acceptor.setHandler(new MinaShortConnServerHandler());

       acceptor.getSessionConfig().setReadBufferSize(2048);

       acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 3);

       acceptor.bind(new InetSocketAddress(PORT));

       logger.debug("Listeningon port " + PORT);

    }
}
