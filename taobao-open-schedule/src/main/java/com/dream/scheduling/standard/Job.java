package com.dream.scheduling.standard;

import java.util.Map;
/**
 * Job Interface.
 * @author Frank
 *
 */
public interface Job {
	public void execute(Map<String,Object> context);
}
