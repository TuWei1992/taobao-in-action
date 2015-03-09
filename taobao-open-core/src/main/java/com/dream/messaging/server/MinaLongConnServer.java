/**
 * 
 */
package com.dream.messaging.server;

import java.io.IOException;

/**
 * @author Frank
 *
 */
public class MinaLongConnServer {
	
	private static final int PORT = 8002;
	   
    public void start()throws IOException{
//       IoAcceptor acceptor = new NioSocketAcceptor();
// 
//       acceptor.getFilterChain().addLast("logger", new LoggingFilter());
//       acceptor.getFilterChain().addLast("codec", newProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
// 
//       acceptor.setHandler(new MinaLongConnServerHandler());
//       acceptor.getSessionConfig().setReadBufferSize(2048);
//       acceptor.bind(new InetSocketAddress(PORT));
//       logger.debug("Listeningon port " + PORT);
    }
}
