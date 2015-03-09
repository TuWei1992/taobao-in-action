package com.dream.messaging.client.mina;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeoutException;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MinaLongConnClientFactory extends BasePooledObjectFactory<IoSession>{
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	private String hostName = "localhost";
    private int port = 8002;
    private int connectionCount;
    private long connectTimeoutMillis = 10000L;
    private long writeTimeoutMillis = 10000L;
    private int idleTime = 10; //秒
    private ProtocolCodecFilter protocolCodecFilter;
    private IoHandler ioHandler = new MinaLongConnClientHandler();
    
    public String getHostName(){
        return hostName;
    }

    public void setHostName(String hostName){
        this.hostName = hostName;
    }

    public int getPort(){
        return port;
    }

    public void setPort(int port){
        this.port = port;
    }

    public int getConnectionCount(){
        return connectionCount;
    }

    public void setConnectionCount(int connectionCount){
        this.connectionCount = connectionCount;
    }

    public long getConnectTimeoutMillis(){
        return connectTimeoutMillis;
    }

    public void setConnectTimeoutMillis(long connectTimeoutMillis){
        this.connectTimeoutMillis = connectTimeoutMillis;
    }

    public long getWriteTimeoutMillis(){
        return writeTimeoutMillis;
    }

    public void setWriteTimeoutMillis(long writeTimeoutMillis){
        this.writeTimeoutMillis = writeTimeoutMillis;
    }

    public int getIdleTime(){
        return idleTime;
    }

    public void setIdleTime(int idleTime){
        this.idleTime = idleTime;
    }

    public ProtocolCodecFilter getProtocolCodecFilter(){
        return protocolCodecFilter;
    }

    public void setProtocolCodecFilter(ProtocolCodecFilter protocolCodecFilter){
        this.protocolCodecFilter = protocolCodecFilter;
    }

    public IoHandler getIoHandler(){
        return ioHandler;
    }

    public void setIoHandler(IoHandler ioHandler){
        this.ioHandler = ioHandler;
    }
	
	public IoSession create() throws Exception{
        NioSocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(connectTimeoutMillis);
        connector.getSessionConfig().setBothIdleTime(idleTime);
//        =connector.getSessionConfig()
        connector.getSessionConfig().setTcpNoDelay(true);
//        connector.getFilterChain().addLast("codec", protocolCodecFilter);
        connector.setHandler(ioHandler);
        ConnectFuture future = connector.connect(new InetSocketAddress(hostName, port));
        boolean completed = future.awaitUninterruptibly(connectTimeoutMillis);
        if(!completed){
            throw new TimeoutException();
        }
        IoSession ioSession = future.getSession();
        return ioSession;
    }


	@Override
    public PooledObject<IoSession> wrap(IoSession ioSession){
        return new DefaultPooledObject<IoSession>(ioSession);
    }
	
	
	 /**
     *  No-op.
     *
     *  @param p ignored
     */
    @Override
    public void activateObject(PooledObject<IoSession> p) throws Exception {
    	logger.debug("Activating pooled object:{}",p.getObject().getId());
    }

    /**
     *  No-op.
     *
     * @param p ignored
     */
    @Override
    public void passivateObject(PooledObject<IoSession> p) throws Exception {
    	logger.debug("Passivating pooled object:{}",p.getObject().getId());
    }
    
    
    /**
     *  No-op.
     *
     *  @param p ignored
     */
    @Override
    public void destroyObject(PooledObject<IoSession> p)throws Exception  {
    	logger.debug("Destroying pooled object:{}",p.getObject().getId());
    	p.getObject().close(true);
    }

    /**
     * This implementation always returns {@code true}.
     *
     * @param p ignored
     *
     * @return {@code true}
     */
    @Override
    public boolean validateObject(PooledObject<IoSession> p) {
    	//如果连接对象为空或者连接状态为已释放
    	if(p.getObject() == null || !p.getObject().isConnected()){
    		return false;
    	}
    	logger.debug("Validating pooled object:{}",p.getObject().getId());
        return true;
    }

}
