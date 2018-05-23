package com.wn.loanapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.wn.loanapp.model.Partner;

public interface PartnerRepository extends CrudRepository<Partner, Long>{

	Partner findByEmail(String email);
}
