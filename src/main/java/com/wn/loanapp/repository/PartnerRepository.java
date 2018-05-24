package com.wn.loanapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wn.loanapp.enums.AccountStatusEnum;
import com.wn.loanapp.model.Partner;

public interface PartnerRepository extends CrudRepository<Partner, Long>{

	public Partner findByEmail(String email);
	
	public Partner findByEmailAndAccountStatus(String email, AccountStatusEnum accountStatus);
	
	@Query("select p.id, p.name, p.email, p.accountStatus, DATE_FORMAT(p.lastLogin, '%d-%m-%Y'), DATE_FORMAT(p.modifiedOn ,'%d %M %Y at %l:%i %p'), p.modifiedBy from Partner p inner join PartnerRole pr on(p.id=pr.partnerId) "
			+ " inner join Role r on(pr.roleId=r.id) where r.id=:roleId")
	public List<Object> findAllByRoleId(@Param("roleId") int roleId);
}
