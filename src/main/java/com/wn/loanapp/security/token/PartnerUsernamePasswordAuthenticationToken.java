package com.wn.loanapp.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PartnerUsernamePasswordAuthenticationToken  extends UsernamePasswordAuthenticationToken{

	
	public PartnerUsernamePasswordAuthenticationToken(Object principal,
			Object credentials) {
		super(principal, credentials);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8649503376614445796L;

}
