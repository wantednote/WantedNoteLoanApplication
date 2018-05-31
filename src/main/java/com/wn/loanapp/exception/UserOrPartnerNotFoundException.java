package com.wn.loanapp.exception;

public class UserOrPartnerNotFoundException extends BaseExceptions{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	
	public UserOrPartnerNotFoundException(){}
	
	public UserOrPartnerNotFoundException(String msg){
		super(msg);
	}
	public UserOrPartnerNotFoundException(Throwable throwable){
		super(throwable);
	}
	public UserOrPartnerNotFoundException(String msg , Throwable throwable ){
		super(msg, throwable);
	}
}
