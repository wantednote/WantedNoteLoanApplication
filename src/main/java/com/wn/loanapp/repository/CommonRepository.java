package com.wn.loanapp.repository;

import java.sql.SQLException;
import java.util.List;

import com.wn.loanapp.dto.BankStatementDTO;
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
	
	public void updateBankStatement(BankStatementDTO bankStatementDTO) throws SQLException;
	
	public List<LoanDispersedDTO> getInvoicesLoanDetails(LoanDispersedForm loanDispersedForm);
	
	public Long getInvoicesLoanDetailsCount(LoanDispersedForm loanDispersedForm);
	
	public void updatePendingLoans(BankStatementDTO bankStatementDTO) throws SQLException;
	
	public List<Object> getRetailers();
	
	public void updateDistributorInvoice(BankStatementDTO bankStatementDTO) throws SQLException;
	
}
