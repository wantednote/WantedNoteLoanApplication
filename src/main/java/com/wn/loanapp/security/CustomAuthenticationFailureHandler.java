package com.wn.loanapp.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	CustomAuthenticationFailureHandler(String defaultUrl){
		super(defaultUrl);
	}
	/*@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		System.out.println("inside CustomAuthentication FailureHandler");
		
	}*/

}
