package com.wn.loanapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wn.loanapp.model.UserRole;

@Repository("userRoleRepository")
public interface UserRoleRepository extends CrudRepository<UserRole, Long>{
	
	UserRole findByUserId(int userId);

}
