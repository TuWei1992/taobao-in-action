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
package com.dream.messaging.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.dream.messaging.InvalidXpathException;


/**
 * The Class QueryNode provides various methods to simplify operations with node(such as get node value {@link #getNodeValue(Node, String)},get node by give path {@link #getNode(Node, String)}).
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 * 
 * @see #getNodeValue(Node,String)
 * @see #getNode(Node, String)
 */
public class QueryNode {
	
	/**
	 * Gets the node value.
	 * 
	 * @param node the node
	 * 
	 * @return the node value
	 */
	public static String getNodeValue(Node node) {
		if (node.hasChildNodes()) {
			Node cnode = node.getChildNodes().item(0);
			switch (cnode.getNodeType()) {
			case Node.TEXT_NODE:
			case Node.CDATA_SECTION_NODE:
				return cnode.getNodeValue();
			}
		}
		return null;
	}

	/**
	 * Parses the xpath.
	 * 
	 * @param xpath the xpath
	 * 
	 * @return the string[]
	 */
	private static String[] parseXpath(String xpath) {

		StringTokenizer paths = new StringTokenizer(xpath, "/[]");

		List<String> al = new ArrayList<String>();

		while (paths.hasMoreTokens()) {

			al.add(paths.nextToken());
		}
		int count = al.size();
		String[] path = new String[count];
		for (int i = 0; i < count; i++) {
			path[i] = (String) al.get(i);
		}
		return path;
	}

	/**
	 * Gets the node.
	 * 
	 * @param root the root
	 * @param xpath the xpath
	 * 
	 * @return the node
	 * 
	 * @throws InvalidXpathException the invalid xpath exception
	 */
	public static Node getNode(Node root, String xpath)
			throws InvalidXpathException {

		Node newNode = root;
		String[] path = parseXpath(xpath);
		String pathName;

		for (int i = 0; i < path.length; i++) {
			if (path[i].equals("."))
				continue;
			if (path[i].equals("..")) {
				newNode = newNode.getParentNode();
				continue;
			}
			if (!newNode.hasChildNodes())
				throw new InvalidXpathException(path[i]);
			int len = newNode.getChildNodes().getLength();
			pathName = path[i];
			i++;// move to next ,it stores the position
			int n, position = 0;
			try {
				if (i < path.length)
					position = Integer.parseInt(path[i]);
			} catch (NumberFormatException ex) {
				// if not a number, it presents a element,move back.
				i--;
			}
			if (len < position || position < 0) {
				throw new InvalidXpathException(pathName + "[" + position
						+ "] invalid,outofbound.");

			}
			newNode = newNode.getFirstChild();
			n = -1;
			do {
				if (!newNode.getNodeName().equals(pathName)) {
					newNode = newNode.getNextSibling();
				} else {
					n++;
					if (n == position) {
						break;
					}
				}
			} while (newNode != null);
			if (n == -1) {
				throw new InvalidXpathException(pathName + "[" + position
						+ "] invalid,no such element.");
			}
			if (newNode == null) {
				throw new InvalidXpathException(pathName + "[" + position
						+ "] invalid,outofbound.");
			}

		}
		return newNode;
	}

	/**
	 * Gets the node value.
	 * 
	 * @param root the root
	 * @param xpath the xpath
	 * 
	 * @return the node value
	 * 
	 * @throws InvalidXpathException the invalid xpath exception
	 */
	public static String getNodeValue(Node root, String xpath)
			throws InvalidXpathException {
		Node node = null;
		try {
			node = QueryNode.getNode(root, xpath);
		} catch (InvalidXpathException ex) {
			throw ex;
		}
		return QueryNode.getNodeValue(node);
	}

	/**
	 * Gets the attribute.
	 * 
	 * @param node the node
	 * @param attName the att name
	 * 
	 * @return the attribute
	 */
	public static String getAttribute(Node node, String attName) {
		if (node.hasAttributes()) {
			NamedNodeMap atts = node.getAttributes();
			Node att = atts.getNamedItem(attName);
			if (att != null)
				return att.getNodeValue();
		}
		return null;
	}

	/**
	 * Gets the attribute.
	 * 
	 * @param root the root
	 * @param nodeXpath the node xpath
	 * @param attName the att name
	 * 
	 * @return the attribute
	 * 
	 * @throws InvalidXpathException the invalid xpath exception
	 */
	public static String getAttribute(Node root, String nodeXpath,
			String attName) throws InvalidXpathException {
		Node node = null;
		try {
			node = QueryNode.getNode(root, nodeXpath);
		} catch (InvalidXpathException ex) {
			throw ex;
		}

		return QueryNode.getAttribute(node, attName);
	}

}
