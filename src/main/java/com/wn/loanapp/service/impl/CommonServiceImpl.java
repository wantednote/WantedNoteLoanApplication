package com.wn.loanapp.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestDataBinder;

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
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM D, YYYY");
//		CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, true);
		
		return commonRepository.getLoanDetails(loanDetailsForm);
	}

}
