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
package com.dream.messaging.parser;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * parse xml file or xml inputsource, if the file doesn't exist or failed to
 * parse, then return null the parser will not verify the xml，ignore the
 * nameSpace, the white space in the Elements,and the Comments.if a node
 * contains both children and node value，the parser will ignore the node
 * value(value means the TEXT/CDATA elements),only keep the children.
 */
public class XmlParser {
	
	/**
	 * create xml DocumentBuilder instance.
	 * 
	 * @return DocumentBuilder
	 */
	private static Logger logger=Logger.getLogger(XmlParser.class);
	public static DocumentBuilder getParser() {
		System.setProperty("javax.xml.parsers.DocumentBuilderFactoryFactory",
				"org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		DocumentBuilder parser = null;
		try {
			DocumentBuilderFactory parserFactory = DocumentBuilderFactory
					.newInstance();
			parserFactory.setValidating(false);
			parserFactory.setNamespaceAware(false);
			parserFactory.setIgnoringElementContentWhitespace(true);
			parserFactory.setIgnoringComments(true);
			parserFactory.setExpandEntityReferences(false);
			parser = parserFactory.newDocumentBuilder();
		} catch (ParserConfigurationException ex) {
			logger.error("Exception raised-------->",ex);
			return null;
		}
		return parser;
	}

	/**
	 * parse a xml file，if the file doesn't exist or failed to parse, then
	 * return null.
	 * 
	 * @param fileName xml file name
	 * @param parser the parser
	 * 
	 * @return Document
	 */
	public static Document parseXML(DocumentBuilder parser, String fileName) {
		if (parser == null) {
			return null;
		}
		File f = new File(fileName);
		if (!f.exists()) {
			return null;
		}
		Document xmldoc = null;
		try {
			xmldoc = parser.parse(fileName);
		} catch (SAXException ex) {

			logger.error("Exception raised-------->",ex);
		} catch (IOException ex) {

			logger.error("Exception raised-------->",ex);
		}
		compactDOM(xmldoc);
		return xmldoc;
	}

	/**
	 * parse a xml inputsource，if the source is null or failed to parse, then
	 * return null.
	 * 
	 * @param source xml input source
	 * @param parser the parser
	 * 
	 * @return Document
	 */
	public static Document parseXML(DocumentBuilder parser, InputSource source) {
		if (parser == null) {
			return null;
		}
		if (source == null)
			return null;
		Document xmldoc = null;
		try {
			xmldoc = parser.parse(source);
		} catch (SAXException ex) {

			logger.error("Exception raised-------->",ex);
		} catch (IOException ex) {

			logger.error("Exception raised-------->",ex);
		}
		compactDOM(xmldoc);
		return xmldoc;

	}

	/**
	 * remove the redundant TEXT/CDATA elements of the DOM if a node contains
	 * both children and node value，will ignore the node value(value means the
	 * TEXT/CDATA elements),only keep the children.
	 * 
	 * @param root xml DOM root
	 */
	private static void compactDOM(Node root) {
		while (root != null) {
			if (!root.hasChildNodes()) {
				root = root.getNextSibling();
				continue;
			}
			NodeList nodes = root.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Node cNode = nodes.item(i);
				switch (cNode.getNodeType()) {
				case Node.TEXT_NODE:
				case Node.COMMENT_NODE:
				case Node.CDATA_SECTION_NODE:
					if (nodes.getLength() > 1) {
						root.removeChild(cNode);
						i--;
					}

					break;

				default:
					compactDOM(cNode);
					break;
				}
			}

			root = root.getNextSibling();
		}
	}
}
