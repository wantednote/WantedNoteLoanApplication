package com.wn.loanapp.service;

import com.wn.loanapp.enums.AccountStatusEnum;
import com.wn.loanapp.exception.EmailAddressAlreadyExitsException;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.Partner;

public interface PartnerLoginService {

	public Partner findPartnerByEmail(String emailAddress);
	
	public Partner findPartnerByEmailAndAccountStatus(String emailAddress, AccountStatusEnum accountStatus);
	
	public void addPartner(UserForm userForm) throws EmailAddressAlreadyExitsException;
	
	public void updatePartner(Partner Partner);
	
}
