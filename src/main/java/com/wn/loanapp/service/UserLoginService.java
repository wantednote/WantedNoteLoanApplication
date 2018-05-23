package com.wn.loanapp.service;

import com.wn.loanapp.enums.AccountStatusEnum;
import com.wn.loanapp.exception.EmailAddressAlreadyExitsException;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.User;

public interface UserLoginService {

	public User findUserByEmail(String emailAddress);
	
	public User findUserByEmailAndAccountStatus(String emailAddress, AccountStatusEnum accountStatus);
	
	public void saveUser(UserForm userForm) throws EmailAddressAlreadyExitsException;
	
	public void updateUser(User user);
}
