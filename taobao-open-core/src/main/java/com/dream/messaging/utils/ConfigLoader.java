package com.dream.messaging.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.dream.messaging.parser.ElementAttr;
import com.dream.messaging.parser.XmlParser;

/**
 * The Class ConfigLoader loads the message definition files into a map to cache
 * them for high performance.
 * 
 * @author anders liang
 */
public class ConfigLoader implements InitializingBean, DisposableBean {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConfigLoader.class);

	private Resource configPath = null;

	private String charsetName = null;

	private static final String DEFAULT_CHARSET_NAME = "UTF-8";

	/**
	 * @param configPath
	 *            the configPath to set
	 */
	public void setConfigPath(Resource configPath) {
		this.configPath = configPath;
	}

	/** The Constant CVS_DIR. */
	private static final String CVS_DIR = "CVS";

	/** The Constant SVN_DIR. */
	private static final String SVN_DIR = ".SVN";

	private static final String XML_FILE_SURFIX = ".xml";

	/** The props. */
	private static Map<String, Map<String, Node>> props = new HashMap<String, Map<String, Node>>();

	/** The instance. */
	private static ConfigLoader instance;

	/**
	 * Gets the single instance of ConfigLoader.
	 * 
	 * @return single instance of ConfigLoader
	 */
	public synchronized static ConfigLoader getInstance() {
		return instance;
	}

	/**
	 * Gets the message configuration.
	 * 
	 * @param key
	 *            the key
	 * 
	 * @return the message configuration
	 */
	public Map<String, Node> getMessageConfig(String key) {
		return props.get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		this.charsetName = this.charsetName == null ? DEFAULT_CHARSET_NAME
				: this.charsetName;
		File file = configPath.getFile();
		if (file.isDirectory()) {
			String systemId = null;
			String transaction = null;
			File[] systemIds = file.listFiles();
			DocumentBuilder parser = XmlParser.getParser();
			for (int i = 0; i < systemIds.length; i++) {
				if (systemIds[i].isDirectory()
						&& !systemIds[i].getName().equalsIgnoreCase(CVS_DIR)
						&& !systemIds[i].getName().equalsIgnoreCase(SVN_DIR)) {
					Map<String, Node> messages = new HashMap<String, Node>();
					systemId = systemIds[i].getName();
					props.put(systemId, messages);
					File[] msgFile = systemIds[i].listFiles();
					for (int j = 0; j < msgFile.length; j++) {
						if (msgFile[j].isFile()
								&& msgFile[j].getName().endsWith(
										XML_FILE_SURFIX)) {
							// 修复Unreleased Resource: Streams
							FileInputStream fis = null;
							try {
								fis = new FileInputStream(msgFile[j]);
								InputStreamReader reader = new InputStreamReader(
										fis, charsetName);
								Node node = XmlParser.parseXML(parser,
										new InputSource(reader));
								node = node.getFirstChild();
								String transCode = QueryNode.getAttribute(node,
										ElementAttr.Attr_Transcode);
								String version = QueryNode.getAttribute(node,
										ElementAttr.Attr_Version);
								transaction = transCode + "_V" + version;
								messages.put(transaction, node);
								logger.info("Transaction :" + transaction
										+ " has been cached under system "
										+ systemId);
							} catch (FileNotFoundException e) {
								logger.error("File **" + msgFile[j].getName()
										+ "** not found...,please check it.", e);
								continue;
							} finally {
								if (fis != null) {
									try {
										fis.close();
									} catch (Exception e) {
										logger.error(e);
									}
								}
							}
						}
					}
				}
			}
		}
		synchronized (ConfigLoader.class) {
			ConfigLoader.instance = this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	public void destroy() throws Exception {
		props.clear();
	}

}
