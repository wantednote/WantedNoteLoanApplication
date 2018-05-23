package com.wn.loanapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wn.loanapp.common.BaseController;
import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.form.UserDetailsSessionForm;
import com.wn.loanapp.util.Format;

/**
 * Only here to test access restrictions for Spring Security.
 * 
 * @author Mithun Mondal
 *
 */
@Controller
//@RequestMapping(value = "/partner")
public class PartnerController extends BaseController{

	//@RequestMapping(value={"/dashboard" , "/" ,"/home", "/index"})
	@RequestMapping(value="/partner" , method = RequestMethod.GET)
	public ModelAndView partnerHome(HttpServletRequest request , UserDetailsSessionForm userDetailsSessionForm){
		log.debug("Start of method partnerHome");
		ModelAndView modelAndView = null;
		try {
			if(Format.isStringNotEmptyAndNotNull(userDetailsSessionForm.getEmailAddress())){
				// here we are redirecting the user to the dashboard based on role.
				//modelAndView = dashboad(request , userDetailsSessionForm);
				modelAndView = new ModelAndView("partnerDashboard");
				modelAndView.addObject("url", Constants.USER_TYPE_PARTNER);
			}else{
				modelAndView = redirectToLoginPage(request.getServletPath());
			}
		} catch (Exception e) {
			log.error("exception occured : ",e);
			saveErrorMessage("Some Exception occured! Please try again");
			modelAndView = redirectToLoginPage(request.getServletPath());
		}
		log.debug("End of method partnerHome");
		addSuccessOrErrorMessageToModel(modelAndView);
		return modelAndView;
	}
}
