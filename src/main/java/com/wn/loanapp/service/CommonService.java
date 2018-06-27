package com.wn.loanapp.service;

import java.text.ParseException;
import java.util.List;

import com.wn.loanapp.dto.LoanDetailsDTO;
import com.wn.loanapp.form.LoanDetailsForm;

public interface CommonService {
	
	public List<LoanDetailsDTO> getLoanDetails(LoanDetailsForm loanDetailsForm) throws ParseException;
	
	public List<Object> getDistributers();
	
	public Long getLoanDetailsCount(LoanDetailsForm loanDetailsForm);
}
