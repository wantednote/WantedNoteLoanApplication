package com.wn.loanapp.repository;

import java.util.List;

import com.wn.loanapp.form.RoleForm;
import com.wn.loanapp.model.Role;

public interface RoleRepository extends PrimaryGenericRepository<Role, String>{

	/*public Role findByRole(String role);
	
	public Role findById(int id);
	
	public List<Role> findAll();*/
	
	//select u.email, r.role from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?
	
	public Role getRole(RoleForm roleForm);
	
	public List<Role> getRoles(RoleForm roleForm);
}
