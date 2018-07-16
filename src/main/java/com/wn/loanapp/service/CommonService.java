package com.wn.loanapp.service;

import java.text.ParseException;
import java.util.List;

import com.wn.loanapp.dto.BankStatementDTO;
import com.wn.loanapp.dto.LoanDetailsDTO;
import com.wn.loanapp.dto.LoanDispersedDTO;
import com.wn.loanapp.form.LoanDetailsForm;
import com.wn.loanapp.form.LoanDispersedForm;

public interface CommonService {
	
	public List<LoanDetailsDTO> getAppliedLoanDetails(LoanDetailsForm loanDetailsForm) throws ParseException;
	
	public List<Object> getDistributers();
	
	public Long getAppliedLoanDetailsCount(LoanDetailsForm loanDetailsForm);
	
	public List<LoanDispersedDTO> getDispersedLoanDetails(LoanDispersedForm loanDispersedForm)throws ParseException;
	
	public Long getDispersedLoanDetailsCount(LoanDispersedForm loanDispersedForm);
	
	public void updateBankStatement(List<BankStatementDTO> bankStatementDTOs);
	
	public void updatePaymentRecieved(LoanDispersedForm loanDispersedForm);
	
	public List<LoanDispersedDTO> getInvoicesLoanDetails(LoanDispersedForm loanDispersedForm)throws ParseException;
	
	public Long getInvoicesLoanDetailsCount(LoanDispersedForm loanDispersedForm);
	
	public void updatePendingLoans(List<BankStatementDTO> bankStatementDTOs);
	
	public List<Object> getRetailsers();
	
	public void updateDistributorInvoiceLoans(List<BankStatementDTO> bankStatementDTOs);
	
	public void updateDistributorInvoiceRecieved(LoanDispersedForm loanDispersedForm);
}
