package com.wn.loanapp.exception;

public class RoleAlreadyExistException extends BaseExceptions{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	
	public RoleAlreadyExistException(){}
	
	public RoleAlreadyExistException(String msg){
		super(msg);
	}
	public RoleAlreadyExistException(Throwable throwable){
		super(throwable);
	}
	public RoleAlreadyExistException(String msg , Throwable throwable ){
		super(msg, throwable);
	}
	
}
