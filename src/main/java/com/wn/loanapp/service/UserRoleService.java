package com.wn.loanapp.service;

import com.wn.loanapp.model.UserRole;

public interface UserRoleService {
	
	UserRole findByUserId(Long userId);
}
