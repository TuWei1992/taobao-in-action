/**************************************************************************
 * Licensed Material - Property of Dawn InfoTek                           *
 * Copyright (c) Dawn InfoTek Inc. 1999, 2004, 2008 -All rights reserved. * 
 * (<http://www.dawninfotek.com>)                                         *
 *                                                                        *
 * This file contains proprietary intellectual property of                *
 * Dawn InfoTek Inc. The contents of and information in this file         *
 * is only to be used in conjunction with a valid Dawn4J license          *
 * as specified in the Dawn4J license agreement. All other use            *
 * is prohibited.                                                         *
 **************************************************************************/
package com.dream.messaging.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.dream.messaging.DataConvertException;
import com.dream.messaging.Message;
import com.dream.messaging.parser.ElementAttr;
import com.dream.messaging.parser.ElementClassType;
import com.dream.messaging.parser.EngineUtil;
import com.dream.messaging.utils.ConfigLoader;
import com.dream.messaging.utils.QueryNode;



/**
 * The Class Message2Obj parses the response message and return an Object by its {@link #generate(Node, Message) <code>generate</code>}.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class Message2Obj extends EngineUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Message2Obj.class);

	/** The logger. */


	/**
	 * Instantiates a new message2 obj.
	 * 
	 * @param systemId the system id
	 * @param dh the dh
	 */
	public Message2Obj(String messageFormat, DataHandler dh) {
		super(messageFormat, dh);
	}

	/**
	 * Creates the object.
	 * 
	 * @param clsname the clsname
	 * 
	 * @return the object
	 */
	private Object createObject(String clsname) {
		Class cls = null;
		Object o = clsMap.get(clsname);
		try {
			if (o == null) {
				cls = Class.forName(clsname);
				clsMap.put(clsname, cls);
			} else {
				cls = (Class) o;
			}
			return cls.newInstance();
		} catch (ClassNotFoundException e) {
			logger.error("", e);
			return null;
		} catch (InstantiationException e) {
			logger.error("", e);
			return null;
		} catch (IllegalAccessException e) {
			logger.error("", e);
			return null;
		}

	}

	/**
	 * Generate.
	 * 
	 * @param request the request
	 * @param message the message
	 * 
	 * @return the object
	 * 
	 * @throws BaseException the base exception
	 */
	public Object generate(Node request, Message message) throws DataConvertException {
		return generate(request, message, null);
	}

	/**
	 * Generate.
	 * 
	 * @param request the request
	 * @param message the message
	 * @param obj the obj
	 * 
	 * @return the object
	 * 
	 * @throws BaseException the base exception
	 */
	public Object generate(Node request, Message message, Object obj)
			throws DataConvertException {
		//check if the node has nodes,if the node does not have node,it represents a reference. 
		if (!request.hasChildNodes()) {
			String ref = QueryNode.getAttribute(request,
					ElementAttr.Attr_Reference);
			if (ref == null)
				return obj;
			request = (Node) ConfigLoader.getInstance().getMessageConfig(
					messageFormat).get(ref);
		}
		if (obj == null) {
			String clsName = null;
			clsName = QueryNode.getAttribute(request, ElementAttr.Attr_Class);
			obj = createObject(clsName);
		}

		if (read(request.getFirstChild(), message, obj)) {
			return obj;
		} else {
			logger.error("the return object is null");
			return null;
		}
	}

	/**
	 * Read.
	 * 
	 * @param node the node
	 * @param message the message
	 * @param obj the obj
	 * 
	 * @return true, if successful
	 * 
	 * @throws BaseException the base exception
	 */
	private boolean read(Node node, Message message, Object obj)
			throws DataConvertException {
		int counter = -1;
		while (node != null) {
			// check if this node should be read
			if (!checkPrecondition(QueryNode.getAttribute(node,
					ElementAttr.Attr_Precondition))) {
				setCondition(node, obj);
				node = node.getNextSibling();
				continue;
			}

			String nodeName = node.getNodeName();
			if (nodeName.equals(ElementAttr.Elem_Section)) {
				if (logger.isDebugEnabled()) {
//					logger.debug(this, "Section is generating");
				}
				// check if a list
				String repeated = QueryNode.getAttribute(node,
						ElementAttr.Attr_Repeated);
				if (repeated != null && repeated.equalsIgnoreCase(TRUE_STRING)) {
					String mapCount = QueryNode.getAttribute(node,
							ElementAttr.Attr_Mapcount);
					int size = 0;
					int offset = dataHandler.getIndex();
					// get list size,if the message didn't contain this
					// info, then get max size
					if (counter > -1) {
						size = counter;
						counter = -1;
					} else {
						if (mapCount != null
								&& mapCount.equalsIgnoreCase(TRUE_STRING)) {
							size = dataHandler.readCount(message, node);
						} else {
							String smax = QueryNode.getAttribute(node,
									ElementAttr.Attr_Max);
							size = (smax != null) ? Integer.parseInt(smax)
									: Integer.MAX_VALUE;
						}
					}
					if (size < 0) {
						dataHandler.setIndex(offset);
						if (!checkStatus(node)
								|| !checkCondition(QueryNode.getAttribute(node,
										ElementAttr.Attr_Condition))) {
							setCondition(node, obj);
							return false;
						}
						node = node.getNextSibling();
						continue;
					}

					String clsName = QueryNode.getAttribute(node,
							ElementAttr.Attr_Class);
					List<Object> list = new ArrayList<Object>();
					// if there defines a delimiter, we will use a
					// CSVDataHandler to generate message
					String delimiter = QueryNode.getAttribute(node,
							ElementAttr.Attr_Delimiter);
					DataHandler oldDh = dataHandler;
					if (delimiter != null) {
						//this.dataHandler = new CSVDataHandler(delimiter);
						//this.dataHandler.setIndex(oldDh.getIndex());
					}
					// check if there is a suffix
					String suffix = QueryNode.getAttribute(node,
							ElementAttr.Attr_Suffix);

					for (int i = 0; i < size; i++) {
						offset = dataHandler.getIndex();
						Object o = null;
						o = createObject(clsName);
						if (o == null
								|| !read(node.getFirstChild(), message, o)
								|| offset == message.length()) {
								logger.info(
										"circle body has reached end,its length is "
												+ offset);
							dataHandler.setIndex(offset);
							break;
						}
						
						//Handle Object call back.
						
						list.add(o);
						// read suffix and validate it
						if (suffix != null) {
							offset = dataHandler.getIndex();
							Object data = dataHandler.readFixedData(message,
									suffix);
							if (data == null || !suffix.equals(data)) {
								// restore the data handler
								this.dataHandler = oldDh;
								oldDh.setIndex(offset);
									logger.error("error happened in the field circle body definition");
								return false;
							}
						}
					}
					String callback = QueryNode.getAttribute(node,
							ElementAttr.Attr_Callback);
					if(callback!=null){
						
					}
					// restore the data handler
					oldDh.setIndex(this.dataHandler.getIndex());
					this.dataHandler = oldDh;

					setCondition(node, list);
					// add to domain object
					String name = QueryNode.getAttribute(node,
							ElementAttr.Attr_Name);
					if (name != null) {
						setValue(obj, list, name);
					}
				} else {
					String clsName = QueryNode.getAttribute(node,
							ElementAttr.Attr_Class);
					int offset = dataHandler.getIndex();
					if (clsName != null) {
						Object o = null;
						o = createObject(clsName);
						if (o == null
								|| !read(node.getFirstChild(), message, o)) {
							if (!checkStatus(node)
									|| !checkCondition(QueryNode.getAttribute(
											node, ElementAttr.Attr_Condition))) {
								dataHandler.setIndex(offset);
								setCondition(node, o);
								return false;
							}
						}

						setCondition(node, o);
						String name = QueryNode.getAttribute(node,
								ElementAttr.Attr_Name);
						if (name != null) {
							setValue(obj, o, name);
						}
					} else {
						if (obj == null
								|| !read(node.getFirstChild(), message, obj)) {
							if (!checkStatus(node)
									|| !checkCondition(QueryNode.getAttribute(
											node, ElementAttr.Attr_Condition))) {
								dataHandler.setIndex(offset);
								setCondition(node, obj);
								return false;
							}
						}
					}
				}
			} else if (nodeName.equals(ElementAttr.Elem_Property)) {
				String ref = QueryNode.getAttribute(node,
						ElementAttr.Attr_Reference);
				String name = QueryNode.getAttribute(node,
						ElementAttr.Attr_Name);
				if (logger.isDebugEnabled()) {
					logger.debug("property " + (name==null?ref:name) + " is generating");
				}
				Object data = null;
				if (ref != null) {
					if (name == null) {
						generate(node, message, obj);
					} else {
						data = EngineUtil.getObjectProperty(obj, name);
						data = generate(node, message, data);
						setValue(obj, data, name);
					}

				} else {
					int offset = dataHandler.getIndex();

					data = dataHandler.readData(message, node);

					if (data == null || !dataHandler.validateData(data, node)) {
						if (!checkStatus(node)
								|| !checkCondition(QueryNode.getAttribute(node,
										ElementAttr.Attr_Condition))) {
							dataHandler.setIndex(offset);
							setCondition(node, data);
							return false;
						}
					} else {
						Object v = dataHandler.createValue(data, node);
						if (name != null) {
							setValue(obj, v, name);
						}
						String cls = QueryNode.getAttribute(node,
								ElementAttr.Attr_Class);
						if (cls != null
								&& cls
										.equalsIgnoreCase(ElementClassType.CLASS_COUNTER)) {
							counter = ((Integer) (v)).intValue();
							Integer defaultCounter = Integer.parseInt(QueryNode.getAttribute(node,
									ElementAttr.Attr_Default));
							logger.info("The real record counts is "+counter+",the default counts is "+defaultCounter);
							dataHandler.setCount(defaultCounter>counter?counter:defaultCounter);
						}
						setCondition(node, v);
					}
				}
				
				// set to object

			} else {
				// warning : illegal node
				logger.fatal("the node is illegal,neither property nor section,please chech it");
			}
			node = node.getNextSibling();
		}
		return true;
	}

	/**
	 * Sets the value.
	 * 
	 * @param msgobj the msgobj
	 * @param value the value
	 * @param property the property
	 * 
	 * @return true, if successful
	 */
	private boolean setValue(Object msgobj, Object value, String property) {
		boolean rst = false;
		if (value == null)
			return rst;
		try {
			String[] member = (property.indexOf(".") >= 0) ? property
					.split("[.]") : new String[] { property };
			for (int i = 0; i < member.length - 1; i++) {

				if (msgobj instanceof Map) {
					property = member[i];
					msgobj = ((Map) msgobj).get(property);
				} else {
					property = member[i].substring(0, 1).toUpperCase()
							+ member[i].substring(1);
					Method method = msgobj.getClass().getMethod(
							"get" + property);
					msgobj = method.invoke(msgobj, new Object[] {});
				}
			}

			Class clz = null;
			if (value instanceof List) {
				clz = List.class;
			} else if (value instanceof Integer) {
				clz = Integer.class;
			} else if (value instanceof Short) {
				clz = Short.class;
			} else if (value instanceof Long) {
				clz = Long.class;
			} else if (value instanceof Double) {
				clz = Double.class;
			} else {
				clz = value.getClass();
			}
			if (msgobj instanceof Map) {
				property = member[member.length - 1];
				((Map) msgobj).put(property, value);
			}else {
				property = member[member.length - 1].substring(0, 1)
						.toUpperCase()
						+ member[member.length - 1].substring(1);
				Method method = msgobj.getClass().getMethod("set" + property,
						new Class[] { clz });
				Object[] objs = { value };
				method.invoke(msgobj, objs);
			}
			rst = true;
			logger.info("add to message object--key:" + property
					+ " value:" + value);
		} catch (SecurityException e) {
			logger
					.error(
							"SecurityException occurs in method Message2Obj#setValue...");
		} catch (NoSuchMethodException e) {
			logger
					.error(
							"NoSuchMethodException occurs in method Message2Obj#setValue...");
		} catch (IllegalArgumentException e) {
			logger
					.error(
							"IllegalArgumentException occurs in method Message2Obj#setValue...");
		} catch (IllegalAccessException e) {
			logger
					.error("llegalAccessException occurs in method Message2Obj#setValue...");
		} catch (InvocationTargetException e) {
			logger
					.error(
							"InvocationTargetException occurs in method Message2Obj#setValue...");
		}
		if (!rst)
			logger.error("Failed to add value to message object--key:"
					+ property + " value:" + value);
		return rst;
	}

}
