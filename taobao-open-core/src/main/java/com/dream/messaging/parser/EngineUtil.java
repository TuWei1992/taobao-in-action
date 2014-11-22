
package com.dream.messaging.parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.dream.messaging.engine.DataHandler;
import com.dream.messaging.utils.ConfigLoader;
import com.dream.messaging.utils.QueryNode;




/**
 * The Class EngineUtil contains the universal methods that will be called by {@link com.iif.gw.core.messaging.engine.Message2Obj <code>Message2Obj</code>} and {@link com.iif.gw.core.messaging.engine.Obj2Message <code>Obj2Message</code>}.
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 * 
 * @see com.iif.gw.core.messaging.engine.Message2Obj
 * @see com.iif.gw.core.messaging.engine.Obj2Message
 */
public class EngineUtil {
	
	
	
	/** The clsMap. */
	protected final static Map<String,Class> clsMap = new HashMap<String,Class>();

	/** The Constant EQUALS. */
	private static final String EQUALS = "=";
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(EngineUtil.class);

	/** The Constant NOT_EQUALS. */
	private static final String NOT_EQUALS = "!=";
	
	/** The Constant NULL_STRING. */
	private static final String NULL_STRING = "null";
	
	/** The Constant TRUE_STRING. */
	protected static final String TRUE_STRING = "true";
	
	/** The Constant FALSE_STRING. */
	protected static final String FALSE_STRING = "false";
	
	/**
	 * Gets the object property.
	 * 
	 * @param msgobj the msgobj
	 * @param property the property
	 * 
	 * @return the object property
	 */
	public static Object getObjectProperty(Object msgobj, String property) {
		try {
			Object obj = msgobj;
			String[] member = property.indexOf('.') >= 0 ? property
					.split("[.]") : new String[] { property };
			for (int i = 0; i < member.length; i++) {
				if (obj instanceof Map) {
					property = member[i];
					obj = ((Map) obj).get(property);
				}else {
					property = member[i].substring(0, 1).toUpperCase()
							+ member[i].substring(1);
					Method method = obj.getClass().getMethod("get" + property);
					obj = method.invoke(obj);
				}
			}
			return obj;
		}catch (NoSuchMethodException e) {
			logger.error("", e);
			return null;
		}catch (IllegalAccessException e) {
			logger.error("", e);
			return null;
		}catch (InvocationTargetException e) {
			logger.error("", e);
			return null;
		}
	}

	/** The data handler. */
	protected DataHandler dataHandler;

	/** The delimiter of valid value. */
	public String delimiterOfValidValue = "|";

	/** The hm condition. */
	protected Map<String,Object> hmCondition = new HashMap<String,Object>();

	/** The system id. */
	protected String messageFormat;

	/**
	 * Instantiates a new engine util.
	 * 
	 * @param systemId the system id
	 * @param dh the dh
	 */
	public EngineUtil(String messageFormat, DataHandler dh) {
		this.messageFormat = messageFormat;
		this.dataHandler = dh;
	}

	/**
	 * check whether the node should exists or not.
	 * 
	 * @param condition the condition
	 * 
	 * @return true if the node must/should exists,otherwise return false
	 */
	protected boolean checkCondition(String condition) {
		if (condition == null || (condition = condition.trim()).equals(""))
			return true;
		String key = null, value = null;
		boolean success = false;
		int len = 2;
		int index = condition.indexOf(NOT_EQUALS);
		if (index < 0) {
			index = condition.indexOf(EQUALS);
			success = true;
			len = 1;
		}
		key = condition.substring(0, index);
		value = condition.substring(index + len);
		String v = (hmCondition.containsKey(key)) ? hmCondition.get(key)
				.toString() : null;
		return (value.equals(v) || (value.equalsIgnoreCase(NULL_STRING) && (v == null || v
				.equalsIgnoreCase(NULL_STRING)))) ? success : !success;
	}

	/**
	 * check whether the node should exists or not.
	 * 
	 * @param condition the condition
	 * 
	 * @return true if the node must/should/maybe exists,otherwise return false
	 */

	protected boolean checkPrecondition(String condition) {
		return checkCondition(condition);
	}

	/**
	 * check whether the element needed or not.
	 * 
	 * @param node the node
	 * 
	 * @return true if the node can not exist,false if the node must/need exist
	 */
	protected boolean checkStatus(Node node) {
		boolean rslt = false;
		String condition = QueryNode.getAttribute(node,
				ElementAttr.Attr_Setcondition);
		Object o = (condition==null)?null:hmCondition.remove(condition);
		if(o!=null){
			logger.info("the condition "+condition +"has been removed.");
		}
		switch (ElementStatus.getStatus(QueryNode.getAttribute(node,
				ElementAttr.Attr_Status))) {
		case ElementStatus.STATUS_C:
			rslt = !checkCondition(QueryNode.getAttribute(node,
					ElementAttr.Attr_Condition));
			break;
		case ElementStatus.STATUS_O:
			rslt = true;
			break;
		case ElementStatus.STATUS_M:

		}
		return rslt;
	}

	/**
	 * Gets the condition map.
	 * 
	 * @return the condition map
	 */
	public Map getConditionMap() {
		return hmCondition;
	}

	/**
	 * Gets the data handler.
	 * 
	 * @return the data handler
	 */
	public DataHandler getDataHandler() {
		return dataHandler;
	}

	/**
	 * Gets the delimiter of valid value.
	 * 
	 * @return the delimiter of valid value
	 */
	public String getDelimiterOfValidValue() {
		return delimiterOfValidValue;
	}

	/**
	 * Gets the ref node.
	 * 
	 * @param name the name
	 * 
	 * @return the ref node
	 */
	protected Node getRefNode(String name) {
		return (Node) ConfigLoader.getInstance().getMessageConfig(messageFormat)
				.get(name);

	}

	/**
	 * Gets the system id.
	 * 
	 * @return the system id
	 */
	public String getMessageFormat() {
		return messageFormat;
	}

	/**
	 * Sets the condition.
	 * 
	 * @param node the node
	 * @param value the value
	 */
	protected void setCondition(Node node, Object value) {
		String key = QueryNode
				.getAttribute(node, ElementAttr.Attr_Setcondition);
		if (key != null)
			hmCondition.put(key, value);
		key = QueryNode.getAttribute(node, ElementAttr.Attr_Resetcondition);
		if (key != null) {
			String[] k = (key.indexOf(";") >= 0) ? key.split("[;]")
					: new String[] { key };
			for (int i = 0; i < k.length; i++)
				hmCondition.remove(key);
		}
	}

	/**
	 * Sets the condition map.
	 * 
	 * @param condition the new condition map
	 */
	public void setConditionMap(Map<String,Object> condition) {
		this.hmCondition = condition;
	}

	/**
	 * Sets the data handler.
	 * 
	 * @param dataHandler the new data handler
	 */
	public void setDataHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	/**
	 * Sets the delimiter of valid value.
	 * 
	 * @param delimiterOfValidValue the new delimiter of valid value
	 */
	public void setDelimiterOfValidValue(String delimiterOfValidValue) {
		if (delimiterOfValidValue == null)
			return;
		this.delimiterOfValidValue = delimiterOfValidValue;
		dataHandler.setDelimiterValidValue(this.delimiterOfValidValue);
	}

	/**
	 * Sets the system id.
	 * 
	 * @param systemId the new system id
	 */
	public void setMessageFormat(String messageFormat) {
		this.messageFormat = messageFormat;
	}
}
