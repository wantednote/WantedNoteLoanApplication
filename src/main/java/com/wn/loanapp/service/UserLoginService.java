package com.wn.loanapp.service;

import java.text.ParseException;
import java.util.List;

import com.wn.loanapp.dto.UserDTO;
import com.wn.loanapp.enums.AccountStatusEnum;
import com.wn.loanapp.exception.EmailAddressAlreadyExitsException;
import com.wn.loanapp.exception.UserOrPartnerNotFoundException;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.User;

public interface UserLoginService {
	
	public User getUser(UserForm userForm);
	
	public void saveUser(UserForm userForm) throws EmailAddressAlreadyExitsException;
	
	public void updateUser(User user);
	
	public List<UserDTO> findAllByRoleId(UserForm userForm) throws ParseException;
	
	public void updateAccountStatus(UserForm userForm) throws UserOrPartnerNotFoundException;
	
	public Boolean isValidUser(UserForm userForm);
}
