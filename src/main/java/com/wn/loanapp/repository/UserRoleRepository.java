package com.wn.loanapp.repository;

import com.wn.loanapp.model.UserRole;

public interface UserRoleRepository extends PrimaryGenericRepository<UserRole, String>{
	
	UserRole findByUserId(long userId);

}
