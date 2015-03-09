package com.dream.messaging.client.mina;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;


public class MinaLongConnClientStarter {
	
	private GenericObjectPool<IoSession> pool = null;
	
	public MinaLongConnClientStarter(){
		MinaLongConnClientFactory factory =  new MinaLongConnClientFactory();
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(5);
		config.setMaxIdle(4);
		config.setMinIdle(2);
		config.setMaxWaitMillis(1000);
		pool = new GenericObjectPool<IoSession>(factory,config);
	}
	
	public static void main(String[] args) throws Exception{
		MinaLongConnClientStarter t = new MinaLongConnClientStarter();
		IoSession iosession = null;
		try{
			IoSession iosession1 = t.getPool().borrowObject();
			IoSession iosession2 = t.getPool().borrowObject();
			IoSession iosession3 = t.getPool().borrowObject();
			IoSession iosession4 = t.getPool().borrowObject();
			IoSession iosession5 = t.getPool().borrowObject();
//			iosession = t.getPool().borrowObject();
//			iosession = t.getPool().borrowObject();
//			iosession = t.getPool().borrowObject();
//			iosession = t.getPool().borrowObject();
//			
			t.getPool().returnObject(iosession1);
			t.getPool().returnObject(iosession2);
			t.getPool().returnObject(iosession3);
			t.getPool().returnObject(iosession4);
			t.getPool().returnObject(iosession5);
//			IoBuffer buffer = IoBuffer.allocate(4);
//            buffer.putInt(1);
////            buffer.putInt(2);
////            buffer.putInt(3);
////            buffer.putInt(4);
//            buffer.flip();
//            WriteFuture future = iosession.write(buffer);
//            future.await();
			test();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//t.getPool().returnObject(iosession);
		}
		
		
		try{
			iosession = t.getPool().borrowObject();
			IoBuffer buffer = IoBuffer.allocate(4);
            buffer.putInt(1);
//            buffer.putInt(2);
//            buffer.putInt(3);
//            buffer.putInt(4);
            buffer.flip();
            WriteFuture future =  iosession.write(buffer);
            future.await();
		}finally{
			t.getPool().returnObject(iosession);
		}
	}

	public GenericObjectPool<IoSession> getPool() {
		return pool;
	}

	public void setPool(GenericObjectPool<IoSession> pool) {
		this.pool = pool;
	}
	
	private static void  test(){
		String i = "";
		String j = "";
	}
}
