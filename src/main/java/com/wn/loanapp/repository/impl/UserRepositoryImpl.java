package com.wn.loanapp.repository.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.User;
import com.wn.loanapp.repository.UserRepository;
import com.wn.loanapp.util.Format;

@Repository(UserRepositoryImpl.REPOSITORY_NAME)
public class UserRepositoryImpl extends PrimaryGenericRepositoryImpl<User, String> implements UserRepository {

	public static final String REPOSITORY_NAME = "userRepository";
	
	public UserRepositoryImpl() {
		super(User.class);
	}

	@Override
	public User getUser(UserForm userForm) {
		User user = null;
		StringBuilder sql = new StringBuilder("SELECT c FROM User c where 1=1");
		if (Format.isLongNotEmtyAndNotZero(userForm.getId())) {
			sql.append(" and c.id = " + userForm.getId());
		}
		if (Format.isStringNotEmptyAndNotNull(userForm.getEmailAddress())) {
			sql.append(" and c.email = '" + userForm.getEmailAddress() + "'");
		}
		if (Format.isNotNull(userForm.getAccountStatus())) {
			sql.append(" and c.accountStatus in (" + userForm.getAccountStatus().getValue() + ")");
		}
		Query query = getPrimaryEntityManager().createQuery(sql.toString());
		try{
			user = (User) query.getSingleResult();
		}catch (NoResultException e){
			user = null;
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(UserForm userForm) {
		StringBuilder sql = new StringBuilder("SELECT c FROM User c where 1=1");
		if (Format.isLongNotEmtyAndNotZero(userForm.getId())) {
			sql.append(" and c.id = " + userForm.getId());
		}
		if (Format.isStringNotEmptyAndNotNull(userForm.getEmailAddress())) {
			sql.append(" and c.email = '" + userForm.getEmailAddress() + "'");
		}
		if (Format.isNotNull(userForm.getAccountStatus())) {
			sql.append(" and c.accountStatus in (" + userForm.getAccountStatus().getValue() + ")");
		}
		Query query = getPrimaryEntityManager().createQuery(sql.toString());
		if(Format.isIntegerNotEmtyAndNotZero(userForm.getStart()) && Format.isIntegerNotEmtyAndNotZero(userForm.getLength())) {
			userForm.setStart(userForm.getStart() + Constants.GET_FIRST_INDEX_OBJECT);
			query.setFirstResult((userForm.getStart()-1) * userForm.getLength()); 
			query.setMaxResults(userForm.getLength());
		}
		List<User> users = query.getResultList();
		return users;
	}

	@Override
	public long getUserCount(UserForm userForm) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(c) FROM User c where 1=1");
		if (Format.isLongNotEmtyAndNotZero(userForm.getId())) {
			sql.append(" and c.id = " + userForm.getId());
		}
		if (Format.isStringNotEmptyAndNotNull(userForm.getEmailAddress())) {
			sql.append(" and c.email = '" + userForm.getEmailAddress() + "'");
		}
		if (Format.isNotNull(userForm.getAccountStatus())) {
			sql.append(" and c.accountStatus in (" + userForm.getAccountStatus().getValue() + ")");
		}
		Query query = getPrimaryEntityManager().createQuery(sql.toString());
		long  userCount = (Long)query.getSingleResult();
		return userCount;
	}

}
