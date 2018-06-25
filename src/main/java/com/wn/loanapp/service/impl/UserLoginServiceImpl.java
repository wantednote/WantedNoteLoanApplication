package com.wn.loanapp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.dto.UserDTO;
import com.wn.loanapp.enums.AccountStatusEnum;
import com.wn.loanapp.exception.EmailAddressAlreadyExitsException;
import com.wn.loanapp.exception.UserOrPartnerNotFoundException;
import com.wn.loanapp.form.PartnerForm;
import com.wn.loanapp.form.RoleForm;
import com.wn.loanapp.form.UserForm;
import com.wn.loanapp.model.Partner;
import com.wn.loanapp.model.Role;
import com.wn.loanapp.model.User;
import com.wn.loanapp.model.UserRole;
import com.wn.loanapp.repository.PartnerRepository;
import com.wn.loanapp.repository.RoleRepository;
import com.wn.loanapp.repository.UserRepository;
import com.wn.loanapp.repository.UserRoleRepository;
import com.wn.loanapp.service.UserLoginService;
import com.wn.loanapp.util.DateDifference;
import com.wn.loanapp.util.Format;

@Service(UserLoginServiceImpl.SERVICE_NAME)
public class UserLoginServiceImpl implements UserLoginService {

	public static final String SERVICE_NAME = "userLoginService";

	/**
	 * @Autowired userRepository
	 */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * @Autowired partnerRepository
	 */
	@Autowired PartnerRepository partnerRepository;
	
	/**
	 * @Autowired roleRepository
	 */
	@Autowired
    private RoleRepository roleRepository;
	
	/**
	 * @Autowired userRoleRepository
	 */
	@Autowired
    private UserRoleRepository userRoleRepository;
	
	/**
	 * @Autowired sessionRegistry
	 */
	@Autowired
	private SessionRegistry sessionRegistry;
	
	/**
	 * @Autowired bCryptPasswordEncoder
	 */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
	public User getUser(UserForm userForm) {
    	return userRepository.getUser(userForm);
    }
    
    @SuppressWarnings("deprecation")
	@Override
	public List<UserDTO> findAllByRoleId(UserForm userForm) throws ParseException {
    	List<UserDTO> userDTOs = null;
    	
    	/*Sort sort = null;
    	Pageable pageable = null;
    	if(userForm.getStart() !=null && userForm.getLength() !=null ) {
    		if(Format.isStringNotEmptyAndNotNull(userForm.getFieldForSorting()) && Format.isStringNotEmptyAndNotNull(userForm.getSortDirection())) {
    			sort = new Sort(new Sort.Order(Direction.valueOf(userForm.getSortDirection().toUpperCase()), userForm.getFieldForSorting()));
    		}else {
    			sort = new Sort(new Sort.Order(Direction.ASC, "email"));
    		}
    		pageable = new PageRequest(userForm.getStart(), userForm.getLength(), sort);
    	}else {
    		sort = new Sort(new Sort.Order(Direction.ASC, "email"));
    		pageable = new PageRequest(0, 10, sort);
    	}
    	
    	String email = null;
    	if(Format.isStringNotEmptyAndNotNull(userForm.getEmailAddress())) {
    		email = userForm.getEmailAddress().toLowerCase();
    	}else {
    		email = "";
    	}
    	List<Object> users = userRepository.findAllByRoleId(Integer.parseInt(userForm.getRoleId()), email, pageable);
    	
    	if(Format.isCollectionNotEmtyAndNotNull(users)) {
    		userDTOs = new ArrayList<>();
    		Iterator<Object> itr = users.iterator();
        	while(itr.hasNext()){
        	   Object[] obj = (Object[]) itr.next();
        	   UserDTO userDTO = new UserDTO();
        	   userDTO.setId(String.valueOf(obj[0]));
        	   userDTO.setName(String.valueOf(obj[1]));
        	   userDTO.setEmail(String.valueOf(obj[2]));
        	   userDTO.setAccountStatus(String.valueOf(obj[3]));
        	   
        	   String dateString = String.valueOf(obj[4]);
        	   if(!dateString.equals(Constants.STRING_NULL)){
            	   Date lastLogin = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
            	   userDTO.setLastLogin(DateDifference.getDaysDiff(lastLogin));
        	   }else {
        		   userDTO.setLastLogin("Never Loged In");
        	   }
        	   
        	   String modifiedOn = String.valueOf(obj[5]);
        	   if(!dateString.equals(Constants.STRING_NULL)){
        		   userDTO.setModifiedOn(modifiedOn);
        	   }else {
        		   userDTO.setModifiedOn("Not Modified");
        	   }
        	   
        	   userDTO.setModifiedBy(String.valueOf(obj[6]));
        	   userDTOs.add(userDTO);
        	}
    	}*/
    	return userDTOs;
	}
    
	@Override
	public void saveUser(UserForm userForm) throws EmailAddressAlreadyExitsException{
		User user = userRepository.getUser(userForm);
		if(Format.isObjectNotEmptyAndNotNull(user)) {
			user = null;
			throw new EmailAddressAlreadyExitsException();
		}else {
			PartnerForm partnerForm = new PartnerForm();
			partnerForm.setEmailAddress(userForm.getEmailAddress());
			Partner partner = partnerRepository.getPartner(partnerForm);
			if(Format.isObjectNotEmptyAndNotNull(partner)) {
				partner = null;
				throw new EmailAddressAlreadyExitsException();
			}else {
				user = new User();
				user.setEmail(userForm.getEmailAddress());
				user.setName(userForm.getName());
				user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
		        user.setAccountStatus(AccountStatusEnum.Active);
		        RoleForm roleForm = new RoleForm();
		        roleForm.setRoleName(userForm.getRoleName());
		        Role role = roleRepository.getRole(roleForm);
		        user.setRoles(new HashSet<Role>(Arrays.asList(role)));
		        user.setCreatedBy(userForm.getEmailAddress());
		        user.setCreatedOn(Format.getCurrentSqlDateTimeStamp());
				userRepository.saveOrUpdate(user);
				user = null;
			}
		}
	}

	@Override
	public void updateUser(User user) {
		userRepository.update(user);
		user = null;
	}

	@Override
	public void updateAccountStatus(UserForm userForm) throws UserOrPartnerNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.getUser(userForm);
		if(Format.isObjectNotEmptyAndNotNull(user)) {
			if(userForm.getAccountStatus().toString().equals(AccountStatusEnum.Active.toString())) {
				user.setAccountStatus(AccountStatusEnum.Active);
			}else if(userForm.getAccountStatus().toString().equals(AccountStatusEnum.Pending.toString())) {
				user.setAccountStatus(AccountStatusEnum.Pending);
			}else if(userForm.getAccountStatus().toString().equals(AccountStatusEnum.Blocked.toString())) {
				user.setAccountStatus(AccountStatusEnum.Blocked);
			}else if(userForm.getAccountStatus().toString().equals(AccountStatusEnum.Deleted.toString())) {
				user.setAccountStatus(AccountStatusEnum.Deleted);
			}
			user.setModifiedOn(Format.getCurrentSqlDateTimeStamp());
			if(Format.isStringNotEmptyAndNotNull(userForm.getEmailAddress())) {
				user.setModifiedBy(userForm.getEmailAddress());
			}else {
				user.setModifiedBy("System");
			}
			userRepository.update(user);
			if(!userForm.getAccountStatus().toString().equals(AccountStatusEnum.Active.toString())) {
				expireSession(user.getSessionID());
			}
			user = null;
		}else {
			throw new UserOrPartnerNotFoundException();
		}
	}
	
	/**This method marks the session for expiration
	 * @author mithun Mondal
	 * @param sessionId
	  */
	private void  expireSession(String sessionId){
		if(sessionId!=null){
			SessionInformation sessionInformation=sessionRegistry.getSessionInformation(sessionId);
			if(sessionInformation!=null) {
				sessionInformation.expireNow(); // marks the session for expiration
			}
		}
	}
	/*@Override
	public User findUserByEmail(UserForm userForm) {
		User user = new User();
		return user;
	}
	
	@Override
	public void userRegistration(UserForm userForm) {
		
	}*/

	@Override
	public Boolean isValidUser(UserForm userForm) {
		if(Format.isStringNotEmptyAndNotNull(userForm.getEmailAddress())  && Format.isStringNotEmptyAndNotNull(userForm.getPassword())) {
			User user = userRepository.getUser(userForm);
			if(Format.isObjectNotEmptyAndNotNull(user)) {
				if (bCryptPasswordEncoder.matches(userForm.getPassword(), user.getPassword())) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
}
