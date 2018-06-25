package com.wn.loanapp.repository.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.wn.loanapp.model.PartnerRole;
import com.wn.loanapp.repository.PartnerRoleRepository;
import com.wn.loanapp.util.Format;

@Repository(PartnerRoleRepositoryImpl.REPOSITORY_NAME)
public class PartnerRoleRepositoryImpl extends PrimaryGenericRepositoryImpl<PartnerRole, String> implements PartnerRoleRepository {

	public static final String REPOSITORY_NAME = "partnerRoleRepository";
	
	public PartnerRoleRepositoryImpl() {
		super(PartnerRole.class);
	}

	@Override
	public PartnerRole findByPartnerId(long partnerId) {
		PartnerRole partnerRole = null;
		StringBuilder sql = new StringBuilder("SELECT c FROM PartnerRole c where 1=1");
		if (Format.isLongNotEmtyAndNotZero(partnerId)) {
			sql.append(" and c.partnerId = " + partnerId);
		}
		Query query = getPrimaryEntityManager().createQuery(sql.toString());
		try{
			partnerRole = (PartnerRole) query.getSingleResult();
		}catch (NoResultException e){
			partnerRole = null;
		}
		return partnerRole;
	}

}
