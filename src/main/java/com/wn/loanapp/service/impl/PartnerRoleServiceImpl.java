package com.wn.loanapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wn.loanapp.model.PartnerRole;
import com.wn.loanapp.repository.PartnerRoleRepository;
import com.wn.loanapp.service.PartnerRoleService;

@Service(PartnerRoleServiceImpl.SERVICE_NAME)
public class PartnerRoleServiceImpl implements PartnerRoleService {

	public static final String SERVICE_NAME = "partnerRoleService";

	@Autowired
	private PartnerRoleRepository partnerRoleRepository;
	
	@Override
	public PartnerRole findByPartnerId(Long partnerId) {
		return partnerRoleRepository.findByPartnerId(partnerId.intValue());
	}
}
