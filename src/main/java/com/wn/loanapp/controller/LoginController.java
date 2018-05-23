package com.wn.loanapp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wn.loanapp.common.BaseController;
import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.exception.EmailAddressAlreadyExitsException;
import com.wn.loanapp.form.UserDetailsSessionForm;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.service.PartnerLoginService;
import com.wn.loanapp.service.UserLoginService;
import com.wn.loanapp.util.Format;

@Controller
//@RequestMapping(value = "/login")
public class LoginController extends BaseController{

	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private PartnerLoginService partnerLoginService;

    //@RequestMapping(value="/" , method = RequestMethod.GET)
    @RequestMapping(value={"/index" , "/", ""} , method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }
    
    @RequestMapping(value="/registration" , method = RequestMethod.GET)
    public ModelAndView gerister() {
        ModelAndView modelAndView = new ModelAndView("registration");
        return modelAndView;
    }
    
    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public ModelAndView gerister(UserForm userForm) {
        ModelAndView modelAndView = new ModelAndView("login");
        if(Format.isStringNotEmptyAndNotNull(userForm.getUserType())) {
        	try {
        		if(userForm.getUserType().equals(Constants.USER_TYPE_ADMIN))
            		userLoginService.saveUser(userForm);
            	else if (userForm.getUserType().equals(Constants.USER_TYPE_PARTNER));
            		partnerLoginService.addPartner(userForm);
        	}catch (EmailAddressAlreadyExitsException e) {
				// TODO: handle exception
			}
        }
        return modelAndView;
    }
    
    //@RequestMapping(value="/loginPage" , method = RequestMethod.GET)
    @RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request , HttpServletResponse response , String error) throws IOException{
		String userType = null;
		ModelAndView modelAndView = new ModelAndView("login");
		RequestCache requestCache = new HttpSessionRequestCache();
		DefaultSavedRequest savedRequest = (DefaultSavedRequest)requestCache.getRequest(request, response);
		if(Format.isNotNull(savedRequest)){
			String path = savedRequest.getServletPath();
			if(Format.isStringNotEmptyAndNotNull(path)){
				if(path.startsWith("/admin") || path.startsWith("/sadmin") ){
					userType = Constants.USER_TYPE_ADMIN;
				}else if(path.startsWith("/partner")){
					userType = Constants.USER_TYPE_PARTNER;
				}
			}else{
				response.sendRedirect("/WantedNoteLoanApplication");
			}
		}else{
			response.sendRedirect("/WantedNoteLoanApplication");
		}
		modelAndView.addObject("userType", userType);
		addSuccessOrErrorMessageToModel(modelAndView);
		return modelAndView;
	}
    
    //@RequestMapping(value={"/login?error=true"})
    @RequestMapping(value={"/loginFailure"})
	public ModelAndView loginFailure( HttpServletRequest request , HttpServletResponse response , String error) throws IOException{
		log.debug("Start custom loginFailure handler");
		String userType = null;
		ModelAndView modelAndView = new ModelAndView("login");
		RequestCache requestCache = new HttpSessionRequestCache();
		DefaultSavedRequest savedRequest = (DefaultSavedRequest)requestCache.getRequest(request, response);
		log.info("savedRequest : "+savedRequest);
		if(savedRequest != null){
			String path = savedRequest.getServletPath();
			if(Format.isStringNotEmptyAndNotNull(path)){
				if(path.startsWith("/admin")){
					userType = Constants.USER_TYPE_ADMIN;
				}else if(path.startsWith("/partner")){
					userType = Constants.USER_TYPE_PARTNER;
				}
			}else{
				response.sendRedirect("/");
			}
		}else{
			response.sendRedirect("/");
		}
		modelAndView.addObject("userType", userType);
		saveErrorMessage(getText("error.login.failure"));
		addSuccessOrErrorMessageToModel(modelAndView);
		log.debug("End custom loginFailure handler ");
		return modelAndView;
	}
    
    /**
	 * This will invalidate the session and redirect the user to Login page
	 * @param request
	 * @param response
	 * @return
	 * @author mithun.mondal
	 */
	@RequestMapping("/logout")
	public ModelAndView logout( HttpServletRequest request , HttpServletResponse response ,UserDetailsSessionForm userDetailsSessionForm){
		log.debug("start of method logout()");
		String url  = "";
		if(Format.isNotNull(userDetailsSessionForm) && Format.isStringNotEmptyAndNotNull(userDetailsSessionForm.getUserRole())){
			if(userDetailsSessionForm.getUserRole().equals(Constants.PARTNER)){
				url = Constants.SELECTED_BASE_LINK_PARTNER_DASHBOARD;
			}else if (userDetailsSessionForm.getUserRole().equals(Constants.ADMIN)){
				url = Constants.SELECTED_BASE_LINK_ADMIN_DASHBOARD;
			}
		}
		CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		cookieClearingLogoutHandler.logout(request, response, null);
		securityContextLogoutHandler.logout(request, response, null);
		log.debug( " ---isInvalidateHttpSession--> " + securityContextLogoutHandler.isInvalidateHttpSession());
		if (securityContextLogoutHandler.isInvalidateHttpSession()) {
			SecurityContextHolder.getContext().setAuthentication(null);
			SecurityContextHolder.getContextHolderStrategy().clearContext();
			securityContextLogoutHandler.setClearAuthentication(true);
			securityContextLogoutHandler.setInvalidateHttpSession(true);
		}
		HttpSession session =request.getSession(false);
		if(session!= null)
		{	request.getSession(false).setAttribute("adminDetailsSessionForm", null);
			session.invalidate();
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
	
		log.debug("end of method logout()");
		return new ModelAndView(new RedirectView(url));
		//return new ModelAndView("home");
		//return new ModelAndView(new RedirectView("index"));
	}
	
	/**
	 * This will be called when the user is trying to access a page without having the {@link Authorization} 
	 * @return
	 * @author mithun.mondal
	 */
	@RequestMapping(value={"/accessDenied"})
	public ModelAndView accessDenied( HttpServletRequest request , HttpServletResponse response){
		log.debug("Start custom accessDenied handler");
		ModelAndView modelAndView = new ModelAndView("home");
		saveErrorMessage(getText("error.accessdenied"));
		addSuccessOrErrorMessageToModel(modelAndView);
		log.debug("End  custom accessDenied handler ");
		return modelAndView;
	}
	
    /**
	 * @author mithun.mondal
	 * This method add success or error messages to the model object. These
	 * objects are used on the jsp to show success or error message
	 * @param modelAndView
	 */
	public void addSuccessOrErrorMessageToModel(ModelAndView modelAndView) {
		if (successMessages != null) {
			modelAndView.addObject(Constants.MESSAGES_KEY, successMessages);
		}
		if (errorMessages != null) {
			modelAndView.addObject(Constants.ERROR_KEY, errorMessages);
		}
		successMessages = null;
		errorMessages = null;
	}
    
    /*@RequestMapping(value="/login" , method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }*/
}
