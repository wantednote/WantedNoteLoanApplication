package com.wn.loanapp.service;

import java.text.ParseException;
import java.util.List;

import com.wn.loanapp.dto.UserDTO;
import com.wn.loanapp.enums.AccountStatusEnum;
import com.wn.loanapp.exception.EmailAddressAlreadyExitsException;
import com.wn.loanapp.exception.UserOrPartnerNotFoundException;
import com.wn.loanapp.form.PartnerForm;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.Partner;

public interface PartnerLoginService {
	
	public Partner getPartner(PartnerForm partnerForm);
	
	public void addPartner(UserForm userForm) throws EmailAddressAlreadyExitsException;
	
	public void updatePartner(Partner Partner);
	
	public List<UserDTO> findAllByRoleId(long roleId) throws ParseException;
	
	public void updateAccountStatus(UserForm userForm) throws UserOrPartnerNotFoundException; 
}
