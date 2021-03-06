/*
 * Copyright (c) 2014 Darren Scott - All Rights Reserved
 * 
 * This program is distributed under LGPL Version 2.1 in the hope that
 * it will be useful, but WITHOUT ANY WARRANTY.
 */
package org.bootstrapjsp.facet;

import org.bootstrapjsp.exception.InvalidAttributeException;
import org.bootstrapjsp.mold.DefaultMold;
import org.bootstrapjsp.mold.Mold;
import org.bootstrapjsp.tags.Component;
import org.bootstrapjsp.util.ComponentUtil;
import org.bootstrapjsp.util.Config;

public class MoldFacet extends Facet<Component, String> {

	private Mold<Component> mold;
	
	public MoldFacet() {
		super(null, "_default");
		this.mold = new DefaultMold();
	}

	@Override
	public boolean setValue(String name, Object value) {
		if ("mold".equals(name)) {
			this.setValue(value.toString());
			return true;
		} else if (this.mold != null) {
			return this.mold.setAttribute(name, value);
		}
		return false;
	}

	protected boolean isValid(String value) {
		// TODO This isn't great as some components set valid values
		// and expect it to be validated. Which is fine when they
		// are moldables and should only be called with the correct
		// valid molds. Once way round it might be to have them 
		// return true or false depending on whether the values
		// are valid or not.
		// There is a wider issue in that properties based molds
		// can't be mixed with non properties based ones, this 
		// whole area needs reworking. The facet itself should
		// do the properties implementation I think, not
		// DefaultMold.
		return true;
	}
	
	@Override
	public void setValue(String value) {
		this.mold = this.getMold(value);
		super.setValue(value);
	}

	@Override
	public void apply(Component tag) {
		final String name = super.getValue();
		this.mold.apply(tag, name);
	}
	
	private Mold<Component> getMold(String name) {
		try {
			Class<?> moldClass = null;
			try {
				moldClass = Class.forName(name);
			} catch (ClassNotFoundException e) {
				final String component = ComponentUtil.getComponentName(super.getTag());
				final String classProperty = component + ".mold." + name;
				final String className = Config.getProperty(classProperty);
				if (className != null) {
					moldClass = Class.forName(className);
				}
			}
			if (moldClass != null) {
				try {
					final Object object = moldClass.newInstance();
					@SuppressWarnings("unchecked")
					final Mold<Component> mold = moldClass.asSubclass(Mold.class).cast(object);
					return mold;
				} catch (Exception e) {
					throw new InvalidAttributeException(e.getMessage());
				}
			}
		} catch (ClassNotFoundException e) {
			throw new InvalidAttributeException(e.getMessage());
		}
		return new DefaultMold();
	}
	
	@Override
	public String convert(String value) {
		return value;
	}
}
