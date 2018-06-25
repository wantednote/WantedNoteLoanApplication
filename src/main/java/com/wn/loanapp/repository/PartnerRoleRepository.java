package com.wn.loanapp.repository;

import com.wn.loanapp.model.PartnerRole;

public interface PartnerRoleRepository extends PrimaryGenericRepository<PartnerRole, String>{

	public PartnerRole findByPartnerId(long partnerId);
}
