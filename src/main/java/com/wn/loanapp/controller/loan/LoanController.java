package com.wn.loanapp.controller.loan;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wn.loanapp.common.BaseController;
import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.dto.DatatableJsonResponse;
import com.wn.loanapp.dto.DistributerDTO;
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
			
			List<DistributerDTO> distributers = new ArrayList<>();
			List<Object> distributerDTOs = commonService.getDistributers();
			if(Format.isCollectionNotEmtyAndNotNull(distributerDTOs)) {
				Iterator<Object> itr = distributerDTOs.iterator();
		    	while(itr.hasNext()){
		    	   Object[] obj = (Object[]) itr.next();
		    	   String distributerId = String.valueOf(obj[0]).trim();
		    	   String distributerName = String.valueOf(obj[1]);
		    	   DistributerDTO distributerDTO = new DistributerDTO();
		    	   distributerDTO.setDistId(distributerId);
		    	   distributerDTO.setDistName(distributerName);
		    	   distributers.add(distributerDTO);
		    	}
			}
			modelAndView.addObject("distributers", distributers);
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
	
	@RequestMapping(value = "/downloadloancsv", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public void downloadFile(HttpServletResponse response, 
		@RequestParam(required=false) String startDate,
		@RequestParam(required=false) String endDate,
		@RequestParam(required=false) String distributers) {
		
		LoanDetailsForm loanDetailsForm = new LoanDetailsForm();
		if(Format.isStringNotEmptyAndNotNull(startDate)) {
			loanDetailsForm.setTnDateStart(startDate);
		}
		if(Format.isStringNotEmptyAndNotNull(endDate)) {
			loanDetailsForm.setTnDateEnd(endDate);
		}
		if(Format.isStringNotEmptyAndNotNull(distributers)) {
			loanDetailsForm.setDistributer(distributers);
		}
		/*SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy_MM_dd,HH_mm_ss");
	    Date now = new Date();
	    String strTime = sdfTime.format(now);*/
	    
		response.addHeader("Content-Type", "application/csv");
		response.addHeader("Content-Disposition", "attachment; filename=loan_details.csv");
		response.setCharacterEncoding("UTF-8");
		
		List<LoanDetailsDTO> detailsDTOs = null;
        try {
        	PrintWriter out = response.getWriter();
        	
        	StringBuilder sb = new StringBuilder();
        	sb.append("Order No");
            sb.append(',');
            sb.append("Distributer Name");
            sb.append(',');
            sb.append("First Name");
            sb.append(',');
            sb.append("Date");
            sb.append(',');
            sb.append("Amount in Rs.");
            sb.append('\n');
            out.write(sb.toString());
            
        	detailsDTOs = commonService.getLoanDetails(loanDetailsForm);
        	if(Format.isCollectionNotEmtyAndNotNull(detailsDTOs)) {
        		detailsDTOs.forEach(detailsDTO -> {
        			StringBuilder sb1 = new StringBuilder();
                	sb1.append(detailsDTO.getOrderNo());
                    sb1.append(',');
                    sb1.append(detailsDTO.getDistributorName());
                    sb1.append(',');
                    sb1.append(detailsDTO.getFirstName());
                    sb1.append(',');
                    sb1.append(detailsDTO.getTnDate());
                    sb1.append(',');
                    sb1.append(detailsDTO.getAmount());
                    sb1.append('\n');
                    out.write(sb1.toString());
        		});
        	}
            out.close();
        }catch (IOException e) {
        	throw new RuntimeException("There is an error while downloading csv", e);
        } catch (ParseException e) {
        	throw new RuntimeException("Parse exception while creating csv", e);
		}
	}
}
