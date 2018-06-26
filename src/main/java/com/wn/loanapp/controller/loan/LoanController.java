package com.wn.loanapp.controller.loan;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wn.loanapp.common.BaseController;
import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.dto.DatatableJsonResponse;
import com.wn.loanapp.dto.LoanDetailsDTO;
import com.wn.loanapp.dto.UserDTO;
import com.wn.loanapp.form.LoanDetailsForm;
import com.wn.loanapp.form.RoleForm;
import com.wn.loanapp.form.UserDetailsSessionForm;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.Role;
import com.wn.loanapp.service.CommonService;
import com.wn.loanapp.util.Format;

@Controller
public class LoanController extends BaseController{
	
	@Autowired
	private CommonService commonService;
	
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
	
	@RequestMapping(value="loanList", method=RequestMethod.POST)
	public @ResponseBody DatatableJsonResponse getActorList(LoanDetailsForm loanDetailsForm) {
		List<LoanDetailsDTO> detailsDTOs = null;
		Integer count = 0;
		DatatableJsonResponse datatableJsonResponse = new DatatableJsonResponse();
		try {
			detailsDTOs = commonService.getLoanDetails(loanDetailsForm);
			if(Format.isCollectionEmtyOrNull(detailsDTOs)){
				detailsDTOs = new ArrayList<>();
			}else{
				count = commonService.getLoanDetailsCount(loanDetailsForm).intValue();
			}
		}catch (ParseException e) {
			detailsDTOs = new ArrayList<>();
		}
		datatableJsonResponse.setData(detailsDTOs);
		datatableJsonResponse.setRecordsTotal(count);
		datatableJsonResponse.setRecordsFiltered(count);
		return datatableJsonResponse;
	}
}
