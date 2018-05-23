package com.wn.loanapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wn.loanapp.model.PartnerRole;

@Repository("partnerRoleRepository")
public interface PartnerRoleRepository extends CrudRepository<PartnerRole, Long>{

	PartnerRole findByPartnerId(int partnerId);
}
