package com.wn.loanapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wn.loanapp.enums.AccountStatusEnum;
import com.wn.loanapp.model.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long>{

	public User findByEmail(String email);
	
	public User findByEmailAndAccountStatus(String email, AccountStatusEnum accountStatus);
	
	//select email, password, active from user where email=?
	
	/*@Query("from Auction a join a.category c where c.name=:categoryName")
	public Iterable<Auction> findByCategory(@Param("categoryName") String categoryName);*/
	
	/*@Query("from User u where u.email=:email")
	public Iterable<User> findByEmail(@Param("email") String email);*/
}
