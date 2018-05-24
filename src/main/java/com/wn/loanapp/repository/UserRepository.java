package com.wn.loanapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wn.loanapp.dto.UserDTO;
import com.wn.loanapp.enums.AccountStatusEnum;
import com.wn.loanapp.model.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long>{

	public User findByEmail(String email);
	
	public User findByEmailAndAccountStatus(String email, AccountStatusEnum accountStatus);
	
	//select email, password, active from user where email=?
	
	/*@Query("from Auction a join a.category c where c.name=:categoryName")
	public Iterable<Auction> findByCategory(@Param("categoryName") String categoryName);*/
	
	//select u.email, r.role from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?
	/*@Query("select u.email, u.name, u.accountStatus, u.lastLogin, u.modifiedOn from user u "
			+ "inner join user_role ur on(u.user_id=ur.user_id) "
			+ "inner join role r on(ur.role_id=r.role_id) where r.id=?")
	public Iterable<User> findAllByRoleId(@Param("id") int id);*/
	
	@Query("select u.id, u.name, u.email, u.accountStatus, DATE_FORMAT(u.lastLogin, '%d-%m-%Y'), DATE_FORMAT(u.modifiedOn ,'%d %M %Y at %l:%i %p'), u.modifiedBy from User u inner join UserRole ur on(u.id=ur.userId) "
			+ " inner join Role r on(ur.roleId=r.id) where r.id=:roleId")
	public List<Object> findAllByRoleId(@Param("roleId") int roleId);
	
//	@Query("SELECT new UserDTO(u.email, count(id))FROM Product p GROUP BY text_to_search ORDER BY counter DESC")
//	List<UserDTO> findTopProducts();
}