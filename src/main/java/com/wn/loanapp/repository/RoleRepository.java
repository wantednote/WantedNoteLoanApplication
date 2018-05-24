package com.wn.loanapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wn.loanapp.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByRole(String role);
	
	Role findById(int id);
	
	List<Role> findAll();
	
	//select u.email, r.role from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?
}
