package com.wn.loanapp.service;

import com.wn.loanapp.model.Role;

public interface RoleService {
	
	Role findByRole(String role);
	
	Role findById(Long id);
}
