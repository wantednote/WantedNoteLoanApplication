package com.wn.loanapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wn.loanapp.model.Role;
import com.wn.loanapp.repository.RoleRepository;
import com.wn.loanapp.service.RoleService;

@Service(RoleServiceImpl.SERVICE_NAME)
public class RoleServiceImpl implements RoleService {

	public static final String SERVICE_NAME = "roleService";

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findByRole(String role) {
		return roleRepository.findByRole(role);
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id.intValue());
	}
}
