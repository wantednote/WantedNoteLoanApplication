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

@Controller
//@RequestMapping("/loan")
public class LoanController extends BaseController{

	@RequestMapping(value="/loan" , method = RequestMethod.GET)
	public ModelAndView getActors(HttpServletRequest request, UserDetailsSessionForm userDetailsSessionForm) {
		ModelAndView modelAndView = null;
		if(Format.isStringNotEmptyAndNotNull(userDetailsSessionForm.getEmailAddress())) {
			modelAndView = new ModelAndView("loanDashboard");
			userDetailsSessionForm.setPageHeaderTitle(Constants.ADMIN_VIEW_LOAN_HEADER_TITLE);
			userDetailsSessionForm.setSelectedBaseLink(Constants.SELECTED_BASE_LINK_ADMIN_VIEW_LOAN);
			userDetailsSessionForm.setSelectedSubLink(Constants.SELECTED_BASE_LINK_ADMIN_VIEW_LOAN);
		}else {
			modelAndView = redirectToLoginPage("/");
		}
		log.debug("End of method addRole");
		return modelAndView;
	}
}
