/**
 * 
 */
package com.dream.messaging.server.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
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
       acceptor.setHandler(new MinaLongConnServerHandler());
       //acceptor.getFilterChain().addLast("logger", new LoggingFilter());
       //acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
       //acceptor.getSessionConfig().setReadBufferSize(2048);
       acceptor.bind(new InetSocketAddress(PORT));
       logger.debug("Listening on port {}", PORT);
    }
    
    public void stop()throws IOException{
          logger.debug("Stop listening on port {}", PORT);
     }

}
