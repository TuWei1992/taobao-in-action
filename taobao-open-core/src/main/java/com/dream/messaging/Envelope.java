
package com.dream.messaging;




// TODO: Auto-generated Javadoc
/**
 * The Interface Envelope implements Interface Message,and add a method fill to put the domain to message.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public interface  Envelope  extends Message {

	/**
	 * Fill.
	 * 
	 * @param domainObject the domain object
	 */
	public abstract void fill(Object domainObject);

}
