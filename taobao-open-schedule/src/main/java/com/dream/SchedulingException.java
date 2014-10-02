package com.dream;



public class SchedulingException  extends RuntimeException{
    private String code = null;
    /**
	 * 
	 */
	private static final long serialVersionUID = -6974414773442212400L;

	public SchedulingException(String code, String message, Object[] args, Throwable cause) {
		this(code,message);
    }

    public SchedulingException(String code, String message, Object[] args) {
    	this(code,message);
    }

    public SchedulingException(String code, String message) {
    	this.code = code;
    }

    public SchedulingException(String message) {
    }
}
