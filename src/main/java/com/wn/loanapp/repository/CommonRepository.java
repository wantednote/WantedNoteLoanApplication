package com.wn.loanapp.repository;

import java.util.List;

import com.wn.loanapp.dto.LoanDetailsDTO;
import com.wn.loanapp.dto.LoanDispersedDTO;
import com.wn.loanapp.form.LoanDetailsForm;
import com.wn.loanapp.form.LoanDispersedForm;
import com.wn.loanapp.model.CommonEntity;

public interface CommonRepository extends PrimaryGenericRepository<CommonEntity, String>{

	public List<LoanDetailsDTO> getAppliedLoanDetails(LoanDetailsForm loanDetailsForm);
	
	public List<Object> getDistributers();
	
	public Long getAppliedLoanDetailsCount(LoanDetailsForm loanDetailsForm);
	
	public List<LoanDispersedDTO> getDispersedLoanDetails(LoanDispersedForm loanDispersedForm);
	
	public Long getDispersedLoanDetailsCount(LoanDispersedForm loanDispersedForm);
}
