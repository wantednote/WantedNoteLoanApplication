package com.wn.loanapp.repository.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.form.PartnerForm;
import com.wn.loanapp.model.Partner;
import com.wn.loanapp.repository.PartnerRepository;
import com.wn.loanapp.util.Format;

@Repository(PartnerRepositoryImpl.REPOSITORY_NAME)
public class PartnerRepositoryImpl extends PrimaryGenericRepositoryImpl<Partner, String> implements PartnerRepository{
	
	public static final String REPOSITORY_NAME = "partnerRepository";
	
	public PartnerRepositoryImpl() {
		super(Partner.class);
	}

	@Override
	public Partner getPartner(PartnerForm partnerForm) {
		Partner partner = null;
		StringBuilder sql = new StringBuilder("SELECT c FROM Partner c where 1=1");
		if (Format.isLongNotEmtyAndNotZero(partnerForm.getId())) {
			sql.append(" and c.id = " + partnerForm.getId());
		}
		if (Format.isStringNotEmptyAndNotNull(partnerForm.getEmailAddress())) {
			sql.append(" and c.email = '" + partnerForm.getEmailAddress() + "'");
		}
		if (Format.isNotNull(partnerForm.getAccountStatus())) {
			sql.append(" and c.accountStatus in (" + partnerForm.getAccountStatus().getValue() + ")");
		}
		Query query = getPrimaryEntityManager().createQuery(sql.toString());
		try{
			partner = (Partner) query.getSingleResult();
		}catch (NoResultException e){
			partner = null;
		}
		return partner;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Partner> getPartners(PartnerForm partnerForm) {
		StringBuilder sql = new StringBuilder("SELECT c FROM Partner c where 1=1");
		if (Format.isLongNotEmtyAndNotZero(partnerForm.getId())) {
			sql.append(" and c.id = " + partnerForm.getId());
		}
		if (Format.isStringNotEmptyAndNotNull(partnerForm.getEmailAddress())) {
			sql.append(" and c.email = '" + partnerForm.getEmailAddress() + "'");
		}
		if (Format.isNotNull(partnerForm.getAccountStatus())) {
			sql.append(" and c.accountStatus in (" + partnerForm.getAccountStatus().getValue() + ")");
		}
		Query query = getPrimaryEntityManager().createQuery(sql.toString());
		if(Format.isIntegerNotEmtyAndNotZero(partnerForm.getStart()) && Format.isIntegerNotEmtyAndNotZero(partnerForm.getLength())) {
			partnerForm.setStart(partnerForm.getStart() + Constants.GET_FIRST_INDEX_OBJECT);
			query.setFirstResult((partnerForm.getStart()-1) * partnerForm.getLength()); 
			query.setMaxResults(partnerForm.getLength());
		}
		List<Partner> partners = query.getResultList();
		return partners;
	}

	@Override
	public long getPartnerCount(PartnerForm partnerForm) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(c) FROM Partner c where 1=1");
		if (Format.isLongNotEmtyAndNotZero(partnerForm.getId())) {
			sql.append(" and c.id = " + partnerForm.getId());
		}
		if (Format.isStringNotEmptyAndNotNull(partnerForm.getEmailAddress())) {
			sql.append(" and c.email = '" + partnerForm.getEmailAddress() + "'");
		}
		if (Format.isNotNull(partnerForm.getAccountStatus())) {
			sql.append(" and c.accountStatus in (" + partnerForm.getAccountStatus().getValue() + ")");
		}
		Query query = getPrimaryEntityManager().createQuery(sql.toString());
		long  partnerCount = (Long)query.getSingleResult();
		return partnerCount;
	}
}
