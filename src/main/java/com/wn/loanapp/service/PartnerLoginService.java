package com.wn.loanapp.service;

import com.wn.loanapp.exception.EmailAddressAlreadyExitsException;
import com.wn.loanapp.form.PartnerForm;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.Partner;

public interface PartnerLoginService {

	Partner findPartnerByEmail(PartnerForm partnerForm);
	
	void addPartner(UserForm userForm) throws EmailAddressAlreadyExitsException;
	
	void updatePartner(Partner Partner);
	
}
