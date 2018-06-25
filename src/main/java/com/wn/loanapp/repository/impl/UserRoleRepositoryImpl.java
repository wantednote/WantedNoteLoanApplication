package com.wn.loanapp.repository.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.wn.loanapp.model.UserRole;
import com.wn.loanapp.repository.UserRoleRepository;
import com.wn.loanapp.util.Format;

@Repository(UserRoleRepositoryImpl.REPOSITORY_NAME)
public class UserRoleRepositoryImpl extends PrimaryGenericRepositoryImpl<UserRole, String> implements UserRoleRepository{

	public static final String REPOSITORY_NAME = "userRoleRepository";
	
	public UserRoleRepositoryImpl() {
		super(UserRole.class);
	}

	@Override
	public UserRole findByUserId(long userId) {
		UserRole userRole = null;
		StringBuilder sql = new StringBuilder("SELECT c FROM UserRole c where 1=1");
		if (Format.isLongNotEmtyAndNotZero(userId)) {
			sql.append(" and c.userId = " + userId);
		}
		Query query = getPrimaryEntityManager().createQuery(sql.toString());
		try{
			userRole = (UserRole) query.getSingleResult();
		}catch (NoResultException e){
			userRole = null;
		}
		return userRole;
	}
	
}
