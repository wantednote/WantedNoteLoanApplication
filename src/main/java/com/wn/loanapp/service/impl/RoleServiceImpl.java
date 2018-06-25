package com.wn.loanapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wn.loanapp.exception.RoleAlreadyExistException;
import com.wn.loanapp.form.RoleForm;
import com.wn.loanapp.model.Role;
import com.wn.loanapp.repository.RoleRepository;
import com.wn.loanapp.service.RoleService;
import com.wn.loanapp.util.Format;

@Service(RoleServiceImpl.SERVICE_NAME)
public class RoleServiceImpl implements RoleService {

	public static final String SERVICE_NAME = "roleService";

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role getRole(RoleForm roleForm) {
		return roleRepository.getRole(roleForm);
	}

	@Override
	public List<Role> getRoles(RoleForm roleForm) {
		return roleRepository.getRoles(roleForm);
	}
	
	@Override
	public void addRole(String roleName) throws RoleAlreadyExistException {
		String roleNameString = roleName.toUpperCase();
		RoleForm roleForm = new RoleForm();
		roleForm.setRoleName(roleNameString);
		Role role = roleRepository.getRole(roleForm);
		if(Format.isObjectNotEmptyAndNotNull(role)) {
			throw new RoleAlreadyExistException();
		}else {
			role = new Role();
			role.setRole(roleNameString);
			roleRepository.saveOrUpdate(role);
		}
	}
}
