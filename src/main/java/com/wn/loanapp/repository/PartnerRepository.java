package com.wn.loanapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.wn.loanapp.enums.AccountStatusEnum;
import com.wn.loanapp.model.Partner;

public interface PartnerRepository extends CrudRepository<Partner, Long>{

	public Partner findByEmail(String email);
	
	public Partner findByEmailAndAccountStatus(String email, AccountStatusEnum accountStatus);
}
