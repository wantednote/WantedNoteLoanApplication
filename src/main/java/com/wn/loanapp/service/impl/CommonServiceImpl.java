package com.wn.loanapp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.wn.loanapp.dto.DistributerDTO;
import com.wn.loanapp.dto.LoanDetailsDTO;
import com.wn.loanapp.dto.LoanDispersedDTO;
import com.wn.loanapp.form.LoanDetailsForm;
import com.wn.loanapp.form.LoanDispersedForm;
import com.wn.loanapp.repository.CommonRepository;
import com.wn.loanapp.service.CommonService;
import com.wn.loanapp.util.Format;

@Service(CommonServiceImpl.SERVICE_NAME)
public class CommonServiceImpl implements CommonService{

	public static final String SERVICE_NAME = "commonService";
	
	@Autowired
	private CommonRepository commonRepository;
	
	@Override
	public List<LoanDetailsDTO> getAppliedLoanDetails(LoanDetailsForm loanDetailsForm) throws ParseException {
		
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getTnDateStart())) {
			SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
			String str = loanDetailsForm.getTnDateStart().trim();
			Date d = sdf.parse(str);
			sdf.applyPattern("yyyy-MM-dd");
			String output = sdf.format(d);
			loanDetailsForm.setTnDateStart(output);
			
		}
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getTnDateEnd())) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM dd, yyyy");
			String str1 = loanDetailsForm.getTnDateEnd().trim();
			Date d1 = sdf1.parse(str1);
			sdf1.applyPattern("yyyy-MM-dd");
			String output1 = sdf1.format(d1);
			loanDetailsForm.setTnDateEnd(output1);
		}
		return commonRepository.getAppliedLoanDetails(loanDetailsForm);
	}

	@Override
	public List<Object> getDistributers() {
		return commonRepository.getDistributers();
	}
	
	@Override
	public Long getAppliedLoanDetailsCount(LoanDetailsForm loanDetailsForm) {
		return commonRepository.getAppliedLoanDetailsCount(loanDetailsForm);
	}

	@Override
	public List<LoanDispersedDTO> getDispersedLoanDetails(LoanDispersedForm loanDispersedForm) throws ParseException{

		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getTnStartDate())) {
			SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
			String str = loanDispersedForm.getTnStartDate().trim();
			Date d = sdf.parse(str);
			sdf.applyPattern("yyyy-MM-dd");
			String output = sdf.format(d);
			loanDispersedForm.setTnStartDate(output);
			
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getTnEndDate())) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM dd, yyyy");
			String str1 = loanDispersedForm.getTnEndDate().trim();
			Date d1 = sdf1.parse(str1);
			sdf1.applyPattern("yyyy-MM-dd");
			String output1 = sdf1.format(d1);
			loanDispersedForm.setTnEndDate(output1);
		}
		return commonRepository.getDispersedLoanDetails(loanDispersedForm);
	}

	@Override
	public Long getDispersedLoanDetailsCount(LoanDispersedForm loanDispersedForm) {
		return commonRepository.getDispersedLoanDetailsCount(loanDispersedForm);
	}

}
