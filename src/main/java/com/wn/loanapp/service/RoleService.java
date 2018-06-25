package com.wn.loanapp.service;

import java.util.List;

import com.wn.loanapp.exception.RoleAlreadyExistException;
import com.wn.loanapp.form.RoleForm;
import com.wn.loanapp.model.Role;

public interface RoleService {
	
	public Role getRole(RoleForm roleForm);
	
	public List<Role> getRoles(RoleForm roleForm);
	
	public void addRole(String roleName) throws RoleAlreadyExistException;
}
