package com.wn.loanapp.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
public class UserUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	public UserUsernamePasswordAuthenticationToken(Object principal,
			Object credentials) {
		super(principal, credentials);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1577898740775173112L;

}
