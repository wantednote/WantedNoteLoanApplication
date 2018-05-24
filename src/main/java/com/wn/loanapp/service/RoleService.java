package com.wn.loanapp.service;

import java.util.List;

import com.wn.loanapp.exception.RoleAlreadyExistException;
import com.wn.loanapp.model.Role;

public interface RoleService {
	
	public Role findByRole(String role);
	
	public Role findById(Long id);
	
	public List<Role> findAll();
	
	public void addRole(String roleName) throws RoleAlreadyExistException;
}
