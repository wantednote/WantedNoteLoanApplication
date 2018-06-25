package com.wn.loanapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wn.loanapp.dto.LoanDetailsDTO;
import com.wn.loanapp.form.LoanDetailsForm;
import com.wn.loanapp.repository.CommonRepository;
import com.wn.loanapp.service.CommonService;

@Service(CommonServiceImpl.SERVICE_NAME)
public class CommonServiceImpl implements CommonService{

	public static final String SERVICE_NAME = "commonService";
	
	@Autowired
	private CommonRepository commonRepository;
	
	@Override
	public List<LoanDetailsDTO> getLoanDetails(LoanDetailsForm loanDetailsForm) {
		return commonRepository.getLoanDetails(loanDetailsForm);
	}

}
