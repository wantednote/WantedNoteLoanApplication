package com.wn.loanapp.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.wn.loanapp.constants.Constants;

public class CustomLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

    	System.out.println(authentication.getCredentials());
    	request.setAttribute("userType", Constants.PARTNER);
        // business logic here
    }
}