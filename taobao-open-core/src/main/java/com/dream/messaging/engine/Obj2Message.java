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

import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.dream.messaging.DataConvertException;
import com.dream.messaging.Message;
import com.dream.messaging.parser.ElementAttr;
import com.dream.messaging.parser.ElementClassType;
import com.dream.messaging.parser.EngineUtil;
import com.dream.messaging.parser.Strings;
import com.dream.messaging.utils.QueryNode;



/**
 * The Class Obj2Message converts object to message by its {@link #generate(Node, Object) <code>generate</code>}
 * method.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class Obj2Message extends EngineUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Obj2Message.class);



	/**
	 * Instantiates a new obj2 message.
	 * 
	 * @param systemId the system id
	 * @param dataHandler the data handler
	 */
	public Obj2Message(String systemId, DataHandler dataHandler) {
		super(systemId, dataHandler);

	}

	/**
	 * Generate message from given object.
	 * 
	 * @param msgNode the msg node
	 * @param msgObj the msg obj
	 * 
	 * @return the message
	 * 
	 * @throws BaseException the base exception
	 */
	public Message generate(Node msgNode, Object msgObj) throws DataConvertException {
		Message message = dataHandler.newMessage();
		if (msgNode == null)
			return null;

		if (msgNode.hasChildNodes())
			msgNode = msgNode.getFirstChild();
		else {
			String ref = QueryNode.getAttribute(msgNode,
					ElementAttr.Attr_Reference);
			msgNode = (ref != null) ? getRefNode(ref) : null;
		}
		int counter = -1;
		while (msgNode != null) {
			// check this node should exists?
			if (!checkPrecondition(QueryNode.getAttribute(msgNode,
					ElementAttr.Attr_Precondition))) {
				setCondition(msgNode, msgObj);
				msgNode = msgNode.getNextSibling();
				continue;
			}

			String tag = msgNode.getNodeName();
			// Node cMsgNode = null;
			String name = QueryNode
					.getAttribute(msgNode, ElementAttr.Attr_Name);
			Object value = null;
			boolean isFixed = false;
			if (name != null) {
				value = getObjectProperty(msgObj, name);
				if (value == null || value.equals(""))
					value = QueryNode.getAttribute(msgNode,
							ElementAttr.Attr_Default);
			} else {
				value = QueryNode.getAttribute(msgNode,
						ElementAttr.Attr_Default);
				if (value == null) {
					value = QueryNode.getAttribute(msgNode,
							ElementAttr.Attr_Fixed);
					if (value != null) {
						value = Strings.replaceESC((String) value);
						isFixed = true;
					}
				}
			}
			// check if a counter
			// if(value==null){
			String clz = QueryNode
					.getAttribute(msgNode, ElementAttr.Attr_Class);
			if (clz != null
					&& clz.equalsIgnoreCase(ElementClassType.CLASS_COUNTER)) {
				if (value == null) {
					String refValue = QueryNode.getAttribute(msgNode,
							ElementAttr.Attr_Countvalue);
					List list = (List) getObjectProperty(msgObj, refValue);
					counter = list.size();
					value = Integer.valueOf(counter);
				} else {
					counter = ((Integer) value).intValue();
				}
			}
			// }
			if (tag.equalsIgnoreCase(ElementAttr.Elem_Section)) {
				if (logger.isDebugEnabled()) {
					logger.debug("section " + name + " is generating");
				}
				// check if it is a list
				String repeated = QueryNode.getAttribute(msgNode,
						ElementAttr.Attr_Repeated);
				if (repeated != null && repeated.equalsIgnoreCase(TRUE_STRING)) {
					if (value == null || !(value instanceof List)) {
						if (!checkCondition(QueryNode.getAttribute(msgNode,
								ElementAttr.Attr_Condition))
								|| !checkStatus(msgNode)) {
							// will append a empty later
						} else {
							setCondition(msgNode, msgObj);
							// this node can be ignored
							msgNode = msgNode.getNextSibling();
							continue;
						}
					}
					String mapCount = QueryNode.getAttribute(msgNode,
							ElementAttr.Attr_Mapcount);
					List list = (value == null) ? null : (List) value;
					if (mapCount != null
							&& mapCount.equalsIgnoreCase(TRUE_STRING)) {
						//修复Null Dereference
						int size = (list == null) ? 0 : ((List) list).size();
						Object count = dataHandler.createMessageData(Integer
								.valueOf(size), msgNode);
						dataHandler.appendData(message, count);

					}
					if (list != null) {
						// if there defines a delimiter, we will use a
						// CSVDataHandler to generate message
						String delimiter = QueryNode.getAttribute(msgNode,
								ElementAttr.Attr_Delimiter);
						DataHandler oldDh = dataHandler;
						if (delimiter != null) {
							//this.dataHandler = new CSVDataHandler(delimiter);
						}
						// check if there is a suffix
						String suffix = QueryNode.getAttribute(msgNode,
								ElementAttr.Attr_Suffix);

						for (int i = 0; i < list.size(); i++) {
							Message append = generate(msgNode, list.get(i));
							dataHandler.appendMessage(message, append);
						}
						// if there is a suffix, we should append it as
						// fixed data
						if (suffix != null) {
							dataHandler.appendFixedData(message, suffix);
						}
						// restore the data handler
						this.dataHandler = oldDh;
					}
					setCondition(msgNode, value);
				} else if (msgNode.hasChildNodes()) {
					Message append = generate(msgNode, value);
					setCondition(msgNode, append);
					dataHandler.appendMessage(message, append);
				} else {
					String ref = QueryNode.getAttribute(msgNode,
							ElementAttr.Attr_Reference);
					if (ref != null) {
						Message append = generate(getRefNode(ref),
								name != null ? value : msgObj);
						setCondition(msgNode, append);
						dataHandler.appendMessage(message, append);
					} else {
						if (value == null) {
							if (!checkCondition(QueryNode.getAttribute(msgNode,
									ElementAttr.Attr_Condition))
									|| !checkStatus(msgNode)) {

								Object append = dataHandler.createMessageData(
										value, msgNode);
								setCondition(msgNode, append);
								if (dataHandler.validateData(append, msgNode)) {
									dataHandler.appendData(message, append);
								} else {
									logger.error("property " + name
											+ " is invalid,please check it.");
									return null;
								}

							}
						} else {
							Object append = dataHandler.createMessageData(
									value, msgNode);
							if (dataHandler.validateData(append, msgNode)) {
								if (isFixed)
									dataHandler
											.appendFixedData(message, append);
								else
									dataHandler.appendData(message, append);

							} else {
								//
								if (!checkCondition(QueryNode.getAttribute(
										msgNode, ElementAttr.Attr_Condition))
										|| !checkStatus(msgNode)) {
									setCondition(msgNode, append);
									return null;
								}
							}
							setCondition(msgNode, append);
						}
					}

				}

			} else if (tag.equalsIgnoreCase(ElementAttr.Elem_Property)) {
				
				String ref = QueryNode.getAttribute(msgNode,
						ElementAttr.Attr_Reference);
				
				
				if (ref != null) {
					Message append = generate(getRefNode(ref),
							name != null ? value : msgObj);
					dataHandler.appendMessage(message, append);
				} else {
					if (value == null || value.equals("")
							|| !dataHandler.validateData(value, msgNode)) {
						if (!checkCondition(QueryNode.getAttribute(msgNode,
								ElementAttr.Attr_Condition))
								|| !checkStatus(msgNode)) {
							setCondition(msgNode, value);
								logger.error("property " + name
										+ " is mandatory,but is null");
							return null;
						} else {
							// create a empty data and append it to message
							Object append = dataHandler.createMessageData(
									value, msgNode);
							setCondition(msgNode, append);
							dataHandler.appendData(message, append);
							// msgNode = msgNode.getNextSibling();
							// continue;
						}
					} else {
						Object append = dataHandler.createMessageData(value,
								msgNode);
						if (logger.isDebugEnabled()) {
							logger.debug("property " + name
									+ " is generated,and its value is " + value);
						}
						setCondition(msgNode, append);
						if (isFixed)
							dataHandler.appendFixedData(message, append);
						else
							dataHandler.appendData(message, append);
					}
				}
			} else {
			}

			msgNode = msgNode.getNextSibling();
		}

		return message;
	}

}
