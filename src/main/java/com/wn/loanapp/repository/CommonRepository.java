package com.wn.loanapp.repository;

import java.util.List;

import com.wn.loanapp.dto.DistributerDTO;
import com.wn.loanapp.dto.LoanDetailsDTO;
import com.wn.loanapp.form.LoanDetailsForm;
import com.wn.loanapp.model.CommonEntity;

public interface CommonRepository extends PrimaryGenericRepository<CommonEntity, String>{

	public List<LoanDetailsDTO> getLoanDetails(LoanDetailsForm loanDetailsForm);
<<<<<<< HEAD

=======
	
	public List<Object> getDistributers();
	
>>>>>>> ef7ccc350fd5924ed53d0cf0d3184fc157262b4a
	public Long getLoanDetailsCount(LoanDetailsForm loanDetailsForm);
}
