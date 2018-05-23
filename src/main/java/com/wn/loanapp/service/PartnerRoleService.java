package com.wn.loanapp.service;

import com.wn.loanapp.model.PartnerRole;

public interface PartnerRoleService {

	PartnerRole findByPartnerId(Long partnerId);
}
