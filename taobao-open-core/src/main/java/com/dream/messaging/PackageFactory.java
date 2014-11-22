
package com.dream.messaging;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Package objects.
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class PackageFactory {

	/** The instance. */
	private static PackageFactory instance;

	/** The packages. */
	private static Map packages;

	/**
	 * Gets the single instance of PackageFactory.
	 * 
	 * @return single instance of PackageFactory
	 */
	public synchronized static PackageFactory getInstance() {
		if (instance == null || packages == null) {
			instance = new PackageFactory();
			instance.initialize();
		}
		return instance;
	}

	/**
	 * Gets the package.
	 * 
	 * @param targetSysId the target sys id
	 * @param systemId the system id
	 * 
	 * @return the package
	 */
	public Package getPackage(String targetSysId, String systemId) {
		return null;
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		packages = new HashMap();
	}

}
