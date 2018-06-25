package com.wn.loanapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wn.loanapp.model.UserRole;
import com.wn.loanapp.repository.UserRoleRepository;
import com.wn.loanapp.service.UserRoleService;

@Service(UserRoleServiceImpl.SERVICE_NAME)
public class UserRoleServiceImpl implements UserRoleService {

	public static final String SERVICE_NAME = "userRoleService";

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public UserRole findByUserId(Long userId) {
		return userRoleRepository.findByUserId(userId);
	}
	
	
}
