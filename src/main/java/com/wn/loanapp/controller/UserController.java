package com.wn.loanapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.service.UserLoginService;

@Controller
public class UserController {

	@Autowired
	private UserLoginService userLoginService;
	

	@PostMapping("loginuser")
	public ResponseEntity<UserForm> addArticle(@RequestBody UserForm userForm, UriComponentsBuilder builder) {
		Boolean isValid = userLoginService.isValidUser(userForm);
		if(isValid) {
			userForm.setSuccessOrErrorMessage(Constants.SUCCESS);
			userForm.setMessageType(Constants.SUCCESS);
		}else {
			userForm.setSuccessOrErrorMessage(Constants.FAILURE);
			userForm.setMessageType(Constants.FAILURE);
		}
		return new ResponseEntity<UserForm>(userForm, HttpStatus.OK);
	}
}
