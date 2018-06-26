package com.wn.loanapp.repository;

import java.util.List;

import com.wn.loanapp.dto.DistributerDTO;
import com.wn.loanapp.dto.LoanDetailsDTO;
import com.wn.loanapp.form.LoanDetailsForm;
import com.wn.loanapp.model.CommonEntity;

public interface CommonRepository extends PrimaryGenericRepository<CommonEntity, String>{

	public List<LoanDetailsDTO> getLoanDetails(LoanDetailsForm loanDetailsForm);
	
	public List<Object> getDistributers();
	
	public Long getLoanDetailsCount(LoanDetailsForm loanDetailsForm);
}
