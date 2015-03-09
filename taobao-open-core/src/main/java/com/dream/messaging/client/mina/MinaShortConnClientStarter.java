package com.dream.messaging.client.mina;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;


public class MinaShortConnClientStarter {
	
	private GenericObjectPool<IoSession> pool = new GenericObjectPool<IoSession>(new MinaLongConnClientFactory());
	
	public static void main(String[] args) throws Exception{
		MinaShortConnClientStarter t = new MinaShortConnClientStarter();
		IoSession iosession = null;
		try{
			iosession = t.getPool().borrowObject();
			IoBuffer buffer = IoBuffer.allocate(4);
            buffer.putInt(1);
//            buffer.putInt(2);
//            buffer.putInt(3);
//            buffer.putInt(4);
            buffer.flip();
            iosession.write(buffer);
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
}
