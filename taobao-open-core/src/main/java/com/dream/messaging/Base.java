package com.dream.messaging;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.log4j.Logger;

/**
 * The Class Base.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class Base {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4752992540814737455L;
	private static Logger logger=Logger.getLogger(Base.class);
	/**
	 * Gets the object property.
	 * 
	 * @param msgobj the msgobj
	 * @param property the property
	 * 
	 * @return the object property
	 */
	public Object getObjectProperty(Object msgobj, String property) {

		try {
			Object obj = msgobj;
			String[] member = property.indexOf('.') >= 0 ? property
					.split("[.]") : new String[] { property };
			for (int i = 0; i < member.length; i++) {
				property = member[i].substring(0, 1).toUpperCase()
						+ member[i].substring(1);
				Method method = obj.getClass().getMethod("get" + property);
				obj = method.invoke(obj, new Object[] {});
			}
			return obj;
		} catch (SecurityException e) {
			logger.error("Exception raised-------->",e);
		} catch (NoSuchMethodException e) {
			logger.error("Exception raised-------->",e);
		} catch (IllegalArgumentException e) {
			logger.error("Exception raised-------->",e);
		} catch (IllegalAccessException e) {
			logger.error("Exception raised-------->",e);
		} catch (InvocationTargetException e) {
			logger.error("Exception raised-------->",e);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "\r\n//==========formBean:" + this.getClass().getName()
				+ "=================\r\n";
		try {
			Class class1 = this.getClass();
			Field afield[] = class1.getDeclaredFields();
			for (int i = 0; i < afield.length; i++) {
				int mod = afield[i].getModifiers();
				s = s + afield[i].getName() + "=";
				if (mod == Modifier.PUBLIC) {
					s = s + afield[i].get(this) + "\r\n";
				} else {
					s = s + getObjectProperty(this, afield[i].getName())
							+ "\r\n";
					;
				}
			}
		} catch (Exception exception) {
			logger.error("Exception raised-------->",exception);
		}
		s = s + "============formBean============\r\n";
		return s;
	}

}
