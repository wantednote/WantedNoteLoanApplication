package com.wn.loanapp.repository.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.wn.loanapp.form.RoleForm;
import com.wn.loanapp.model.Role;
import com.wn.loanapp.repository.RoleRepository;
import com.wn.loanapp.util.Format;

@Repository(RoleRepositoryImpl.REPOSITORY_NAME)
public class RoleRepositoryImpl extends PrimaryGenericRepositoryImpl<Role, String> implements RoleRepository{

	public static final String REPOSITORY_NAME = "roleRepository";
	
	public RoleRepositoryImpl() {
		super(Role.class);
	}

	@Override
	public Role getRole(RoleForm roleForm) {
		Role role = null;
		StringBuilder sql = new StringBuilder("SELECT c FROM Role c where 1=1");
		if (Format.isLongNotEmtyAndNotZero(roleForm.getId())) {
			sql.append(" and c.id = " + roleForm.getId());
		}
		if (Format.isStringNotEmptyAndNotNull(roleForm.getRoleName())) {
			sql.append(" and c.role = '" + roleForm.getRoleName() + "'");
		}
		Query query = getPrimaryEntityManager().createQuery(sql.toString());
		try{
			role = (Role) query.getSingleResult();
		}catch (NoResultException e){
			role = null;
		}
		return role;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles(RoleForm roleForm) {
		StringBuilder sql = new StringBuilder("SELECT c FROM Role c where 1=1");
		if (Format.isLongNotEmtyAndNotZero(roleForm.getId())) {
			sql.append(" and c.id = " + roleForm.getId());
		}
		if (Format.isStringNotEmptyAndNotNull(roleForm.getRoleName())) {
			sql.append(" and c.role = '" + roleForm.getRoleName() + "'");
		}
		Query query = getPrimaryEntityManager().createQuery(sql.toString());
		List<Role> roles = query.getResultList();
		return roles;
	}
}
