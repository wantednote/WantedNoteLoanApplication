package com.wn.loanapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wn.loanapp.dto.UserDTO;
import com.wn.loanapp.enums.AccountStatusEnum;
import com.wn.loanapp.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findById(int id);

	public User findByEmail(String email);
	
	public User findByEmailAndAccountStatus(String email, AccountStatusEnum accountStatus);
	
	
	
	@Query("select u.id, u.name, u.email, u.accountStatus, DATE_FORMAT(u.lastLogin, '%d-%m-%Y'), "
			+ " DATE_FORMAT(u.modifiedOn ,'%d %M %Y at %l:%i %p'), u.modifiedBy from User u inner join UserRole ur on(u.id=ur.userId) "
			+ " inner join Role r on(ur.roleId=r.id) where "
			+ " r.id=:roleId and "
			+ " u.email like :email" + "%")
	/*@Query("select u.id, u.name, u.email, u.accountStatus, DATE_FORMAT(u.lastLogin, '%d-%m-%Y'), DATE_FORMAT(u.modifiedOn ,'%d %M %Y at %l:%i %p'), u.modifiedBy from User u inner join UserRole ur on(u.id=ur.userId) "
			+ " inner join Role r on(ur.roleId=r.id) where r.id=:roleId")*/
	public List<Object> findAllByRoleId(@Param("roleId") int roleId, @Param("email") String email, Pageable pageable);
	
}