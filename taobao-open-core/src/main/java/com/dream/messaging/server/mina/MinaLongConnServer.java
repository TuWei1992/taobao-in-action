/**
 * 
 */
package com.dream.messaging.server.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Frank
 *
 */
public class MinaLongConnServer {
	
	private static final Logger logger = LoggerFactory.getLogger(MinaLongConnServer.class);
	
	private static final int PORT = 8002;

    public void start()throws IOException{

       IoAcceptor acceptor = new NioSocketAcceptor();

       acceptor.getFilterChain().addLast("logger", new LoggingFilter());

       acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

       acceptor.setHandler(new MinaLongConnServerHandler());

       acceptor.getSessionConfig().setReadBufferSize(2048);

       acceptor.bind(new InetSocketAddress(PORT));

       logger.debug("Listeningon port {}", PORT);

    }

}
