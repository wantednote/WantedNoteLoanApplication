package com.wn.loanapp.exception;

import java.io.Serializable;



public class BaseExceptions extends Exception implements Serializable{

	private static final long serialVersionUID = 7832737117963456839L;

	public BaseExceptions(String msg) {
		super(msg);
	}

	public BaseExceptions(Throwable e) {
		super(e);
	}

	public BaseExceptions(String msg, Throwable e) {
		super(msg,e);
	}

	public BaseExceptions() {
		super();
	}
	
}
