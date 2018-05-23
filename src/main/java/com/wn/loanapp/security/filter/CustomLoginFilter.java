package com.wn.loanapp.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.security.token.PartnerUsernamePasswordAuthenticationToken;
import com.wn.loanapp.security.token.UserUsernamePasswordAuthenticationToken;

/**
 * Servlet Filter implementation class CustomLoginFilter
 */
public class CustomLoginFilter extends  UsernamePasswordAuthenticationFilter {


	private Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
			log.debug("start of method attemptAuthentication of CustomFilter");
			UsernamePasswordAuthenticationToken authRequest = null;
			String uname=request.getParameter("username");
		 
			String pwd=request.getParameter("password");
		 
			String type=request.getParameter("userType");
			log.debug("user Type : "+type);
			if (uname == null) {
	            uname = "";
	        }
	        if (pwd == null) {
	            pwd = "";
	        }
			if(type.equals(Constants.USER_TYPE_ADMIN)){
			     authRequest = new UserUsernamePasswordAuthenticationToken(uname, pwd);
		    }else if(type.equals(Constants.USER_TYPE_PARTNER)) {
		        authRequest = new PartnerUsernamePasswordAuthenticationToken(uname, pwd);
		    }
			HttpSession session = request.getSession(false);
			if(null == session){
				session = request.getSession(true);
			}
			session.setAttribute("userType", type);
			setDetails(request, authRequest);
		   
		log.debug("end of method attemptAuthentication ");
		return this.getAuthenticationManager().authenticate(authRequest);
		
	}

	 

}
