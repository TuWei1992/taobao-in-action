package com.dream.messaging;

public class DataConvertException extends MessagingException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2151348534688457011L;

	public DataConvertException(String errCode){
		super(errCode);
	}
	
	public DataConvertException(String errCode,String errMsg){
		super(errCode,errMsg);
	}
	
	public DataConvertException(String errCode,Throwable throwable){
		super(errCode,throwable);
	}
	
	 public String toString(){
	    	return super.toString();
	 }
}
