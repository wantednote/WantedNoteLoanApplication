package com.wn.loanapp.service;

import com.wn.loanapp.exception.EmailAddressAlreadyExitsException;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.User;

public interface UserLoginService {

	public User findUserByEmail(UserForm userForm);
	
	public void saveUser(UserForm userForm) throws EmailAddressAlreadyExitsException;
	
	public void updateUser(User user);
}
