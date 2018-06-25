package com.wn.loanapp.service;

import java.util.List;

import com.wn.loanapp.dto.LoanDetailsDTO;
import com.wn.loanapp.form.LoanDetailsForm;

public interface CommonService {
	
	public List<LoanDetailsDTO> getLoanDetails(LoanDetailsForm loanDetailsForm);
}
