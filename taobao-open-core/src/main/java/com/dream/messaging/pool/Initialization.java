package com.dream.messaging.pool;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

public class Initialization {
	
	private static Initialization initialization = new Initialization();
	
	private Map<String, IoSession> sessions = new HashMap<String, IoSession>();
	
	private Initialization(){
		
	}
	
	public static Initialization  getInstance(){
		return initialization;
	}

	public Map<String, IoSession> getClientMap() {
		return sessions;
	}
}
