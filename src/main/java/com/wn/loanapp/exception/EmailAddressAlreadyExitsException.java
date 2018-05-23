package com.wn.loanapp.exception;

public class EmailAddressAlreadyExitsException  extends BaseExceptions{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	
	public EmailAddressAlreadyExitsException(){}
	
	public EmailAddressAlreadyExitsException(String msg){
		super(msg);
	}
	public EmailAddressAlreadyExitsException(Throwable throwable){
		super(throwable);
	}
	public EmailAddressAlreadyExitsException(String msg , Throwable throwable ){
		super(msg, throwable);
	}
	

}
