
package com.dream.messaging;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Envelope objects.
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class EnvelopeFactory {

	/** The envelopes. */
	private static Map envelopes;

	/** The instance. */
	private static EnvelopeFactory instance;

	/**
	 * Gets the single instance of EnvelopeFactory.
	 * 
	 * @return single instance of EnvelopeFactory
	 */
	public synchronized static EnvelopeFactory getInstance() {
		if (instance == null) {
			instance = new EnvelopeFactory();
			instance.initialize();
		}
		return instance;
	}


	/**
	 * Gets the envelope.
	 * 
	 * @param targetSysId the target sys id
	 * @param systemsId the systems id
	 * 
	 * @return the envelope
	 */
	public Envelope getEnvelope(String targetSysId, String systemsId) {
		return null;
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		envelopes = new HashMap();
	}

}
