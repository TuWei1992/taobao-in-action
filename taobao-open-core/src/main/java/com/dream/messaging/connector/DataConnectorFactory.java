package com.dream.messaging.connector;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author Frank
 *
 */
public class DataConnectorFactory {
	
	private static DataConnectorFactory instance;
	
	private Map<String, DataConnector> connectors;
	
	private DataConnectorFactory(){
		super();
		connectors = Collections.synchronizedMap(new HashMap<String, DataConnector>());
	}

	public static DataConnectorFactory getInstance(){
		
		if(instance == null){
			synchronized (DataConnectorFactory.class) {				
				instance = new DataConnectorFactory();				
			}
		}
		return instance;
	}
	
	public DataConnector getDataConnector(String connectorId)throws Exception{
		
		return connectors.get(connectorId);

	}
}
