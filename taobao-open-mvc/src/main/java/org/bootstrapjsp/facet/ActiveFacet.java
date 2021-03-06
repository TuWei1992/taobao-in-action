/*
 * Copyright (c) 2014 Darren Scott - All Rights Reserved
 * 
 * This program is distributed under LGPL Version 2.1 in the hope that
 * it will be useful, but WITHOUT ANY WARRANTY.
 */
package org.bootstrapjsp.facet;

import org.bootstrapjsp.dialect.Html;
import org.bootstrapjsp.tags.Component;

public class ActiveFacet extends Facet<Component, Boolean> {

	public ActiveFacet(Boolean dflt) {
		super("active", dflt);
	}

	@Override
	public void apply(Component tag) {
		if (super.getValue()) {
			tag.setAttribute(Html.CLASS_ATTRIBUTE, "active");
		}
	}
	
	@Override
	public Boolean convert(String value) {
		return Boolean.valueOf(value);
	}

}
