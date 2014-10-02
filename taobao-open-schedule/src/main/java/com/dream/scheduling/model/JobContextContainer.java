package com.dream.scheduling.model;

public class JobContextContainer {
	private static ThreadLocal<String> jobDetileName = new ThreadLocal<String>();
	private static ThreadLocal<Boolean> amcOnOff = new ThreadLocal<Boolean>();
	public static String getJobDetileName() {
		return jobDetileName.get();
	}
	public static void setJobDetileName(String jn) {
		
		jobDetileName.set(jn);
	}
	public static boolean getAmcOnOff() {
		return amcOnOff.get().booleanValue();
	}
	public static void setAmcOnOff(boolean ao) {
		amcOnOff.set(new Boolean(ao));
	}
	
}
